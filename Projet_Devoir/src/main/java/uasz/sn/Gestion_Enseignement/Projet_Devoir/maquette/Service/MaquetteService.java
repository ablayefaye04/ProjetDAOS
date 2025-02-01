package uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.EC;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.Formation;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.Maquette;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.UE;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository.ECRepository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository.FormationRepository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository.MaquetteRepository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository.UERepository;

import java.util.List;

@Service
public class MaquetteService {

    @Autowired
    private MaquetteRepository maquetteRepository;
    @Autowired
    private UERepository ueRepository;
    @Autowired
    private ECRepository ecRepository;
    @Autowired
    private FormationRepository formationRepository;

    //methode pour lister tous les maquette
    public List<Maquette> findAll(){
        return  maquetteRepository.findAll();
    }

    //methode pour savegarder une maquette
    public void  saveMaquette (Maquette maquette){
        maquetteRepository.save(maquette);
    }

    //methode pour supprimer une maquette
    public void DeleteMaquetteById(Long id){
        maquetteRepository.deleteById(id);
    }

    //methode pour mise a jour une maquette
    public void UpdateMaquette (Maquette maquette){
        maquetteRepository.save(maquette);
    }

    //methode pour recuperer la liste des formation non Archiver
    public List<Formation> findAllFormationNonArchived(){
        List<Formation> formation = formationRepository.findAllNonArchivedFormations();
        return formation;
    }
    //methode pour recuperer la liste des UE
    public List<UE> findAllUE (){
        return  ueRepository.findAll();
    }

    //methode pour recuperer la liste des EC a partir d'un UE
    public List<EC> findEcByUE(Long ueId) {
        if (ueId == null) {
            throw new IllegalArgumentException("L'identifiant de l'UE ne peut pas être null");
        }

        return ueRepository.findById(ueId)
                .map(UE::getEcs)
                .map(List::copyOf)
                .orElseGet(List::of);
    }


    //fonctionalite pour archiver une maquette
    public void archivee(Long id){
        Maquette maquette = maquetteRepository.findById(id).get();
        if(maquette.isArchiver()==true){
            maquette.setArchiver(false);
        }
        else {
            maquette.setArchiver(true);
        }
        maquetteRepository.save(maquette);
    }
}
