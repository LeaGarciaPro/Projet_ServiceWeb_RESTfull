package tp.modelsServeur;

import java.util.ArrayList;
import java.util.Objects;

public class Chambre {
	
	private int idChambre;
	private float prix;
	private int places;
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
		
}
