package com.lucas.gowatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GowatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(GowatchApplication.class, args);
	}

	// Used to test mapping, save and get data from db
	// Remove after
	//@Bean
	//public ApplicationRunner applicationRunner(ChannelRepository channelRepository, VideoRepository videoRepository){
	//	return args -> {
	//		Channel channel = new Channel("testeChannel", "teste@email.com", "teste_pass", "teste about", "São Paulo/Brasil");
	//		channelRepository.save(channel);
	//
	//		Video video = new Video("teste", "teste", "teste", channel);
	//		videoRepository.save(video);
	//
	//		System.out.println(videoRepository.findById(Integer.toUnsignedLong(1)).get().getChannel());
	//		System.out.println(channelRepository.findById(Integer.toUnsignedLong(1)).get().getVideos());
	//	};
	//}
}
