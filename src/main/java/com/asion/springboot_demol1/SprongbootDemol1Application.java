package com.asion.springboot_demol1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.asion.springboot_demol1.mappering")
@ComponentScan(basePackages = {"com.asion.*"})
public class SprongbootDemol1Application {

	public static void main(String[] args) {
		SpringApplication.run(SprongbootDemol1Application.class, args);
	}

}
