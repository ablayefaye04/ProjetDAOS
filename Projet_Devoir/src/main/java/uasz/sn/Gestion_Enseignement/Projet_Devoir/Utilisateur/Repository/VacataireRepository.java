package uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Modele.Vacataire;

@Repository
public interface VacataireRepository extends JpaRepository<Vacataire, Long> {
}
