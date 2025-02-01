package uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.EC;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Service.ECService;


@Controller
public class ECController {
    @Autowired
    private ECService ecService;

    @RequestMapping(value = "/ajouter_ec_ue", method = RequestMethod.POST)
    public String ajouter_ec_ue(Model model, EC ec){
        ecService.ajouterEC(ec);
        return "redirect:/details_ue?id="+ec.getUe().getId();
    }

    @RequestMapping(value = "/modifier_ec_ue", method = RequestMethod.POST)
    public String modifier_ec_ue(Model model, EC ec){
        ecService.modifierEC(ec);
        return "redirect:/details_ue?id="+ec.getUe().getId();
    }

    @RequestMapping(value = "/supprimer_ec_ue", method = RequestMethod.POST)
    public String supprimer_ec_ue(Model model, EC ec){
        Long id = ec.getUe().getId();
        ecService.supprimerEC(ec);
        return "redirect:/details_ue?id="+id;
    }
}
