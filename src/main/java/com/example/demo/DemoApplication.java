package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@EnableCaching
@EnableAsync
@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class})
@SpringBootApplication
@ComponentScan
@Controller
public class DemoApplication {
	@RequestMapping("/")
	public String index(Model model){
		return "index";
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
