package tp.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import tp.models.Agence;
import tp.models.Chambre;
import tp.models.Client;
import tp.models.Hotel;
import tp.models.Offre;
import tp.models.Reservation;

@Component
public class TripAdvisorRESTCLI extends AbstractMain implements CommandLineRunner {

	@Autowired
	private RestTemplate proxy;
	private IntegerInputProcessor inputProcessor;
		
	@Override
	public void run(String... args) throws Exception {
		BufferedReader inputReader;
		String userInput = "";
		
		try {
			inputReader = new BufferedReader(new InputStreamReader(System.in));
			do {
				menu();
				userInput = inputReader.readLine();
				processUserInput(inputReader, userInput, proxy);
				Thread.sleep(3000);
				
			} while(!userInput.equals(QUIT));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void menu() {
		StringBuilder builder = new StringBuilder();
		builder.append(QUIT+". Quit.");
		builder.append("\n1. Rechercher une chambre et comparer les offres des agences et hôtels");
		builder.append("\n2. Effectuer une réservation suite à votre recherche.");
		
		System.out.println(builder);
	}
	
	private void processUserInput(BufferedReader reader, String userInput, RestTemplate proxy) {
		try {
			
			String uriAgence1 = "http://localhost:8085";
			String uriAgence2 = "http://localhost:8086";
			String uriAgence3 = "http://localhost:8087";
			
			ArrayList<String> agencesConnues = new ArrayList<>();
			agencesConnues.add(uriAgence1);
			agencesConnues.add(uriAgence2);
			agencesConnues.add(uriAgence3);
			
			switch(userInput) {
				case "1":
					System.out.println("Vous avez indiqué vouloir rechercher une chambre dans un hôtel.");
					System.out.println("D'abord, nous avons besoin de vos critères pour chercher un hôtel qui correspondrait à vos attentes :");
					
					//ville
					System.out.println("Entrez une ville de séjour (Paris)");
					String ville = reader.readLine();
					
					//dateDebut
					System.out.println("Entrez une date d'arrivée (2021/10/16) : ");
					String dateDebut = reader.readLine().replace("/", "");
					
					//dateFin
					System.out.println("Entrez une date de départ (2021/10/20) : ");
					String dateFin = reader.readLine().replace("/", "");
					
					//prixMin
					System.out.println("Entrez un prix minimum (10) : ");
					inputProcessor = new IntegerInputProcessor(reader);
					float prixMin = (float) inputProcessor.process();
					System.out.println();
					
					//prixMax
					System.out.println("Entrez un prix maximum (150) : ");
					inputProcessor = new IntegerInputProcessor(reader);
					float prixMax = (float) inputProcessor.process();
					System.out.println();
					
					//nbEtoiles
					System.out.println("Entrez un nombre d'étoiles (3) : ");
					inputProcessor = new IntegerInputProcessor(reader);
					int nbEtoiles = inputProcessor.process();
					System.out.println();
					
					//nbLits
					System.out.println("Entrez un nombre de lits (2) : ");
					inputProcessor = new IntegerInputProcessor(reader);
					int nbLits = inputProcessor.process();
					System.out.println();
					
					System.out.println("Les offres disponibles selon vos demandes : \n");
					
					//Je parcours mes agences
					Offre[] offre;
					for(int i = 0; i < agencesConnues.size(); i++) {
					
						String uri = agencesConnues.get(i)+"/tripadvisor/rechercher/"+dateDebut+":"+dateFin+":"+prixMin+":"+prixMax+":"+nbLits+":"+ville+":"+nbEtoiles;
						offre = proxy.getForObject(uri, Offre[].class);						
						if(offre.length != 0) {
							
							//Je récupère l'agence : 
							String uri2 = agencesConnues.get(i)+"/agence/get/"+ (i+1);
							Agence agence = proxy.getForObject(uri2, Agence.class);
							System.out.println("L'agence numéro "+agence.getIdentifiant()+" propose les offres suivantes :");
							
							for(int j=0; j<offre.length; j++) {
								//Je récupère l'hôtel à chaque fois 
								String uri3 = agencesConnues.get(i)+"/agence/getHotel/"+offre[j].getIdHotel();
								Hotel hotelRecu = proxy.getForObject(uri3, Hotel.class);
								
								System.out.println(offre[j].toString(agence, hotelRecu));
								System.out.println();
								
							}
							
							System.out.println();
							System.out.println();
						}
					}
					
					break;
					
						
				case "2":
					System.out.println("Vous avez indiqué vouloir effectuer une réservation.");
					
					//nom
					System.out.println("Entrez votre nom : ");
					String nom = reader.readLine();
					System.out.println();
					
					//prenom
					System.out.println("Entrez votre prénom : ");
					String prenom = reader.readLine();
					System.out.println();
					
					//carteBancaire
					System.out.println("Entrez votre cartebancaire (XXXX) : ");
					inputProcessor = new IntegerInputProcessor(reader);
					int carteBancaire = inputProcessor.process();
					System.out.println();
					
					//agence
					System.out.println("Entrez le numéro de l'agence dans laquelle vous voulez réserver : ");
					inputProcessor = new IntegerInputProcessor(reader);
					int numAgence = inputProcessor.process();
					System.out.println();
					
					//hotel
					System.out.println("Entrez l'identifiant de l'hôtel dans lequel vous voulez réserver : ");
					inputProcessor = new IntegerInputProcessor(reader);
					int numHotel = inputProcessor.process();
					System.out.println();
					
					//offre
					System.out.println("Entrez l'identifiant de l'offre que vous voulez réserver : ");
					inputProcessor = new IntegerInputProcessor(reader);
					int numOffre = inputProcessor.process();
					System.out.println();
					
					//Création du client
					String uriClient = agencesConnues.get(numAgence-1)+"/agence/creationClient/"+numHotel;
					Client client1 = new Client(nom, prenom, carteBancaire);
					Client clientCree = proxy.postForObject(uriClient, client1, Client.class);
					
					//Récupération de l'offre
					String uriOffre = agencesConnues.get(numAgence-1)+"/agence/getoffre/"+numOffre+":"+numHotel;
					Offre offre1 = proxy.getForObject(uriOffre, Offre.class);
					
					//Création de la réservation
					Reservation reservation = new Reservation(offre1.getDateDebut(), offre1.getDateFin(), clientCree.getIdClient(), offre1.getIdChambre());
					
					String uri = agencesConnues.get(numAgence-1)+"/agence/reserverUneChambre/"+offre1.getIdHotel();
					Reservation refOffre = proxy.postForObject(uri, reservation, Reservation.class);	
					
					System.out.println("Félicitations votre réservation a pu être faite");
					System.out.println("Vous avez une réservation dans l'hôtel numéro "+offre1.getIdHotel()+" dans la chambre :"+offre1.getIdChambre());
					System.out.println("Votre numéro de réservation : "+refOffre.getIdReservation());
					break;
						
					
				case QUIT:
					System.out.println("Au revoir");
					return;
				
				default:
					System.err.println("Mauvaise entrée, veuillez recommencer");
					return;
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
