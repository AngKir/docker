package com.kang.ypoth.indexservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableBinding(Source.class)
public class IndexServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndexServiceApplication.class, args);
	}

}
