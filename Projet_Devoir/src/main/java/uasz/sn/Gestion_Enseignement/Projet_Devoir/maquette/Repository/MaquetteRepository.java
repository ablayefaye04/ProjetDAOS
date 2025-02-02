package uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.Classe;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.Formation;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.Maquette;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.UE;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaquetteRepository extends JpaRepository<Maquette, Long> {

    @Query("SELECT m FROM Maquette m WHERE m.classe.id = :classeId")
    List<Maquette> findByClasseId(@Param("classeId") Long classeId);

    @Query("SELECT m FROM Maquette m WHERE m.archiver IS FALSE")
    List<Maquette> findAllNonArchiverdMaquette() ;

    @Query("SELECT m FROM Maquette m WHERE m.semestre = :semestre AND m.formation = :formation AND m.classe = :classe AND :ue MEMBER OF m.ue")
    Optional<Maquette> findBySemestreAndFormationAndClasseAndUe(@Param("semestre") String semestre,
                                                                @Param("formation") Formation formation,
                                                                @Param("classe") Classe classe,
                                                                @Param("ue") UE ue);


    // Requête pour récupérer les maquettes par classe, formation et tous les semestres
    @Query("SELECT m FROM Maquette m " +
            "JOIN m.ue ue " +
            "JOIN ue.ecs ec " +
            "WHERE m.classe.id = :classeId AND m.formation.id = :formationId")
    List<Maquette> findMaquettesByClasseAndFormation(@Param("classeId") Long classeId,
                                                     @Param("formationId") Long formationId);

}
