package fr.taches.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import fr.taches.domain.*;
import fr.taches.jms.Consumer;
import fr.taches.jms.Producer;
import fr.taches.repository.ListeRepository;
import fr.taches.repository.NoteRepository;
import fr.taches.repository.TacheRepository;
import fr.taches.repository.TypeTacheRepository;



@Service
public class ServiceListeImpl implements ServiceListe {

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private ListeRepository listeRepository;

	@Autowired
	private TacheRepository tacheRepository;

	@Autowired
	private TypeTacheRepository typeTacheRepository;

	@Autowired
	Consumer jmsConsumer;

	@Autowired
	Producer jmsProducer;

	@Async
	@Override
	public List<Note> listNote() {
		return noteRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "id")));  
	}

	@Async
	@Override
	public void createNote(long idListe, Note newNote) {
		Liste liste = findById(idListe);
		newNote.setListe(liste);
		noteRepository.save(newNote);
	}

	@Async
	@Override
	public List<Liste> listListe() {
		return listeRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "id")));
	}

	@Async
	@Override
	public void createListe(Liste newListe) {
		listeRepository.save(newListe);
	}

	@Override
	public List<Note> getAllNotes(Long idListe) {
		return noteRepository.getNotebyListe(idListe);
	}

	@Override
	public List<Note> getAllElements(Long idListe) {
		return noteRepository.getElementByListe(idListe);
	}

	public Liste findById(Long idListe){
		return listeRepository.findById(idListe);
	}


	public void updateListe(Liste listeUpdated, Long idListe) {
		Liste liste = listeRepository.findOne(idListe);
		liste.setNom(listeUpdated.getNom());
		listeRepository.save(liste);
	}

	@Override
	public void deleteListe(Long idListe) {
		List<Note> listeElements = getAllElements(idListe);
		for(Note note : listeElements){
			deleteNote(note.getId());
		}
		listeRepository.delete(idListe);
	}

	@Override
	public Note findNoteById(Long idNote) {
		return noteRepository.findById(idNote);
	}

	@Override
	public void updateNote(Note noteUpdated, Long idNote) {
		Note note = noteRepository.findOne(idNote);
		note.setNom(noteUpdated.getNom());
		note.setTexte(noteUpdated.getTexte());
		noteRepository.save(note);	
	}

	@Override
	public void deleteNote(Long idNote) {
		noteRepository.delete(idNote);
	}

	@Override
	public List<Tache> getAllTaches(Long idListe) {
		return tacheRepository.getTachebyListe(idListe);
	}


	@Override
	public Tache findTacheById(Long idTache) {
		return tacheRepository.findById(idTache);
	}

	@Override
	public void updateTache(Tache tacheUpdated, Long idTache) {		
		Tache tache = tacheRepository.findOne(idTache);
		tache.setNom(tacheUpdated.getNom());
		tache.setTexte(tacheUpdated.getTexte());
		tache.setTypeTache(tacheUpdated.getTypeTache());
		tacheRepository.save(tache);	
	}

	@Override
	public void deleteTache(Long idTache) {
		tacheRepository.delete(idTache);
	}

	@Override
	public List<Tache> listTache() {
		return tacheRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "id"))); 
	}

	@Override
	public void createTache(long idListe, Tache newTache) {
		Liste liste = findById(idListe);
		TypeTache typeTache = findTypeTacheById(newTache.getTypeTache().getId());
		newTache.setListe(liste);
		newTache.setTypeTache(typeTache);
		tacheRepository.save(newTache);
	}


	@Override
	public TypeTache findTypeTacheById(Long idTypeTache) {
		return typeTacheRepository.findById(idTypeTache);
	}

	@Override
	public void updateTypeTache(TypeTache typeTacheUpdated, Long idTypeTache) {
		TypeTache typeTache = typeTacheRepository.findOne(idTypeTache);
		typeTache.setNom(typeTacheUpdated.getNom());
		typeTacheRepository.save(typeTache);	
	}

	@Override
	public void deleteTypeTache(Long idTypeTache) {
		typeTacheRepository.delete(idTypeTache);
	}

	@Override
	public List<TypeTache> listTypeTache() {
		return typeTacheRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "id"))); 
	}

	@Override
	public void createTypeTache(TypeTache newTypeTache) {
		typeTacheRepository.save(newTypeTache);
	}

	@Override
	public Categorie[] listCategories() {
		return Categorie.values();
	}



}