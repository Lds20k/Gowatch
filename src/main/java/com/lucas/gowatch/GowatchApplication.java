package com.lucas.gowatch;

import com.lucas.gowatch.config.StorageConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageConfiguration.class)
public class GowatchApplication {
	public static void main(String[] args) {
		SpringApplication.run(GowatchApplication.class, args);
	}
}
