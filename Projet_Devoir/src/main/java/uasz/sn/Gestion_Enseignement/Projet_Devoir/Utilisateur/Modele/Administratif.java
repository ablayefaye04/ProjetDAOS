package uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Modele;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Authentification.modele.Utilisateur;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Adminnistratif")

public class Administratif extends Utilisateur {
    private String matricule;

}
