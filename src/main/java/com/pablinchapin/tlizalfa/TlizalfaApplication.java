package com.pablinchapin.tlizalfa;

import com.pablinchapin.tlizalfa.configuration.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class TlizalfaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TlizalfaApplication.class, args);
	}

}
