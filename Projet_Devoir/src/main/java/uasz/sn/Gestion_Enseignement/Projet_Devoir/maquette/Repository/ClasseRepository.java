package uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.Classe;

import java.util.List;


@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {
    // Méthode pour trouver des classes par ID de formation et niveau
    List<Classe> findByFormationIdAndNiveau(Long formationId, String niveau);
}
