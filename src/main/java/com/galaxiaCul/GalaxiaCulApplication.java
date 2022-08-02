package com.galaxiaCul;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.galaxiaCul.repository.*;
import com.galaxiaCul.model.*;
import com.galaxiaCul.service.GalaxiaCulServiceI;

@SpringBootApplication
public class GalaxiaCulApplication {

	private static final Logger logger = LoggerFactory.getLogger(GalaxiaCulApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(GalaxiaCulApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(PronosticoRepository pronosticoRepository) {
		return (args) -> {
			int sentidoHorario = 1;
			int sentidoAntihorario = -1;

			Planeta ferengi = new Planeta("Ferengi", 500, 1, sentidoHorario);
			Planeta betasoide = new Planeta("Betasoide", 2000, 3, sentidoHorario);
			Planeta vulcano = new Planeta("Vulcano", 1000, 5, sentidoAntihorario);

			GalaxiaCulServiceI galaxiaCulService = new GalaxiaCulServiceI();
			galaxiaCulService.init(ferengi, betasoide, vulcano, pronosticoRepository);

			logger.info("Los datos fueros cargados correctamente");

	}
}
