package com.digital.signage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication//(exclude = {HibernateJpaAutoConfiguration.class})
@EnableDiscoveryClient
//@EntityScan("com.digital.signage.models")
//@ComponentScan("com.digital.signage.*")
//@EnableJpaRepositories(basePackages = "com.digital.signage.repository")
@ComponentScan(basePackages = "com.digital.signage.*")
public class LocalServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalServerApplication.class, args);
	}

}
