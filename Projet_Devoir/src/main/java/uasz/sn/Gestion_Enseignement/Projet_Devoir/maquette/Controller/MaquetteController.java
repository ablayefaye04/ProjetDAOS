package uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Authentification.modele.Utilisateur;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Authentification.service.UtilisateurService;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.*;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository.ECRepository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository.FormationRepository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository.MaquetteRepository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Repository.UERepository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Service.ClasseService;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Service.FormationService;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Service.MaquetteService;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Service.UEService;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/maquettes")
public class MaquetteController {

    @Autowired
    MaquetteRepository maquetteRepository;
    @Autowired
    MaquetteService maquetteService;

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    private FormationService formationService;

    @Autowired
    UERepository ueRepository;
    @Autowired
    private UEService ueService;
    @Autowired
    private ClasseService classeService;

    //recuperation des EC du UE correspondant
    @GetMapping("/getEcsByUe/{ueId}")
    @ResponseBody
    public List<EC> getEcsByUe(@PathVariable Long ueId) {
        return maquetteService.findEcByUE(ueId);
    }

    //ajouter une maquette
    @PostMapping("/ajouter")
    public String ajouterMaquette(@RequestParam("semestre") String semestre,
                                  @RequestParam("ue") Long ueId,
                                  @RequestParam("formationId") Long formationId,
                                  @RequestParam("classeId") Long classeId,
                                  RedirectAttributes redirectAttributes) {

        // Récupérer la formation et la classe en fonction des IDs
        Formation formation = formationService.afficherFormation(formationId);
        Classe classe = classeService.afficherClasse(classeId);
        UE ue = ueService.afficherUE(ueId);

        // Vérifier si la formation, la classe ou l'UE n'existent pas
        if (formation == null || classe == null || ue == null) {
            // Ajouter un message d'erreur et rediriger
            redirectAttributes.addFlashAttribute("error", "Formation, classe ou UE introuvable !");
            return "redirect:/maquettes/liste"; // Rediriger vers la page de liste des maquettes
        }

        // Vérifier si une maquette avec les mêmes données existe déjà
        Optional<Maquette> maquetteExistante = maquetteService.findBySemestreAndFormationAndClasseAndUe(
                semestre, formation, classe, ue
        );

        if (maquetteExistante.isPresent()) {
            // Ajouter un message d'erreur et rediriger vers la page de détails de la maquette
            redirectAttributes.addFlashAttribute("error", "Cette maquette existe déjà pour cette classe et ce semestre !");
            return "redirect:/maquettes/details_maquette_classe?id=" + formationId + "&classeId=" + classeId;
        }

        // Créer et sauvegarder la maquette
        Maquette maquette = new Maquette();
        maquette.setSemestre(semestre);
        maquette.setFormation(formation);
        maquette.setClasse(classe);
        maquette.setUe(Collections.singleton(ue));

        maquetteService.saveMaquette(maquette);

        // Ajouter un message de succès et rediriger vers la page de détails de la maquette
        redirectAttributes.addFlashAttribute("success", "Maquette ajoutée avec succès !");
        return "redirect:/maquettes/details_maquette_classe?id=" + formationId + "&classeId=" + classeId;
    }


    // Traiter la soumission du formulaire de modification (POST)
    @PreAuthorize("hasRole('ChefDepartement')")
    @PostMapping("/modifier/{id}")
    public String modifierMaquette(@PathVariable("id") Long id, Maquette maquette) {
        Maquette existingMaquette = maquetteService.getMaquetteById(id);
        if (existingMaquette == null) {
            return "error_page";  // Retourner une page d'erreur si la maquette n'existe pas
        }

        // Sauvegarder la maquette modifiée
        maquetteService.UpdateMaquette(existingMaquette);

        return "redirect:/details_maquette_classe?id=" + existingMaquette.getFormation().getId() + "&classeId=" + existingMaquette.getClasse().getId();
    }

    // Traiter la soumission du formulaire de suppression (POST)
    @PreAuthorize("hasRole('ChefDepartement')")
    @PostMapping("/supprimer/{id}")
    public String supprimerMaquette(@PathVariable("id") Long id) {
        Maquette maquette = maquetteService.getMaquetteById(id);
        if (maquette != null) {
            maquetteService.DeleteMaquetteById(id);  // On passe l'ID directement
            return "redirect:/details_maquette_classe";  // Rediriger vers la page de détails après suppression
        } else {
            // Gérer le cas où la maquette n'existe pas (facultatif)
            return "redirect:/error_page";  // Ou une autre page d'erreur
        }
    }

    //archiver une maquette
    @PostMapping("/archiver/{id}")
    public String archiverMaquette(@PathVariable("id") Long id) {
        maquetteService.archivee(id);
        return "redirect:/maquettes/liste";
    }

