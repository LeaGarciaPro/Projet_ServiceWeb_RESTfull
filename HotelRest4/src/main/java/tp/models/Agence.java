package tp.models;

import java.util.ArrayList;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Agence {
	
	@Id
	private int identifiant;
	
	@Column(name="mdp")
	private String motDePasse;
	
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
		return Objects.hash(identifiant, motDePasse);
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
		return identifiant == other.identifiant && Objects.equals(motDePasse, other.motDePasse);
	}

	@Override
	public String toString() {
		return "Agence [identifiant=" + identifiant + ", motDePasse=" + motDePasse + "]";
	}
	
		
}
