package uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Modele;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Authentification.modele.Utilisateur;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "id")
public abstract class Enseignant extends Utilisateur {
    private String Specialite;
    private boolean archive;

}
