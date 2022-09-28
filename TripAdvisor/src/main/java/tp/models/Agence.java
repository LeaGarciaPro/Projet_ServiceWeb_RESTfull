package tp.models;

import java.util.ArrayList;
import java.util.Objects;

public class Agence {
	
	private int identifiant;
	private String motDePasse;
	private ArrayList<String> ServiceWebHotelsConnus;
	
	public Agence(){}
	
	public Agence(int identifiant, String motDePasse) {
		super();
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

	@Override
	public int hashCode() {
		return Objects.hash(ServiceWebHotelsConnus, identifiant, motDePasse);
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
		return Objects.equals(ServiceWebHotelsConnus, other.ServiceWebHotelsConnus) && identifiant == other.identifiant
				&& Objects.equals(motDePasse, other.motDePasse);
	}

	@Override
	public String toString() {
		return "C'est l'agence numéro " + identifiant+".";
	}
	
	

	
	
}
