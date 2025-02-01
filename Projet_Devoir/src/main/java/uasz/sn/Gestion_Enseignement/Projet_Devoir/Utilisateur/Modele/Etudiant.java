package uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Modele;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Authentification.modele.Utilisateur;

import java.util.Date;
 @Entity
 @Data
 @AllArgsConstructor
 @NoArgsConstructor
 @Table(name = "Etudiant")
 @PrimaryKeyJoinColumn(name = "id")

 public class Etudiant extends Utilisateur {
    private String matricule;
    private Date dateNaissance;
}
