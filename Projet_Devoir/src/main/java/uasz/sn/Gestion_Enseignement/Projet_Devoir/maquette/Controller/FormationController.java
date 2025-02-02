package uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.Classe;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.Formation;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Service.FormationService;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Service.MaquetteService;


import java.util.List;

@Controller
public class FormationController {
    @Autowired
    private FormationService formationService;
    @Autowired
    MaquetteService maquetteService;

    @RequestMapping(value = "/formation", method = RequestMethod.GET)
    public  String lister_formation(Model model){
        List<Formation> formationList = formationService.afficherToutformation();
        model.addAttribute("listeDesFormations", formationList);
        return "formation";
    }

    @RequestMapping(value = "/formation", method = RequestMethod.POST)
    public String Archiveer(Long id){
        formationService.archiver(id);
        return "redirect:/formation";
    }

    @RequestMapping(value = "/ajouter_formation", method = RequestMethod.POST)
    public String ajouter_formation(Model model, Formation formation){
        formationService.ajouterFormation(formation);
        return "redirect:/formation";
    }

    @RequestMapping(value = "/modifier_formation", method = RequestMethod.POST)
    public String modifier_formation(Model model, Formation formation){
        formationService.modifierFormation(formation);
        return "redirect:/formation";
    }

    @RequestMapping(value = "/supprimer_formation", method = RequestMethod.POST)
    public String supprimer_formation(Model model, Formation formation){
        formationService.supprimerFormation(formation);
        return "redirect:/formation";
    }

    @RequestMapping(value = "/details_formation", method = RequestMethod.GET)
    public String details_formation(Model model, @RequestParam("id") Long id){
        Formation formation = formationService.afficherFormation(id);
        List <Classe> classeList = formationService.afficherLesClasses(formation);
        // Ajouter un nouvel objet EC pour le formulaire
        Classe newClasse = new Classe();
        newClasse.setFormation(formation); // Associe directement la formation au nouvel classe

        model.addAttribute("formation", formation);
        model.addAttribute("listeDesClasses", classeList);
        model.addAttribute("listeDesUEs", maquetteService.findAllUE());
        model.addAttribute("newClasse", newClasse); // Objet pour le formulaire
        return "formation_details";
    }
}
