package uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.EC;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.UE;

import java.util.List;

@Repository
public interface UERepository extends JpaRepository <UE, Long> {
    @Query("SELECT e FROM EC e WHERE e.ue = ?1")
    List<EC> findByUE (UE ue);

}
