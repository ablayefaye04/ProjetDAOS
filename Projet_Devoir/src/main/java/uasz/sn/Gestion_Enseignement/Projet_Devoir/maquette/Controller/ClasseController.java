package uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.Classe;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Service.ClasseService;


@Controller
public class ClasseController {
    @Autowired
    private ClasseService classeService;

    @RequestMapping(value = "/ajouter_classe_formation", method = RequestMethod.POST)
    public String ajouter_classe_formation(Model model, Classe classe){
        classeService.ajouterClasse(classe);
        return "redirect:/details_formation?id="+classe.getFormation().getId();
    }

    @RequestMapping(value = "/modifier_classe_formation", method = RequestMethod.POST)
    public String modifier_classe_formation(Model model, Classe classe){
        classeService.modifierClasse(classe);
        return "redirect:/details_formation?id="+classe.getFormation().getId();
    }

    @RequestMapping(value = "/supprimer_classe_formation", method = RequestMethod.POST)
    public String supprimer_classe_formation(Model model, Classe classe){
        Long id = classe.getFormation().getId();
        classeService.supprimerClasse(classe);
        return "redirect:/details_formation?id="+id;
    }
}
