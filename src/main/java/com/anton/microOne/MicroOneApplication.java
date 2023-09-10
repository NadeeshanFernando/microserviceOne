package com.anton.microOne;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients
public class MicroOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroOneApplication.class, args);
	}

	@GetMapping("/microOne")
	public String getMessage(){
		return "Welcome MicroOne Service!";
	}

}
