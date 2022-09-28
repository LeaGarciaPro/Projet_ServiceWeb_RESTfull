package tp.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {
		"tp.models"
		
})

@EnableJpaRepositories(basePackages = {
		"tp.repositories"
		
})

@SpringBootApplication(scanBasePackages = {
		"tp.data",
		"tp.controllers",
		"tp.client"
		
})
public class AgenceRestApplication1 {

	public static void main(String[] args) {
		//Cette méthode démarre le framework Spring
		SpringApplication.run(AgenceRestApplication1.class, args);
	}

}