package uasz.sn.Gestion_Enseignement.Projet_Devoir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Authentification.modele.Role;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Authentification.service.UtilisateurService;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Modele.Permanent;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Modele.Vacataire;
import uasz.sn.Gestion_Enseignement.Projet_Devoir.Utilisateur.Service.EnseignantService;

import java.util.Date;

@SpringBootApplication
public class ProjetDevoirApplication implements CommandLineRunner {

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private EnseignantService enseignantService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ProjetDevoirApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*Role permanent = utilisateurService.ajouter_Role(new Role("Permanent"));
		Role vacataire = utilisateurService.ajouter_Role(new Role("Vacataire"));
		String password = passwordEncoder.encode("Passer123");

		Permanent user_1 = new Permanent();
		user_1.setNom("DIOP");
		user_1.setPrenom("Ibrahima");
		user_1.setUsername("idiop@uasz.sn");
		user_1.setPassword(password);
		user_1.setDateCreation(new Date());
		user_1.setActive(true);
		user_1.setSpecialite("Web Semantique");
		user_1.setMatricule("ID2024");
		user_1.setGrade("Professeur");
		enseignantService.ajouter(user_1);
		utilisateurService.ajouter_UtilisateurRoles(user_1, permanent);

		Vacataire user_2 = new Vacataire();
		user_2.setNom("MALACK");
		user_2.setPrenom("Camin");
		user_2.setUsername("cmalack@uasz.sn");
		user_2.setPassword(password);
		user_2.setDateCreation(new Date());
		user_2.setActive(true);
		user_2.setSpecialite("Ingénierie de Connaissances");
		user_2.setNiveau("Doctorant");
		enseignantService.ajouter(user_2);
		utilisateurService.ajouter_UtilisateurRoles(user_2, vacataire);

		Role chefDepartement = utilisateurService.ajouter_Role(new Role("ChefDepartement"));

		Permanent user = new Permanent();
		user.setNom("DIAGNE");
		user.setPrenom("Serigne");
		user.setUsername("sdiagne@uasz.sn");
		user.setPassword(password);
		user.setDateCreation(new Date());
		user.setActive(true);
		user.setSpecialite("Base de donnees");
		user.setMatricule("SD2024");
		user.setGrade("Professeur");

		enseignantService.ajouter(user);
		utilisateurService.ajouter_UtilisateurRoles(user, chefDepartement);*/

	}

}
