package com.ijse.aad_75;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@Configuration - mark as a config class
//@ComponentScan - find all our Controllers and Services automatically
//@EnableAutoConfiguration - Spring automagically Configure dependencies
@SpringBootApplication
public class Aad75Application {

	public static void main(String[] args) {
        //run - server start
		SpringApplication.run(Aad75Application.class, args);
	}

}
