package uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Authentification.modele.Utilisateur;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Authentification.service.UtilisateurService;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Modele.Permanent;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Modele.Vacataire;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Service.PermanentService;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Service.VacataireService;

import java.security.Principal;
import java.util.List;

@Controller
public class EnseignantController {
    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private PermanentService permanentService;
    @Autowired
    private VacataireService vacataireService;

    @RequestMapping(value = "/ChefDepartement/Enseignant", method = RequestMethod.GET)
    public  String chefDepartement_Enseignant(Model model, Principal principal){
        List <Permanent> permanents = permanentService.Liste();
        model.addAttribute("permanents", permanents);
        List <Vacataire> vacataires = vacataireService.Liste();
        model.addAttribute("vacataires", vacataires);
        Utilisateur utilisateur = utilisateurService.rechercher_Utilisateur(principal.getName());
        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom().charAt(0));
        return "chefDepartement_Enseignant";
    }
}
