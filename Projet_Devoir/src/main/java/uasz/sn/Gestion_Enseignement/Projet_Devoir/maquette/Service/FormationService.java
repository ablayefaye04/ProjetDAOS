package uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.Classe;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.Formation;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository.FormationRepository;


import java.util.List;

@Service
@Transactional
public class    FormationService {
    @Autowired
    private FormationRepository formationRepository;

    public void ajouterFormation(Formation formation){
        formationRepository.save(formation);
    }

    public List<Formation> afficherToutformation(){
        return formationRepository.findAll();
    }

   /* public List<Formation> getNonArchivedFormations() {
        return formationRepository.findAllNonArchivedFormations();
    }*/

    public Formation afficherFormation(Long id){
        return formationRepository.getById(id);
    }

    public void modifierFormation(Formation formation){
        Formation ueUpdate=formationRepository.getById(formation.getId());
        ueUpdate.setNom((formation.getNom()));
        ueUpdate.setDescription((formation.getDescription()));
        formationRepository.save(ueUpdate);
    }

    public void supprimerFormation(Formation formation){
        formationRepository.delete(formation);
    }

    public List<Classe> afficherLesClasses(Formation formation){
        return formationRepository.findByFormation(formation);
    }
//LA fonctionnalite  pour archiver  les formation
    public void archiver(Long id){
        Formation formation = formationRepository.findById(id).get();
        if( formation.isArchiver()==true){
            formation.setArchiver(false);
        }
        else {
            formation.setArchiver(true);
        }
        formationRepository.save(formation);
    }
}
