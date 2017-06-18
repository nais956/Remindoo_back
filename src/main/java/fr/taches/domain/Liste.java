package fr.taches.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

//import fr.taches.domain.Note.Builder;

@Entity

public class Liste {

	@Id
	@GeneratedValue
	private Long  id;

	private String nom;

	@ManyToOne
	private Utilisateur utilisateur;


	public Liste() {

	}


	protected Liste(Builder liste) {
		this.id = liste.id;
		this.nom = liste.nom;
	}



	@OneToMany(mappedBy = "liste")
	private List<Note> note = new ArrayList<Note>();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public List<Note> getNote() {
		return note;
	}
	public void setNote(List<Note> note) {
		this.note = note;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public static class Builder {
		private Long id;
		private String nom;
		private Utilisateur utilisateur;

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withNom(String nom) {
			this.nom = nom;
			return this;
		}

		public Builder withUtilisateur(Utilisateur utilisateur) {
			this.setUtilisateur(utilisateur);
			return this;
		}



		public Liste build() {
			return new Liste(this);
		}

		public Utilisateur getUtilisateur() {
			return utilisateur;
		}

		public void setUtilisateur(Utilisateur utilisateur) {
			this.utilisateur = utilisateur;
		}


	}
}
