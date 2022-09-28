package tp.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Partenaire {

	@Id
	@GeneratedValue
	private int idPartenaire;
	
	@Column(name="pourcentage")
	private float pourcentage;

	@Column(name="IDAGENCE")
	private int idAgence;
	
	@Column(name="mdp")
	private String mdp;
	
	@Column(name="hotel")
	private int idHotel;
	
	Partenaire(){}

	public Partenaire(float pourcentage, int idAgence, String mdp, int idHotel) {
		super();
		this.pourcentage = pourcentage;
		this.idAgence = idAgence;
		this.mdp = mdp;
		this.idHotel = idHotel;
	}

	public int getIdPartenaire() {
		return idPartenaire;
	}

	public void setIdPartenaire(int idPartenaire) {
		this.idPartenaire = idPartenaire;
	}

	public float getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(float pourcentage) {
		this.pourcentage = pourcentage;
	}

	public int getIdAgence() {
		return idAgence;
	}

	public void setIdAgence(int idAgence) {
		this.idAgence = idAgence;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idAgence, idHotel, idPartenaire, mdp, pourcentage);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partenaire other = (Partenaire) obj;
		return idAgence == other.idAgence && idHotel == other.idHotel && idPartenaire == other.idPartenaire
				&& Objects.equals(mdp, other.mdp)
				&& Float.floatToIntBits(pourcentage) == Float.floatToIntBits(other.pourcentage);
	}

	@Override
	public String toString() {
		return "Partenaire [idPartenaire=" + idPartenaire + ", pourcentage=" + pourcentage + ", idAgence=" + idAgence
				+ ", mdp=" + mdp + ", idHotel=" + idHotel + "]";
	}
	
	

	
}
