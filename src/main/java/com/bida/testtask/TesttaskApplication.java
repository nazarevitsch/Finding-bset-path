package com.bida.testtask;

import com.bida.testtask.service.CalculatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TesttaskApplication implements CommandLineRunner {

	@Autowired
	private CalculatingService calculatingService;

	public static void main(String[] args) {
		SpringApplication.run(TesttaskApplication.class, args);
	}

	@Override
	public void run(String[] args) {
		System.out.println(calculatingService.calculate());
	}
}