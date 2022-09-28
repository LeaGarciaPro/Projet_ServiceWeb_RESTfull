package tp.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"tp.models",
		"tp.modelsServeur",
		"tp.cli",
		"tp.client",
		"tp.controllers"
		
})
public class TripAdvisorRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripAdvisorRestApplication.class, args);
	}

}
