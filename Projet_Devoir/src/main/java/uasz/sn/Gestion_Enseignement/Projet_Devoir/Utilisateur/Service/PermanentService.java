package uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Modele.Permanent;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Repository.PermanentRepository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.exception.ResourceAlreadyExistException;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.exception.ResourceNotFoundException;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PermanentService {
    @Autowired
    private PermanentRepository permanentRepository;

    public Permanent ajouter(Permanent permanent){
        Optional<Permanent> optionalEtudiant = permanentRepository.findByMatricule(permanent.getMatricule());
        if (optionalEtudiant.isPresent())
           // System.out.println(permanent);
        throw  new ResourceAlreadyExistException("Le matricule "+permanent.getMatricule()+ "existe deja");
        System.out.println(permanent);
        try {
            return permanentRepository.save(permanent);
        }catch (Exception exception){
            throw  new ResourceNotFoundException("Erreur lors de l'ajout");
        }
    }

    public List<Permanent> Liste (){
        return permanentRepository.findAll();
    }

    public Permanent rechercher(Long id){
        try {
            return permanentRepository.findById(id).get();
        }catch (Exception e){
            throw new ResourceNotFoundException("Permanent avec ID "+id+"n'existe pas ");
        }

    }

    public Permanent modifier(Permanent permanent){
        try {
            return permanentRepository.save(permanent);
        }catch ( Exception exception){
            throw new ResourceNotFoundException("Erreurs lors de la modification "+permanent.getMatricule()+" ");
        }
    }

    public void supprimer(Long id){
        permanentRepository.deleteById(id);
    }
}

