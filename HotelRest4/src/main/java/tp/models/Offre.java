package tp.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Offre {
	
	@Id
	@GeneratedValue
	private int idOffre;
	
	@Column(name="dateDebut")
	private String dateDebut;
	
	@Column(name="dateFin")
	private String dateFin;
	
	@Column(name="prix")
	private float prix;

	@Column(name="chambre")
	private int idChambre;
	
	@Column(name="hotel")
	private int idHotel;
	
	@Column(name="client")
	private int idClient;
	
	Offre(){}

	public Offre(String dateDebut, String dateFin, float prix, int idChambre, int idHotel) {
		super();
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
		return Objects.hash(dateDebut, dateFin, idChambre, idClient, idHotel, idOffre, prix);
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
				&& idChambre == other.idChambre && idClient == other.idClient && idHotel == other.idHotel
				&& idOffre == other.idOffre && Float.floatToIntBits(prix) == Float.floatToIntBits(other.prix);
	}

	@Override
	public String toString() {
		return "Offre [idOffre=" + idOffre + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", prix=" + prix
				+ ", idChambre=" + idChambre + ", idHotel=" + idHotel + ", idClient=" + idClient + "]";
	}
	
	
}
