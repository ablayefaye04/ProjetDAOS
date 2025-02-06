package uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Modele.Enseignant;

import java.util.Optional;

@Repository
public interface EnseignantRepository extends JpaRepository <Enseignant, Long> {
    Optional<Enseignant> findByUsername(String username); // Nouvelle méthode

}
