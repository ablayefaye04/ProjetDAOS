package uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.Classe;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.Formation;


import java.util.List;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {
    @Query("SELECT c FROM Classe c WHERE c.formation = ?1")
    List<Classe> findByFormation (Formation formation);

   /* @Query("SELECT f FROM Formation f WHERE f.archiver = false")
    List<Formation> findAllNonArchivedFormations();*/
    @Query("SELECT f FROM Formation f WHERE f.archiver IS FALSE")
    List<Formation> findAllNonArchivedFormations();


}
