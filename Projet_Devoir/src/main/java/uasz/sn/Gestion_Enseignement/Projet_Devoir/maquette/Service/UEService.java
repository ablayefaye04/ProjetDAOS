package uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.EC;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.UE;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository.UERepository;

import java.util.List;

@Service
@Transactional
public class UEService {
    @Autowired
    private UERepository ueRepository;

    public void ajouterUE(UE ue){
        ueRepository.save(ue);
    }

    public List <UE> afficherToutUE(){
        return ueRepository.findAll();
    }

    public UE afficherUE(Long id){
        return ueRepository.getById(id);
    }

    public void modifierUE(UE ue){
        UE ueUpdate=ueRepository.getById(ue.getId());
        ueUpdate.setCode((ue.getCode()));
        ueUpdate.setLibelle(ue.getLibelle());
        ueUpdate.setCredit(ue.getCredit());
        ueUpdate.setCoefficient(ue.getCoefficient());
        ueUpdate.setDescription(ue.getDescription());
        ueRepository.save(ueUpdate);
    }

    public void supprimerUE(UE ue){
        ueRepository.delete(ue);
    }

    public List<EC> afficherLesEC(UE ue){
        return ueRepository.findByUE(ue);
    }


}
