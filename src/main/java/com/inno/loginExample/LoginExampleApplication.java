package com.inno.loginExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LoginExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginExampleApplication.class, args);
	}

}
