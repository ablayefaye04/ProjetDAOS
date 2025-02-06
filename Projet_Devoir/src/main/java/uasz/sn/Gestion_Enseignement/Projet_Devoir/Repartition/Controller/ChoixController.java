package uasz.sn.Gestion_Enseignement.Projet_Devoir.Repartition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Authentification.modele.Utilisateur;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Authentification.service.UtilisateurService;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Repartition.Modele.Choix;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Repartition.Modele.Enseignement;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Repartition.Repository.ChoixRepository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Repartition.Repository.EnseignementRepository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Repartition.Service.ChoixService;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Repartition.Service.EnseignementService;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Modele.Enseignant;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Repository.EnseignantRepository;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Service.EnseignantService;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.EC;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Modele.Maquette;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Service.ECService;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.maquette.Service.MaquetteService;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
@Controller
public class ChoixController {




}
