package uasz.sn.Gestion_Enseignement.Projet_Devoir.Repartition.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Repartition.Modele.Choix;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Repartition.Modele.Enseignement;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Modele.Enseignant;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.Classe;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.EC;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChoixRepository extends JpaRepository<Choix, Long> {

     List<Choix> findByEnseignement(Enseignement enseignement);

    List<Choix> findByEnseignantId(Long enseignantId);

    boolean existsByEnseignantAndEnseignementAndClasseAndSemestre(Enseignant enseignant, Enseignement enseignement, Classe classe, String semestre);

}
