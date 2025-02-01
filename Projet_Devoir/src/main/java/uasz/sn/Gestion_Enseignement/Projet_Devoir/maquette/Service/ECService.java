package uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.EC;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository.ECRepository;


import java.util.List;

@Service
@Transactional
public class ECService {
    @Autowired
    private ECRepository ecRepository;

    public void ajouterEC(EC ec){
        ecRepository.save(ec);
    }

    public List<EC> afficherToutEC(){
        return ecRepository.findAll();
    }

    public EC afficherEC(Long id){
        return ecRepository.getById(id);
    }

    public void modifierEC(EC ec){
        EC ecUpdate=ecRepository.getById(ec.getId());
        ecUpdate.setCode((ec.getCode()));
        ecUpdate.setLibelle(ec.getLibelle());
        ecUpdate.setCm(ec.getCm());
        ecUpdate.setTd(ec.getTd());
        ecUpdate.setTp(ec.getTp());
        ecUpdate.setTpe(ec.getTpe());
        ecUpdate.setCoefficient(ec.getCoefficient());
        ecUpdate.setDescription(ec.getDescription());
        ecRepository.save(ecUpdate);
    }

    public void supprimerEC(EC ec){
        ecRepository.delete(ec);
    }

}
