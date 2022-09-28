package tp.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import tp.models.Agence;
import tp.modelsServeur.Chambre;
import tp.modelsServeur.Client;
import tp.modelsServeur.Hotel;
import tp.modelsServeur.Offre;
import tp.modelsServeur.Reservation;
import tp.repositories.AgenceRepository;

@RestController
public class AgenceController {
	
	@Autowired
	private AgenceRepository agenceRepository;
	
	@Autowired
	private RestTemplate proxy;
	
	@GetMapping("/tripadvisor/rechercher/{dateDebut}:{dateFin}:{prixMin}:{prixMax}:{nbLits}:{adresseHotel}:{nbEtoiles}")
	public ArrayList<Offre> rechercher(
			@PathVariable String dateDebut, 
			@PathVariable String dateFin, 
			@PathVariable float prixMin, 
			@PathVariable float prixMax, 
			@PathVariable int nbLits,
			@PathVariable String adresseHotel,
			@PathVariable int nbEtoiles
			){
		
		Optional<Agence> agence = agenceRepository.findById(3);
		ArrayList<Offre> offres = agence.get().rechercherHotel(agence.get().getIdentifiant(), agence.get().getMotDePasse(), dateDebut, dateFin, prixMin, prixMax, nbLits, adresseHotel, nbEtoiles, proxy);
		
		return offres;
			
	}
	
	@GetMapping("/agence/get/{id}")
	public Optional<Agence> getAgenceById(@PathVariable int id) {
		return agenceRepository.findById(id);
	}
	
	@GetMapping("/agence/getHotel/{id}")
	public Hotel getHotelById(@PathVariable int id) {
		Optional<Agence> agence = agenceRepository.findById(3);
		Hotel hotel = agence.get().rechHotel(id, proxy);
		return hotel;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/agence/creationClient/{idHotel}")
	public Client createClient(@RequestBody Client client, @PathVariable int idHotel) {
		Optional<Agence> agence = agenceRepository.findById(3);
		Client clientRet = agence.get().creationClient(client, idHotel, proxy);
		return clientRet;
	}
	
	@GetMapping("/agence/getoffre/{idOffre}:{idHotel}")
	public Offre getOffreById(@PathVariable int idOffre, @PathVariable int idHotel) {
		Optional<Agence> agence = agenceRepository.findById(3);
		Offre offre = agence.get().getOffre(idOffre, idHotel, proxy);
		return offre;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/agence/reserverUneChambre/{idHotel}")
	public Reservation reserverUneChambre(@RequestBody Reservation reservation, @PathVariable int idHotel) {
		Optional<Agence> agence = agenceRepository.findById(3);
		Reservation res = agence.get().creationReservation(reservation, idHotel, proxy);
		return res;
	}

}
