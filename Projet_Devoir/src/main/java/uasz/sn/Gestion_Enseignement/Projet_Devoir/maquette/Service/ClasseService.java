package uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.Classe;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository.ClasseRepository;

import java.util.List;

@Service
@Transactional
public class ClasseService {
    @Autowired
    private ClasseRepository classeRepository;

    public void ajouterClasse(Classe classe){
        classeRepository.save(classe);
    }

    public List<Classe> afficherToutClasse(){
        return classeRepository.findAll();
    }

    public Classe afficherClasse(Long id){
        return classeRepository.getById(id);
    }

    public void modifierClasse(Classe classe){
        Classe ecUpdate=classeRepository.getById(classe.getId());
        ecUpdate.setNiveau((classe.getNiveau()));
        ecUpdate.setDescriptionClasse((classe.getDescriptionClasse()));
        classeRepository.save(ecUpdate);
    }

    public void supprimerClasse(Classe classe){
        classeRepository.delete(classe);
    }

}
