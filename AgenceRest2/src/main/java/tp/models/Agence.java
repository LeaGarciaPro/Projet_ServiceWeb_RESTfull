package tp.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.util.ArrayUtils;

import tp.modelsServeur.Chambre;
import tp.modelsServeur.Client;
import tp.modelsServeur.Hotel;
import tp.modelsServeur.Offre;
import tp.modelsServeur.Reservation;

@Entity
public class Agence {
	
	@Id
	private int identifiant;
	
	@Column(name="mdp")
	private String motDePasse;
	
	@Column(name = "HOTELSCONNUS")
	@ElementCollection
	private List<String> hotelsConnus;
	
	public Agence(){}

	public Agence(int identifiant, String motDePasse, List<String> hotelsConnus) {
		super();
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
		this.hotelsConnus = hotelsConnus;
	}

	public Agence(int identifiant, String motDePasse) {
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
	}

	public int getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public List<String> getHotelsConnus() {
		return hotelsConnus;
	}

	public void setHotelsConnus(List<String> hotelsConnus) {
		this.hotelsConnus = hotelsConnus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hotelsConnus, identifiant, motDePasse);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agence other = (Agence) obj;
		return Objects.equals(hotelsConnus, other.hotelsConnus) && identifiant == other.identifiant
				&& Objects.equals(motDePasse, other.motDePasse);
	}

	@Override
	public String toString() {
		return "Agence [identifiant=" + identifiant + ", motDePasse=" + motDePasse + ", hotelsConnus=" + hotelsConnus
				+ "]";
	}
	
	public ArrayList<Offre> rechercherHotel(int idAgence, String mdpAgence, String dateDebut, 
			String dateFin, float prixMin, float prixMax, int nbLits, String adresseHotel, int nbEtoiles, RestTemplate proxyAgence){
		
		ArrayList<Offre> offresFinales = new ArrayList<>();
		Offre[] offre = null;
		for(int i = 0; i < hotelsConnus.size(); i++) {
		
			String uri = hotelsConnus.get(i)+"/hotel/rechercher/"+getIdentifiant()+":"+getMotDePasse()+":"+dateDebut+":"+dateFin+":"+prixMin+":"+prixMax+":"+nbLits+":"+adresseHotel+":"+nbEtoiles;
			System.out.println(uri);
			offre = proxyAgence.getForObject(uri, Offre[].class);	
			offresFinales.addAll(Arrays.asList(offre));
		}
		
		return offresFinales;
	}
	
	public Hotel rechHotel(int idHotel, RestTemplate proxy){
		
		String uri = hotelsConnus.get(idHotel)+"/hotel/get/"+idHotel;
		Hotel hotel = proxy.getForObject(uri, Hotel.class);	
		
		return hotel;
	}
	
public Client creationClient(Client client, int idHotel, RestTemplate proxy){
		
		String uri = hotelsConnus.get(idHotel)+"/client";
		Client clientRet = proxy.postForObject(uri, client, Client.class);	
		
		return clientRet;
		
	}
	
	public Offre getOffre(int numOffre, int numHotel, RestTemplate proxy){
			
			String uri = hotelsConnus.get(numHotel)+"/hotel/getoffre/"+numOffre;
			Offre of = proxy.getForObject(uri, Offre.class);	
			
			return of;
			
	}
	
	public Reservation creationReservation(Reservation reservation,  int numHotel, RestTemplate proxy){
		
		String uri = hotelsConnus.get(numHotel)+"/hotel/reserverUneChambre";
		Reservation res = proxy.postForObject(uri, reservation, Reservation.class);	
		
		return res;

}
	
	
	
	
	
}
