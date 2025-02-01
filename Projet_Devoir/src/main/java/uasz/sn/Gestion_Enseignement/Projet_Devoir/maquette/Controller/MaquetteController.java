package uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.EC;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.Formation;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.Maquette;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.UE;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository.ECRepository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository.FormationRepository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository.MaquetteRepository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository.UERepository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Service.MaquetteService;

import java.util.List;


@Controller
@RequestMapping("/maquettes")
public class MaquetteController {

    @Autowired
    MaquetteRepository maquetteRepository;
    @Autowired
    MaquetteService maquetteService;
    @Autowired
    UERepository ueRepository;

    //lister la liste des maquettes
    @GetMapping("/liste")
    public String ListerMaquette(Model model){
        model.addAttribute("listeDesMaquettes", maquetteService.findAll());
        model.addAttribute("ListeDesFormation", maquetteService.findAllFormationNonArchived());
        model.addAttribute("listeDesUEs", maquetteService.findAllUE());
        return "maquette";
    }
    //recuperation des EC du UE correspondant
    @GetMapping("/getEcsByUe/{ueId}")
    @ResponseBody
    public List<EC> getEcsByUe(@PathVariable Long ueId) {
        return maquetteService.findEcByUE(ueId);
    }

    //ajouter une maquette
    @PostMapping("/ajouter")
    public String ajouterMaquette(@ModelAttribute Maquette maquette){
        maquetteService.saveMaquette(maquette);
        return "redirect:/maquettes/liste";
    }

    //Modifier une maquette
    @PostMapping("/modifier")
    public String ModifierMaquette(@ModelAttribute Maquette maquette){
        maquetteService.UpdateMaquette(maquette);
        return "redirect:/maquettes/liste";
    }

    //supprimer une maquette
    @GetMapping("/supprimer/{id}")
    public String SupprimerMaquette(@PathVariable("id") Long id){
        maquetteService.DeleteMaquetteById(id);
        return "redirect:/maquettes/liste" ;
    }

    //archiver une maquette
    @PostMapping("/archiver/{id}")
    public String archiverMaquette(@PathVariable("id") Long id) {
        maquetteService.archivee(id);
        return "redirect:/maquettes/liste";
    }

}
