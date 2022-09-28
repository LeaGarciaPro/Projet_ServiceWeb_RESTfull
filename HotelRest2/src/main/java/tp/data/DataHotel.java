package tp.data;

import java.util.ArrayList;

import org.hibernate.mapping.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tp.models.Agence;
import tp.models.Chambre;
import tp.models.Client;
import tp.models.Hotel;
import tp.models.Partenaire;
import tp.models.Reservation;
import tp.repositories.AgenceRepository;
import tp.repositories.ChambreRepository;
import tp.repositories.ClientRepository;
import tp.repositories.HotelRepository;
import tp.repositories.PartenaireRepository;
import tp.repositories.ReservationRepository;

@Configuration
public class DataHotel {
	
	//retourne une fonction qui prend un String comme argument
	//on utilise donc une lambda
	
	@Bean
	public CommandLineRunner initDatabase(ChambreRepository repositoryC, ClientRepository repositoryCl, HotelRepository repositoryH, PartenaireRepository repositoryP, ReservationRepository repositoryR, AgenceRepository repositoryAg){
		return args -> {
			
			//Hôtel
			Hotel h1 = new Hotel(1, "Le plaza 2", "Paris", 3);
			repositoryH.save(h1);
					
			//Clients
			Client cl1 = new Client("Garcia", "Lea", 8888);
			Client cl2 = new Client("Desbos", "Marie-Lou", 1234);
			Client cl3 = new Client("Askin", "Semih", 3333);
			Client cl4 = new Client("Ferrie", "Yannick", 4444);
			repositoryCl.save(cl1);
			repositoryCl.save(cl2);
			repositoryCl.save(cl3);
			repositoryCl.save(cl4);
			
			//Chambres
			Chambre ch1 = new Chambre(1, 70, 2, 1);
			Chambre ch2 = new Chambre(2, 80, 2, 1);
			Chambre ch3 = new Chambre(3, 90, 2, 1);
			Chambre ch4 = new Chambre(4, 100, 4, 1);
			Chambre ch5 = new Chambre(5, 110, 2, 1);
			Chambre ch6 = new Chambre(6, 120, 4, 1);
			Chambre ch7 = new Chambre(7, 130, 4, 1);
			Chambre ch8 = new Chambre(8, 140, 3, 1);
			Chambre ch9 = new Chambre(9, 150, 2, 1);
			Chambre ch10 = new Chambre(10, 200, 6, 1);
			repositoryC.save(ch1);
			repositoryC.save(ch2);
			repositoryC.save(ch3);
			repositoryC.save(ch4);
			repositoryC.save(ch5);
			repositoryC.save(ch6);
			repositoryC.save(ch7);
			repositoryC.save(ch8);
			repositoryC.save(ch9);
			repositoryC.save(ch10);
			
			//Agences (partenaires seulement)
			Agence agence1 = new Agence(1, "agence1");
			repositoryAg.save(agence1);
			Agence agence2 = new Agence(2, "agence2");
			repositoryAg.save(agence2);
			Agence agence3 = new Agence(3, "agence3");
			repositoryAg.save(agence3);
			Agence agence4 = new Agence(3, "agence4");
			repositoryAg.save(agence4);
			
			//Partenaires
			Partenaire p1 = new Partenaire(20, 1, "agence1", 1);
			repositoryP.save(p1);
			Partenaire p2 = new Partenaire(10, 3, "agence3", 1);
			repositoryP.save(p2);
			
	
		};
		}
}
