package uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.Maquette;

import java.util.List;

@Repository
public interface MaquetteRepository extends JpaRepository<Maquette, Long> {

    @Query("SELECT m FROM Maquette m WHERE m.classe.id = :classeId")
    List<Maquette> findByClasseId(@Param("classeId") Long classeId);

    @Query("SELECT m FROM Maquette m WHERE m.archiver IS FALSE")
    List<Maquette> findAllNonArchiverdMaquette() ;

}
