package tp.models;

import java.util.ArrayList;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import tp.repositories.ReservationRepository;

@Entity
public class Chambre {
	
	@Id
	private int idChambre;
	
	@Column(name="prix")
	private float prix;
	
	@Column(name="places")
	private int places;
	
	@Column(name="hotel")
	private int idHotel;
	
	Chambre(){}

	public Chambre(int idChambre, float prix, int places, int idHotel) {
		super();
		this.idChambre = idChambre;
		this.prix = prix;
		this.places = places;
		this.idHotel = idHotel;
	}

	public int getIdChambre() {
		return idChambre;
	}

	public void setIdChambre(int idChambre) {
		this.idChambre = idChambre;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public int getPlaces() {
		return places;
	}

	public void setPlaces(int places) {
		this.places = places;
	}

	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idChambre, idHotel, places, prix);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chambre other = (Chambre) obj;
		return idChambre == other.idChambre && idHotel == other.idHotel && places == other.places
				&& Float.floatToIntBits(prix) == Float.floatToIntBits(other.prix);
	}

	@Override
	public String toString() {
		return "Chambre [idChambre=" + idChambre + ", prix=" + prix + ", places=" + places + ", idHotel=" + idHotel
				+ "]";
	}
	
	public boolean chambreLibre(String debut, String fin, ReservationRepository reservationRepository) {
		int d1 = Integer.parseInt(debut.replace("/", ""));
		int d2 = Integer.parseInt(fin.replace("/", ""));
		boolean libre = true;
		for(Reservation r : reservationRepository.findReservationDeChambre(idChambre)) {
			int d3 = Integer.parseInt(r.getDateArrivee().replace("/", ""));
			int d4 = Integer.parseInt(r.getDateDepart().replace("/", ""));
			if ((d1 < d3 && d2 > d3) || (d1>= d3 && d2 <= d4) || (d1 < d4) && (d2 > d4)) {
				libre = false;
			}
		}
		return libre;
	}
		
}
