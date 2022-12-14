package tp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tp.models.Chambre;
import tp.models.Client;
import tp.models.Hotel;
import tp.models.Offre;
import tp.models.Partenaire;
import tp.models.Reservation;
import tp.repositories.ChambreRepository;
import tp.repositories.ClientRepository;
import tp.repositories.HotelRepository;
import tp.repositories.OffreRepository;
import tp.repositories.PartenaireRepository;
import tp.repositories.ReservationRepository;

@RestController
public class HotelController {
	
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private PartenaireRepository partenaireRepository;
	@Autowired
	private ChambreRepository chambreRepository;
	@Autowired
	private OffreRepository offreRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private ClientRepository clientRepository;
	
	@GetMapping("/hotel/all")
	public List<Hotel> getHotels(){
		return hotelRepository.findAll();
	}
	
	@GetMapping("/hotel/get/{id}")
	public Optional<Hotel> getHotelById(@PathVariable int id) {
		return hotelRepository.findById(id);
	}
	
	@GetMapping("/chambre/get/{id}")
	public Optional<Chambre> getChambreById(@PathVariable int id) {
		return chambreRepository.findById(id);
	}
	
	@GetMapping("/hotel/rechercher/{idAgence}:{mdpAgence}:{dateDebut}:{dateFin}:{prixMin}:{prixMax}:{nbLits}:{adresseHotel}:{nbEtoiles}")
	public ArrayList<Offre> rechercher(
			@PathVariable int idAgence, 
			@PathVariable String mdpAgence,
			@PathVariable String dateDebut, 
			@PathVariable String dateFin, 
			@PathVariable float prixMin, 
			@PathVariable float prixMax, 
			@PathVariable int nbLits,
			@PathVariable String adresseHotel,
			@PathVariable int nbEtoiles
			){
		
		ArrayList<Offre> listeRetour = new ArrayList<>() ;
		
		//je r??cup??re l'h??tel auquel l'agence s'adresse dans la m??thode (un h??tel par service mais pas grave)
		Hotel h = hotelRepository.getById(3);
		
		if(h.getNbEtoiles() == nbEtoiles && h.getAdresseHotel().equals(adresseHotel)) {
		
		//je r??cup??re l'??ventuel pourcentage dont l'agence pourrait profiter
		float reduction = 0;
		for(Partenaire p : partenaireRepository.findAll()) {
			if(p.getIdAgence()== idAgence && p.getMdp().equals(mdpAgence)) {
				reduction = p.getPourcentage();
			}
		}
		
		//Compteur pour construire toutes les offres
		int cmpt = 1;
		//je r??cup??re chaque chambre de l'h??tel
		for(Chambre c : chambreRepository.findAll()) {
			if((c.getPrix() >= prixMin) && (c.getPrix() <= prixMax) && (c.getPlaces() == nbLits) && (c.chambreLibre(dateDebut,dateFin, reservationRepository))) {
				if(reduction != 0) {
					float reduc = c.getPrix() * (1 - (reduction/100));
					Offre offre = new Offre(dateDebut, dateFin, reduc, c.getIdChambre(), c.getIdHotel());
					offreRepository.save(offre);
					listeRetour.add(offre);
					cmpt++;
				}
				else {
					Offre offre = new Offre(dateDebut, dateFin, c.getPrix(), c.getIdChambre(), c.getIdHotel()); 
					offreRepository.save(offre);
					listeRetour.add(offre);
					cmpt++;
				}
			}
		}
		}
			
		return listeRetour;
		
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/client")
	public Client createClient(@RequestBody Client client) {
		return clientRepository.save(client);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/hotel/reserverUneChambre")
	public Reservation reserverUneChambre(@RequestBody Reservation reservation) {
		return reservationRepository.save(reservation);
	}
	
	@GetMapping("/hotel/getoffre/{id}")
	public Optional<Offre> getOffreById(@PathVariable int id) {
		return offreRepository.findById(id);
	}
	
	@GetMapping("/hotel/getpartenaire/{id}")
	public ArrayList<Partenaire> getPartenaireById(@PathVariable int id) {
		return partenaireRepository.findByIdAgence(id);
	}
}
