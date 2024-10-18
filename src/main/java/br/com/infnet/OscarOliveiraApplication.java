package br.com.infnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OscarOliveiraApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(OscarOliveiraApplication.class, args);
		
	}

}
