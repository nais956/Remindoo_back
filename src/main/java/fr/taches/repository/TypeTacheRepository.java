package fr.taches.repository;


import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

//import fr.taches.domain.Note;
//import fr.taches.domain.Tache;
import fr.taches.domain.TypeTache;

@Component
public interface TypeTacheRepository extends JpaRepository<TypeTache, Long> {
	TypeTache findByNom(String nom);
	
	
	
	TypeTache findById(Long idTypeTache);
}
