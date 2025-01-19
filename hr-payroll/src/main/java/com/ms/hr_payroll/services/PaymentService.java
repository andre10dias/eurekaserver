package com.ms.hr_payroll.services;

import org.springframework.stereotype.Service;

import com.ms.hr_payroll.entities.Payment;

@Service
public class PaymentService {
	
	public Payment getPayment(Long workerId, Integer days) {
		return new Payment("Bob", 200.0, days);
	}
	
}
