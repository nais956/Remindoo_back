package fr.taches.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity

public class Utilisateur {
	@Id
	@GeneratedValue
	private int id;

	@OneToMany(mappedBy = "utilisateur")
	private List<Liste> liste;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Liste> getListe() {
		return liste;
	}

	public void setListe(List<Liste> liste) {
		this.liste = liste;
	}




}
