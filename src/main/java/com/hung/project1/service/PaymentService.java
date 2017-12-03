package com.hung.project1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hung.project1.entity.FinanceIncurredPlan;
import com.hung.project1.entity.FinancePlan;

@Service
public class PaymentService {

	public double calculateTotalFinance(List<FinancePlan> financePlans,
			List<FinanceIncurredPlan> financeIncurredPlans) {
		
		double expectedCost = calculateExpectedCost(financePlans),
			incurredCost = calculateIncurredCost(financeIncurredPlans);

		return expectedCost + incurredCost;
	}
	
	public double calculateIncurredCost(List<FinanceIncurredPlan> financeIncurredPlans) {
		if (financeIncurredPlans.isEmpty() || financeIncurredPlans == null) {
			return 0;
		}
		
		double totalIncurredFinanceCost = 0;
		for (int i = 0; i < financeIncurredPlans.size(); i++) {
			totalIncurredFinanceCost += financeIncurredPlans.get(i).getCost()
					* (1 + financeIncurredPlans.get(i).getTax() / 100);
		}

		return totalIncurredFinanceCost;
	}
	
	public double calculateExpectedCost(List<FinancePlan> financePlans) {
		if (financePlans.isEmpty() || financePlans == null) {
			return 0;
		}
		
		double totalFinancePlanCost = 0;
		for (int i = 0; i < financePlans.size(); i++) {
			totalFinancePlanCost += financePlans.get(i).getCost() 
					* (1 + financePlans.get(i).getTax() / 100);
		}

		return totalFinancePlanCost;
	}
}
