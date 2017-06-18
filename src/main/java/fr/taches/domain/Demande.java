package fr.taches.domain;

import java.io.Serializable;

public class Demande implements Serializable {

	private static final long serialVersionUID = 7950227209188487776L;
	private String typeDemande;
	private long id;
	private Object contenu;
	private long numero;
	private Object reponse;
	private boolean disponible = false;


	public Demande(String typeDemande) {
		super();
		this.typeDemande = typeDemande;
	}

	public Demande(String typeDemande, long id) {
		super();
		this.typeDemande = typeDemande;
		this.id = id;
	}

	public Demande(String typeDemande, long id, Object contenu) {
		super();
		this.typeDemande = typeDemande;
		this.id = id;
		this.contenu = contenu;
	}

	public String getTypeDemande() {
		return typeDemande;
	}

	public void setTypeDemande(String typeDemande) {
		this.typeDemande = typeDemande;
	}

	public Object getContenu() {
		return contenu;
	}

	public void setContenu(Object contenu) {
		this.contenu = contenu;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isDisponible() {
		return (disponible);
	}

	public void setDisponible() {
		disponible = true;
	}

	public Object getReponse() {
		return reponse;
	}

	public void setReponse(Object reponse) {
		this.reponse = reponse;
		this.contenu = null;
	}

}
