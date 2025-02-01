package com.ms.hr_api_gateway_zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HrApiGatewayWebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrApiGatewayWebfluxApplication.class, args);
	}

}
