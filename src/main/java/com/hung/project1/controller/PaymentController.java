package com.hung.project1.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hung.project1.entity.FinanceIncurredPlan;
import com.hung.project1.entity.FinancePlan;
import com.hung.project1.entity.GeneralPlan;
import com.hung.project1.entity.PersonelIncurredPlan;
import com.hung.project1.entity.PersonelPlan;
import com.hung.project1.repository.FinanceIncurredPlanRepository;
import com.hung.project1.repository.FinancePlanRepository;
import com.hung.project1.repository.GeneralPlanRepository;
import com.hung.project1.repository.PersonelIncurredPlanRepository;
import com.hung.project1.repository.PersonelPlanRepository;
import com.hung.project1.service.PaymentService;

@Controller
public class PaymentController {

	@Autowired
	private GeneralPlanRepository planRepo;

	@Autowired
	private PersonelPlanRepository personelPlanRepo;

	@Autowired
	private FinancePlanRepository financePlanRepo;

	@Autowired
	private PersonelIncurredPlanRepository personelIncurredPlanRepo;

	@Autowired
	private FinanceIncurredPlanRepository incurredFinanceRepo;

	@Autowired
	private PaymentService paymentService;

	@GetMapping("/payment/{planId}")
	public String viewPayment(@PathVariable("planId") int planId, ModelMap map) {
		GeneralPlan generalPlan = planRepo.findById(planId);

		List<PersonelPlan> personelPlanList = personelPlanRepo.findByGeneralPlanId(generalPlan.getId());

		List<FinancePlan> expectedFinances = financePlanRepo.findByGeneralPlanId(generalPlan.getId());

		List<PersonelIncurredPlan> personelIncurredPlanList = personelIncurredPlanRepo
				.findByGeneralPlanId(generalPlan.getId());

		List<FinanceIncurredPlan> incurredFinances = incurredFinanceRepo
				.findByGeneralPlanId(generalPlan.getId());

		double totalFinance;
		if (generalPlan.getStatus().equals("Đã hoàn thành")) {
			totalFinance = generalPlan.getCost();
		} else {
			totalFinance = paymentService.calculateTotalFinance(expectedFinances, incurredFinances);
		}

		map.addAttribute("plan", generalPlan);
		map.addAttribute("personelPlan", personelPlanList);
		map.addAttribute("financePlan", expectedFinances);
		map.addAttribute("financeIncurredPlans", incurredFinances);
		map.addAttribute("personelIncurredPlans", personelIncurredPlanList);
		map.addAttribute("totalFinance", totalFinance);

		map.addAttribute("title", "Tổng hóa đơn cho chuyến công tác " + generalPlan.getName());

		return "tax-declaration";
	}

	
	@PostMapping("/payment/{planId}")
	public @ResponseBody void doPayment(@PathVariable("planId") int planId, @RequestBody Integer[] removedPlans) {
		// change status of plan
		GeneralPlan generalPlan = planRepo.findById(planId);
		generalPlan.setStatus("Đã hoàn thành");

		// convert array to list
		List<Integer> removedPlansList = Arrays.asList(removedPlans);

		List<FinanceIncurredPlan> acceptedPlansList = new ArrayList<>();

		// change status for finance plan
		// set false for removed plans and true for accepted plans
		List<FinanceIncurredPlan> financeIncurredPlans = incurredFinanceRepo
				.findByGeneralPlanId(generalPlan.getId());
		financeIncurredPlans.forEach(plan -> {
			if (removedPlansList.contains(plan.getId())) {
				plan.setConfirmed(false);
			} else {
				plan.setConfirmed(true);
				acceptedPlansList.add(plan);
			}

			incurredFinanceRepo.save(plan);
		});

		// create bill
		List<FinancePlan> financePlans = financePlanRepo.findByGeneralPlanId(generalPlan.getId());
		double totalCost = paymentService.calculateTotalFinance(financePlans, acceptedPlansList);
		generalPlan.setCost(totalCost);
		planRepo.save(generalPlan);
	}

}
