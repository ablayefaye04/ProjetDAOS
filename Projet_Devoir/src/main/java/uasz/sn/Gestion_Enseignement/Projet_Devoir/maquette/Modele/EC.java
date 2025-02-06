package uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Repartition.Modele.Enseignement;


import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private String code;
    private int coefficient;
    private int cm;
    private int td;
    private int tp;
    private int tpe;
    private String description;
    @ManyToOne
    private UE ue;
    @OneToMany(mappedBy = "ec")
    private Collection <Module> modules;

}
