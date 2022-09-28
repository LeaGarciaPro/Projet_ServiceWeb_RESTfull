package tp.data;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tp.models.Agence;
import tp.repositories.AgenceRepository;

@Configuration
public class DataAgence {
	
	@Bean
	public CommandLineRunner initDatabase(AgenceRepository agenceRepository) {
		
		return args -> {
			List<String> hotelsConnus = new ArrayList<>();
			String uriHotel1 = "http://localhost:8081";
			String uriHotel2 = "http://localhost:8082";
			String uriHotel3 = "http://localhost:8083";
			String uriHotel4 = "http://localhost:8084";
			hotelsConnus.add(uriHotel1);
			hotelsConnus.add(uriHotel2);
			hotelsConnus.add(uriHotel3);
			hotelsConnus.add(uriHotel4);
			Agence agence = new Agence(2, "agence2", hotelsConnus);
			agenceRepository.save(agence);	
		};
		
	}

}