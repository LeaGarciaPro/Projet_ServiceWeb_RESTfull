package tp.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Client {

	
	@Id
	@GeneratedValue
	private int idClient;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="carteBancaire")
	private int carteBancaire;

	public Client() {}

	public Client(String nom, String prenom, int carteBancaire) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.carteBancaire = carteBancaire;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getCarteBancaire() {
		return carteBancaire;
	}

	public void setCarteBancaire(int carteBancaire) {
		this.carteBancaire = carteBancaire;
	}

	@Override
	public int hashCode() {
		return Objects.hash(carteBancaire, idClient, nom, prenom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return carteBancaire == other.carteBancaire && idClient == other.idClient && Objects.equals(nom, other.nom)
				&& Objects.equals(prenom, other.prenom);
	}

	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", nom=" + nom + ", prenom=" + prenom + ", carteBancaire="
				+ carteBancaire + "]";
	}
	
	
}
