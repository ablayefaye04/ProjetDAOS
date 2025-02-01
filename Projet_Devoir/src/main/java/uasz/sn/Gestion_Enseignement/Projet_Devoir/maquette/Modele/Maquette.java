package uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Maquette {
    //id de la maquette
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;

    //l'attribut semestre permet de trier les elements a l'affichage pour savoir si c'est au 1er ou 2nd semestre
    private String semestre ;

    //Dans une maquette on peut avoir plusier formation alors que dans une formation peut appartenir a une seule maquette
    @ManyToOne( cascade =  CascadeType.PERSIST)
    private Formation formation;

    //dans une maquette on peut avoir plusieur UE et une UE peut appartenir a plusieur maquette
    @ManyToMany( cascade = CascadeType.ALL)
    private Collection<UE> ue;

    //dans une maquette on peut avoir plusieur UC et une UC peut appartenir a plusieur maquette
    @ManyToMany
    private Collection<EC> ec;

    //une maquette peut une seule classe  alors qu'une maquette peut avoir plusieur classe
    @ManyToOne
    private Classe classe;

    //variable boolean pour archiver une maquette
    private boolean archiver ;


}