    @GetMapping("/details_maquette_classe")
    public String afficherDetailsMaquetteParClasse(Model model,
                                                   @RequestParam("id") Long formationId,
                                                   @RequestParam("classeId") Long classeId,
                                                   Principal principal) {
        // Récupérer la formation et la classe par leurs ID
        Formation formation = formationService.afficherFormation(formationId);
        Classe classe = classeService.afficherClasse(classeId);

        if (formation == null || classe == null) {
            model.addAttribute("error", "Formation ou Classe introuvable");
            return "error_page"; // Ou une page d'erreur appropriée
        }

        // Récupérer les autres informations nécessaires pour l'affichage
        List<Classe> classeList = formationService.afficherLesClasses(formation);
        List<UE> listeDesUEs = maquetteService.findAllUE();
        List<Maquette> maquettes = maquetteService.findMaquettesForClasseAndFormation(classeId, formationId);
        Set<String> semestres = maquetteService.getAllSemestres(maquettes);

        // Initialisation des totaux pour chaque semestre
        Map<String, Integer> totalCreditsUE = new HashMap<>();
        Map<String, Integer> totalCoeffUE = new HashMap<>();
        Map<String, Integer> totalCM = new HashMap<>();
        Map<String, Integer> totalTD = new HashMap<>();
        Map<String, Integer> totalTP = new HashMap<>();
        Map<String, Integer> totalTPE = new HashMap<>();
        Map<String, Integer> totalCoefficient = new HashMap<>();
        Map<String, Double> totalSumFormula = new HashMap<>(); // Stocke la somme des valeurs (CM + TD/TP)

        // Calcul des totaux par semestre
        for (Maquette maquette : maquettes) {
            String semestre = maquette.getSemestre();

            int sumCreditUE = 0, sumCoeffUE = 0;
            int sumCM = 0, sumTD = 0, sumTP = 0, sumTPE = 0, sumCoefficient = 0;
            double sumFormula = 0.0; // Somme totale de la formule (CM + TD / TP)

            for (UE ue : maquette.getUe()) {
                sumCreditUE += ue.getCredit();
                sumCoeffUE += ue.getCoefficient();

                for (EC ec : ue.getEcs()) {
                    sumCM += ec.getCm();
                    sumTD += ec.getTd();
                    sumTP += ec.getTp();
                    sumTPE += ec.getTpe();
                    sumCoefficient += ec.getCoefficient();

                    // Éviter une division par zéro en vérifiant si TP est non nul
                    double value = ec.getTp() != 0 ? ec.getCm() + (ec.getTd() / (double) ec.getTp()) : ec.getCm();
                    sumFormula += value;
                }
            }

            // Mettre à jour les totaux pour ce semestre
            String semestreKey = maquette.getSemestre().trim(); // Enlever espaces inutiles

            totalCreditsUE.put(semestreKey, totalCreditsUE.getOrDefault(semestreKey, 0) + sumCreditUE);
            totalCoeffUE.put(semestreKey, totalCoeffUE.getOrDefault(semestreKey, 0) + sumCoeffUE);
            totalCM.put(semestreKey, totalCM.getOrDefault(semestreKey, 0) + sumCM);
            totalTD.put(semestreKey, totalTD.getOrDefault(semestreKey, 0) + sumTD);
            totalTP.put(semestreKey, totalTP.getOrDefault(semestreKey, 0) + sumTP);
            totalTPE.put(semestreKey, totalTPE.getOrDefault(semestreKey, 0) + sumTPE);
            totalCoefficient.put(semestreKey, totalCoefficient.getOrDefault(semestreKey, 0) + sumCoefficient);
            totalSumFormula.put(semestreKey, totalSumFormula.getOrDefault(semestreKey, 0.0) + sumFormula);
        }

        // Ajouter des objets au modèle pour l'affichage
        model.addAttribute("formation", formation);
        model.addAttribute("listeDesClasses", classeList);
        model.addAttribute("listeDesUEs", listeDesUEs);
        model.addAttribute("classe", classe);
        model.addAttribute("maquettes", maquettes);
        model.addAttribute("semestres", semestres);

        // Ajouter les totaux au modèle
        model.addAttribute("totalCreditsUE", totalCreditsUE);
        model.addAttribute("totalCoeffUE", totalCoeffUE);
        model.addAttribute("totalCM", totalCM);
        model.addAttribute("totalTD", totalTD);
        model.addAttribute("totalTP", totalTP);
        model.addAttribute("totalTPE", totalTPE);
        model.addAttribute("totalCoefficient", totalCoefficient);
        model.addAttribute("totalSumFormula", totalSumFormula); // Ajout de la somme totale calculée

        // Utiliser Principal pour obtenir l'utilisateur connecté
        String username = principal.getName(); // Récupérer le nom d'utilisateur

        // Récupérer l'utilisateur depuis le service
        Utilisateur utilisateur = utilisateurService.rechercher_Utilisateur(username);

        if (utilisateur != null) {
            model.addAttribute("utilisateur", utilisateur);

            // Si l'utilisateur a un rôle "ChefDepartement", rediriger vers la page spécifique
            if (utilisateur.getRoles().stream().anyMatch(role -> role.getRole().equals("ChefDepartement"))) {
                return "details_maquette_classe_chef"; // Rediriger vers la page spécifique pour le Chef de Département
            }
        }

        // Pour les autres utilisateurs, rediriger vers la page par défaut
        return "details_maquette_classe"; // Page par défaut pour les autres utilisateurs
    }
}
