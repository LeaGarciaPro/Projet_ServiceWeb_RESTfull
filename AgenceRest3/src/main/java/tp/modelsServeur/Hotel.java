package tp.modelsServeur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hotel {
	
	private int idHotel;
	private String nomHotel;
	private String adresseHotel;
	private int nbEtoiles;
	
	Hotel(){}

	public Hotel(int idHotel, String nomHotel, String adresseHotel, int nbEtoiles) {
		super();
		this.idHotel = idHotel;
		this.nomHotel = nomHotel;
		this.adresseHotel = adresseHotel;
		this.nbEtoiles = nbEtoiles;
	}

	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	public String getNomHotel() {
		return nomHotel;
	}

	public void setNomHotel(String nomHotel) {
		this.nomHotel = nomHotel;
	}

	public String getAdresseHotel() {
		return adresseHotel;
	}

	public void setAdresseHotel(String adresseHotel) {
		this.adresseHotel = adresseHotel;
	}

	public int getNbEtoiles() {
		return nbEtoiles;
	}

	public void setNbEtoiles(int nbEtoiles) {
		this.nbEtoiles = nbEtoiles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adresseHotel, idHotel, nbEtoiles, nomHotel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hotel other = (Hotel) obj;
		return Objects.equals(adresseHotel, other.adresseHotel) && idHotel == other.idHotel
				&& nbEtoiles == other.nbEtoiles && Objects.equals(nomHotel, other.nomHotel);
	}

	@Override
	public String toString() {
		return "L'hotel numéro "+idHotel+" : [nomHotel=" + nomHotel + ", adresseHotel=" + adresseHotel
				+ ", nbEtoiles=" + nbEtoiles + "]";
	}
	
	
}
