package com.nasa.robot.mars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Rafael Ghezzi Nakamoto
**/

@SpringBootApplication
@ComponentScan(basePackages={"com.nasa.robot.controller","com.nasa.robot.service"})
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
