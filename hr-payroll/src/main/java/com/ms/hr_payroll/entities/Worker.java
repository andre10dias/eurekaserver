package com.ms.hr_payroll.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Worker implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Double dailyIncome;
	
}
