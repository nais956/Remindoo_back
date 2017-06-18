package fr.taches.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Note")
public class Note   implements Serializable {

	private static final long serialVersionUID = -4142036022065393602L;

	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	private String texte;


	@ManyToOne
	@JoinColumn(name="listeid")
	@JsonBackReference
	private Liste liste;





	public Note() {

	}


	protected Note(Builder note) {
		this.id = note.id;
		this.nom = note.nom;
		this.texte = note.texte;
	}



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
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}

	public Liste getListe() { 
		return liste; 
	}


	public static class Builder {
		private Long id;
		private String nom;
		private String texte;
		private Long idListe;
		private Liste liste;

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withNom(String nom) {
			this.nom = nom;
			return this;
		}

		public Builder withTexte(String texte) {
			this.texte = texte;
			return this;
		}

		public Builder withListe(Liste liste) {
			this.setListe(liste);
			return this;
		}

		public Note build() {
			return new Note(this);
		}

		public Liste getListe() {
			return liste;
		}

		public void setListe(Liste liste) {
			this.liste = liste;
		}

		public Long getIdListe() {
			return idListe;
		}

		public void setIdListe(Long idListe) {
			this.idListe = idListe;
		}
	}


	public void setListe(Liste liste) {
		this.liste = liste;

	}

}
