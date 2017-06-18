package fr.taches.domain;



import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Tache extends Note {

	private static final long serialVersionUID = 1L;



	public Tache() {
		super();
		// TODO Auto-generated constructor stub
	}

	@ManyToOne
	private TypeTache typeTache;



	public TypeTache getTypeTache() {
		return typeTache;
	}


	public void setTypeTache(TypeTache typeTache) {
		this.typeTache = typeTache;
	}











}
