package uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Modele.Permanent;

import java.util.Optional;

@Repository
public interface PermanentRepository extends JpaRepository<Permanent, Long> {
    @Query("SELECT p FROM Permanent p WHERE p.matricule = ?1")
    Optional findByMatricule(String Matricule);
}
