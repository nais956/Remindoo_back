package fr.taches.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

public class TypeTache {

	@Id
	@GeneratedValue
	private Long id;
	private String nom;

	@Enumerated(EnumType.STRING)
	private Categorie categorie;


	public Long getId() {
		return id;
	}
	public void setId(Long idTypeTache) {
		this.id = idTypeTache;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Categorie getCategorie(){
		return categorie;
	}

	public void setCategorie(Categorie categorie){
		this.categorie = categorie;
	}

}
