package com.flagship.startup;

import org.springframework.boot.Banner.Mode;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class App 
{
    public static void main(String[] args) throws Exception {
    	SpringApplication application = new SpringApplication(App.class);
    	application.setBannerMode(Mode.OFF);
    	application.run(args);
    }
    
    @Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("来看看 SpringBoot 默认为我们提供的 Bean：");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			Arrays.stream(beanNames).forEach(System.out::println);
		};
	}
}
