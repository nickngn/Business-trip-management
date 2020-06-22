package com.projects.business_trip_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projects.business_trip_management.entity.FinanceIncurredPlan;
import com.projects.business_trip_management.entity.FinancePlan;

@Service
public class PaymentService {

	public double calculateTotalFinance(List<FinancePlan> financePlans,
			List<FinanceIncurredPlan> financeIncurredPlans) {
		
		double expectedCost = calculateExpectedCost(financePlans),
			incurredCost = calculateIncurredCost(financeIncurredPlans);

		return expectedCost + incurredCost;
	}
	
	public double calculateIncurredCost(List<FinanceIncurredPlan> plans) {
		if (plans.isEmpty() || plans == null) {
			return 0;
		}
		
		double totalCost = 0;
		for (int i = 0; i < plans.size(); i++) {
			if (plans.get(i).isOn_Going()) {
				totalCost += plans.get(i).getCost()
						* (1 + plans.get(i).getTax() / 100);
			}
		}

		return totalCost;
	}
	
	public double calculateExpectedCost(List<FinancePlan> financePlans) {
		if (financePlans.isEmpty() || financePlans == null) {
			return 0;
		}
		
		double totalCost = 0;
		for (int i = 0; i < financePlans.size(); i++) {
			totalCost += financePlans.get(i).getCost() 
					* (1 + financePlans.get(i).getTax() / 100);
		}

		return totalCost;
	}
}
