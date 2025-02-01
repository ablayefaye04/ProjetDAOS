package uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Authentification.modele.Utilisateur;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.Module;

import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private String code;
    private int credit;
    private int coefficient;
    private String description;
    private Date dateCreation;
    @ManyToOne
    private Utilisateur utilisateur;
    @OneToMany(mappedBy = "ue")
    private Collection <EC> ecs;
    @OneToMany(mappedBy = "ue")
    private Collection <Module> modules;

}
