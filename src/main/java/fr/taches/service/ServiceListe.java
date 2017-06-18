package fr.taches.service;

import java.util.List;

import fr.taches.domain.Categorie;
import fr.taches.domain.Liste;
import fr.taches.domain.Note;
import fr.taches.domain.Tache;
import fr.taches.domain.TypeTache;

public interface ServiceListe {


	List<Liste> listListe();
	void createListe(Liste newListe);
	Liste findById(Long idListe);	
	void deleteListe(Long idListe);
	public void updateListe(Liste liste, Long idListe);


	List<Note> listNote();
	List<Note> getAllNotes(Long idListe);
	List<Note> getAllElements(Long idListe);
	void createNote(long idListe, Note newNote);
	Note findNoteById(Long idNote);
	void updateNote(Note note, Long idNote);
	void deleteNote(Long idNote);


	List<Tache> listTache();
	List<Tache> getAllTaches(Long idListe);
	void createTache(long idListe, Tache newTache);
	Tache findTacheById(Long idTache);
	void updateTache(Tache tache, Long idTache);
	void deleteTache(Long idTache);

	List<TypeTache> listTypeTache();
	void createTypeTache(TypeTache newTypeTache);
	TypeTache findTypeTacheById(Long idTypeTache);
	void updateTypeTache(TypeTache typeTache, Long idTypeTache);
	void deleteTypeTache(Long idTypeTache);
	Categorie[] listCategories();

}
