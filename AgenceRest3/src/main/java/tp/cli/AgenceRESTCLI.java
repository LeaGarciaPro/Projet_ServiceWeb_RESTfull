package tp.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import tp.models.Agence;
import tp.modelsServeur.Reservation;
import tp.modelsServeur.Client;
import tp.modelsServeur.Hotel;
import tp.modelsServeur.Offre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AgenceRESTCLI extends AbstractMain implements CommandLineRunner {

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
		builder.append("\n1. Rechercher une chambre dans un de nos hôtels.");
		builder.append("\n2. Effectuer une réservation.");
		
		System.out.println(builder);
	}
	
	private void processUserInput(BufferedReader reader, String userInput, RestTemplate proxy) {
		try {
			
			String uriHotel1 = "http://localhost:8081";
			String uriHotel2 = "http://localhost:8082";
			String uriHotel3 = "http://localhost:8083";
			String uriHotel4 = "http://localhost:8084";
			
			ArrayList<String> hotelsConnus = new ArrayList<>();
			hotelsConnus.add(uriHotel1);
			hotelsConnus.add(uriHotel2);
			hotelsConnus.add(uriHotel3);
			hotelsConnus.add(uriHotel4);
			Agence agenceProjet1 = new Agence(1, "agence1");
			
			switch(userInput) {
				case "1":
					System.out.println("Vous avez indiqué vouloir rechercher une chambre dans un de nos hôtels.");
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
					
					//Je vais chercher dans tous les hôtels connus par l'agence
					//On appelle la méthode rechercherChambre pour chacun
					//On obtient des listes d'offres qu'il faut exposer au client pour qu'il puisse réserver
					//Il choisit hôtel puis choisit offre dans cet hôtel
					//Il doit passer par le case 2 pour faire ça
					//Attention aux ArrayList					
					
					System.out.println("Les offres disponibles selon vos demandes : \n");
					
					//Je parcours ma liste d'hôtels connus (pas forcément partenaires)
					//Si ils renvoient une offre au moins, je les affiche
					for(int i = 0; i < hotelsConnus.size(); i++) {
					
					String uri = hotelsConnus.get(i)+"/hotel/rechercher/"+agenceProjet1.getIdentifiant()+":"+agenceProjet1.getMotDePasse()+":"+dateDebut+":"+dateFin+":"+prixMin+":"+prixMax+":"+nbLits+":"+ville+":"+nbEtoiles;
					Offre[] offre = proxy.getForObject(uri, Offre[].class);	
					if(offre.length != 0) {
						
						//Je récupère l'hôtel i pour l'afficher
						String uriHotel = hotelsConnus.get(i) + "/hotel/get/" + (i);
						Hotel hotelRet = proxy.getForObject(uriHotel, Hotel.class);
						System.out.print(hotelRet.toString());
						System.out.println(" propose les offres suivantes : ");
						Arrays.asList(offre).forEach(System.out::println);
						System.out.println();
						
					}					
					}
					
					break;
					
						
				case "2":
					//String uri3 = URI_HOTELS_ID+"/reserverUneChambre/{idAgence}:{mdpAgence}:{identifiantOffre}:{nom}:{prenom}:{carteBancaire}";
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
					
					//hotel
					System.out.println("Entrez l'identifiant de l'hôtel dans lequel vous voulez réserver : ");
					inputProcessor = new IntegerInputProcessor(reader);
					int numHotel = inputProcessor.process();
					System.out.println();
					
					//hotel
					System.out.println("Entrez l'identifiant de l'offre que vous voulez réserver : ");
					inputProcessor = new IntegerInputProcessor(reader);
					int numOffre = inputProcessor.process();
					System.out.println();
					
					//Création du client
					String uriClient = hotelsConnus.get(numHotel)+"/client";
					Client client1 = new Client(nom, prenom, carteBancaire);
					Client clientCree = proxy.postForObject(uriClient,client1, Client.class);
					
					//Récupération de l'offre
					String uriOffre = hotelsConnus.get(numHotel)+"/hotel/getoffre/"+numOffre;
					Offre offre = proxy.getForObject(uriOffre, Offre.class);
					
					//Création de la réservation
					Reservation reservation = new Reservation(offre.getDateDebut(), offre.getDateFin(), clientCree.getIdClient(), offre.getIdChambre());
					
					String uri = hotelsConnus.get(numHotel)+"/hotel/reserverUneChambre";
					Reservation refOffre = proxy.postForObject(uri, reservation, Reservation.class);	
					
					System.out.println(refOffre);
					
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
