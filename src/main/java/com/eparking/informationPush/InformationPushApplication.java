package com.eparking.informationPush;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = {"com.eparking.informationPush.dao"})
public class InformationPushApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		//tomcat启动
		return application.sources(InformationPushApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(InformationPushApplication.class, args);
	}

}
