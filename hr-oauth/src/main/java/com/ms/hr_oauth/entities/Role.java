package com.ms.hr_oauth.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
}
