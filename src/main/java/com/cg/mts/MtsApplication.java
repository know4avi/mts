package com.cg.mts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MtsApplication {

	public static void main(String[] args) {
		System.out.println("Start");
		SpringApplication.run(MtsApplication.class, args);
		System.out.println("End");
	}

}
