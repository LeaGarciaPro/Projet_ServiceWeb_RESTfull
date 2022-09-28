package tp.modelsServeur;

import java.util.Objects;

public class Partenaire {

	private int idPartenaire;
	private float pourcentage;
	private int idAgence;
	private int idHotel;
	
	Partenaire(){}

	public Partenaire(int idPartenaire, float pourcentage, int idAgence, int idHotel) {
		super();
		this.idPartenaire = idPartenaire;
		this.pourcentage = pourcentage;
		this.idAgence = idAgence;
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

	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idAgence, idHotel, idPartenaire, pourcentage);
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
				&& Float.floatToIntBits(pourcentage) == Float.floatToIntBits(other.pourcentage);
	}

	@Override
	public String toString() {
		return "Partenaire [idPartenaire=" + idPartenaire + ", pourcentage=" + pourcentage + ", idAgence=" + idAgence
				+ ", idHotel=" + idHotel + "]";
	}
	
	
	
}
