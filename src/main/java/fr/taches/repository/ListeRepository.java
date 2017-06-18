package fr.taches.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import fr.taches.domain.Liste;

@Component
public interface ListeRepository extends JpaRepository<Liste, Long> {
	Liste findByNom(String nom);

	Liste findById(Long id);


}
