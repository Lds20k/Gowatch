package com.lucas.gowatch;

import com.lucas.gowatch.database.mariadb.model.Channel;
import com.lucas.gowatch.database.mariadb.repository.ChannelRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class GowatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(GowatchApplication.class, args);
	}

	// Used to test mapping and save
	// Remove after
	//@Bean
	//public ApplicationRunner applicationRunner(ChannelRepository channelRepository){
	//	return args -> {
	//		Channel channel = new Channel();
	//		channel.setCreationDate(LocalDate.now());
	//		channel.setLocation("São Paulo/Brazil");
	//		channel.setPassword("123");
	//		channel.setUsername("Lucas");
	//		channel.setEmail("lucas@hotmail.com");
	//
	//		channelRepository.save(channel);
	//		System.out.println(channelRepository.findAll());
	//	};
	//}
}
