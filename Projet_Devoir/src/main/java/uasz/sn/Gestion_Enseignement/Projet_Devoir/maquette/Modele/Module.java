package uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @ManyToOne
    @JoinColumn(name = "ec_id")
    private EC ec;
    @ManyToOne
    @JoinColumn(name = "ue_id") // Clé étrangère pointant vers UE
    private UE ue;
}
