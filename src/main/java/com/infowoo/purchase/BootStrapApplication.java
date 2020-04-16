package com.infowoo.purchase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:application.properties"})
@MapperScan("com.infowoo.purchase.mapper")
@SpringBootApplication
public class BootStrapApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootStrapApplication.class, args);
	}
}
