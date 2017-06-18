package fr.taches.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;


import fr.taches.domain.Tache;


@Component
public interface TacheRepository extends JpaRepository<Tache, Long> {
	Tache findByNom(String nom);


	@Query("select t from Tache t where t.liste.id=?1")
	List<Tache> getTachebyListe(Long idListe);

	Tache findById(Long idTache);
}
