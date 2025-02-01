package uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.EC;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.UE;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Service.UEService;


import java.util.List;

@Controller
public class UEController {
    @Autowired
    private UEService ueService;

    @RequestMapping(value = "/ue", method = RequestMethod.GET)
    public  String lister_ue(Model model){

        List<UE> ueList = ueService.afficherToutUE();
        model.addAttribute("listeDesUE", ueList);
        return "ue";
    }

    @RequestMapping(value = "/ajouter_ue", method = RequestMethod.POST)
    public String ajouter_ue(Model model, UE ue){
        ueService.ajouterUE(ue);
        return "redirect:/ue";
    }

    @RequestMapping(value = "/modifier_ue", method = RequestMethod.POST)
    public String modifier_ue(Model model, UE ue){
        ueService.modifierUE(ue);
        return "redirect:/ue";
    }

    @RequestMapping(value = "/supprimer_ue", method = RequestMethod.POST)
    public String supprimer_ue(Model model, UE ue){
        ueService.supprimerUE(ue);
        return "redirect:/ue";
    }

    @RequestMapping(value = "/details_ue", method = RequestMethod.GET)
    public String details_ue(Model model, @RequestParam("id") Long id){
        UE ue = ueService.afficherUE(id);
        List <EC> ecList = ueService.afficherLesEC(ue);
        // Ajouter un nouvel objet EC pour le formulaire
        EC newEC = new EC();
        newEC.setUe(ue); // Associe directement l'UE au nouvel EC

        model.addAttribute("ue", ue);
        model.addAttribute("listeDesEC", ecList);
        model.addAttribute("newEC", newEC); // Objet pour le formulaire
        return "details_ue";
    }

}
