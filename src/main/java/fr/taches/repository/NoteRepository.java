package fr.taches.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

//import fr.taches.domain.Liste;
import fr.taches.domain.Note;

@Component
public interface NoteRepository extends JpaRepository<Note, Long> {
	Note findByNom(String nom);
	
	
	@Query("select n from Note n where n.liste.id=?1 and TYPE(n) = Note")
	List<Note> getNotebyListe(Long idListe);

	
	/*@Query("select n from Note n where n.liste.id=?1 and TYPE(n) = Note")
	List<Note> getNotebyListe(Long idListe);*/

	Note findById(Long idNote);

	//Renvoie tous les éléments liés à une liste (le parent Note et la classe fille Tache)
	@Query("select n from Note n where n.liste.id=?1")
	List<Note> getElementByListe(Long idListe);
	

}
