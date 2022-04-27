package com.example.RickAndMorty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RickAndMortyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RickAndMortyApplication.class, args);
	}
	//TODO in the model package: there should be a model class for every entity (character, season, episode)
	//The classes in the model package could be further packaged (e.g. Episodes package)
}
