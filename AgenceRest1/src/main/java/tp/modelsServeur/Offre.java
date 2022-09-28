package tp.modelsServeur;

import java.util.Objects;

public class Offre {
	
	private int idOffre;
	private static int cmpt = 0;
	private String dateDebut;
	private String dateFin;
	private float prix;
	private int idChambre;
	private int idHotel;
	
	Offre(){}

	public Offre(int idOffre, String dateDebut, String dateFin, float prix, int idChambre, int idHotel) {
		super();
		this.idOffre = idOffre;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.prix = prix;
		this.idChambre = idChambre;
		this.idHotel = idHotel;
	}

	public int getIdOffre() {
		return idOffre;
	}

	public void setIdOffre(int idOffre) {
		this.idOffre = idOffre;
	}

	public static int getCmpt() {
		return cmpt;
	}

	public static void setCmpt(int cmpt) {
		Offre.cmpt = cmpt;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public int getIdChambre() {
		return idChambre;
	}

	public void setIdChambre(int idChambre) {
		this.idChambre = idChambre;
	}

	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateDebut, dateFin, idChambre, idHotel, idOffre, prix);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offre other = (Offre) obj;
		return Objects.equals(dateDebut, other.dateDebut) && Objects.equals(dateFin, other.dateFin)
				&& idChambre == other.idChambre && idHotel == other.idHotel && idOffre == other.idOffre
				&& Float.floatToIntBits(prix) == Float.floatToIntBits(other.prix);
	}

	@Override
	public String toString() {
		return "Offre [idOffre=" + idOffre + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", prix=" + prix
				+ ", idChambre=" + idChambre + ", idHotel=" + idHotel + "]";
	}
	
	
	
}
