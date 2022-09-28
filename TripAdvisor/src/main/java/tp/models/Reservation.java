package tp.models;

import java.util.Objects;

public class Reservation {

	private int idReservation;
	private String dateArrivee;
	private String dateDepart;
	private int idClient;
	private int idChambre;
	
	Reservation(){}

	public Reservation(String dateArrivee, String dateDepart, int idClient, int idChambre) {
		super();
		this.dateArrivee = dateArrivee;
		this.dateDepart = dateDepart;
		this.idClient = idClient;
		this.idChambre = idChambre;
	}

	public int getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public String getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(String dateArrivee) {
		this.dateArrivee = dateArrivee;
	}

	public String getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(String dateDepart) {
		this.dateDepart = dateDepart;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdChambre() {
		return idChambre;
	}

	public void setIdChambre(int idChambre) {
		this.idChambre = idChambre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateArrivee, dateDepart, idChambre, idClient, idReservation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		return Objects.equals(dateArrivee, other.dateArrivee) && Objects.equals(dateDepart, other.dateDepart)
				&& idChambre == other.idChambre && idClient == other.idClient && idReservation == other.idReservation;
	}

	@Override
	public String toString() {
		return "Reservation [idReservation=" + idReservation + ", dateArrivee=" + dateArrivee + ", dateDepart="
				+ dateDepart + ", idClient=" + idClient + ", idChambre=" + idChambre + "]";
	}
	
	

}

