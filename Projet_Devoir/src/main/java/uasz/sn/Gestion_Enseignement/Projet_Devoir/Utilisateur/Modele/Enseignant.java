package uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Authentification.modele.Utilisateur;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Repartition.Modele.Choix;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "id")
public abstract class Enseignant extends Utilisateur {
    private String Specialite;
    private boolean archive;
    @OneToMany(mappedBy = "enseignant")
    private Set<Choix> choixEnseignements = new HashSet<>();

}
