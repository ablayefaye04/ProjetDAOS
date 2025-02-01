package uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.Classe;


@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {
}
