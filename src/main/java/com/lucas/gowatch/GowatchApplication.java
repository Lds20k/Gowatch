package com.lucas.gowatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GowatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(GowatchApplication.class, args);
	}

	// Used to test mapping, save and get data from db
	// Remove after finished testes and deploy
	//@Bean
	//public ApplicationRunner applicationRunner(ChannelRepository channelRepository, VideoRepository videoRepository, RatingRepository ratingRepository){
	//	return args -> {
	//		Channel channel = new Channel("testeChannel", "teste2@email.com", "teste_pass", "teste about", "São Paulo/Brasil");
	//		channelRepository.save(channel);
	//
	//		Video video = new Video("teste", "teste", "teste", channel);
	//		videoRepository.save(video);
	//
	//		Rating rating = new Rating(videoRepository.findById(1L).get(), channelRepository.findById(1L).get(), "like");
	//		ratingRepository.save(rating);
	//
	//		channelRepository.insertSubiscribed(channelRepository.findById(1L).get(), channelRepository.findById(3L).get());
	//
	//		System.out.println(videoRepository.findById(1L));
	//		System.out.println(channelRepository.findById(1L));
	//		System.out.println(ratingRepository.findById(1L));
	//	};
	//}
}
