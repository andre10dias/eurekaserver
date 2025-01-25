package com.ms.hr_payroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.hr_payroll.entities.Payment;
import com.ms.hr_payroll.entities.Worker;
import com.ms.hr_payroll.feignclients.WorkerFeignClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class PaymentService {
	
	@Autowired
	private WorkerFeignClient workerFeignClient;

	@CircuitBreaker(name = "paymentService", fallbackMethod = "getPaymentFallback") // Chama um método alternativo em caso de falha
	public Payment getPayment(Long workerId, Integer days) {
//		int x = 1;
//		if (x == 1) {
//			throw new IllegalArgumentException("Teste"); // Simulação de falha
//		}

		Worker worker = workerFeignClient.findById(workerId).getBody();
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}

	// Método alternativo (fallback)
	public Payment getPaymentFallback(Long workerId, Integer days, Throwable throwable) {
		// Pode usar 'throwable' para logar ou tomar ações adicionais, se necessário
		return new Payment("Fallback Worker", 400.0, days);
	}
}
