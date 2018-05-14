package ileInterdite;

import java.awt.BorderLayout;
import java.awt.Color;

/* 
   Classe principale ileInterdite.
   Ici on initialise le jeu et l'affichage.

   Pour l'affichage, on fait appel a la classe Fenetre
*/
public class IleInterdite {

	
	public static void main(String[] args) {
		
		//doc code couleur
		System.out.println("CODE COULEUR :\nRouge,Vert,Rose : joueurs\nViolet : Heliport\n" + 
		"orange pale : element feu\nbleu pale : element eau\nmarron : element terre\n" +
				"blanc : element air\n");
		
		//doc clics de la souris
		System.out.println("CLICS SOURIS :\nClic gauche : assecher une zone, et se deplacer en helicoptere" +
				" (si on possede l'action speciale helicoptere)\n" + "Clic droit : recuperer un artefact. \n" );
		
		//Initialisation fenetre,ile
		Fenetre fenetre = new Fenetre("Ile interdite");
		Ile ile = new Ile (10,10);
		
		//initialisation des textes
		TexteJoueur t1 = new TexteJoueur("ROUGE");
		TexteJoueur t2 = new TexteJoueur("VERT");
		TexteJoueur t3 = new TexteJoueur("ROSE");
		Texte tour = new Texte ("Tour de Rouge");
		
		//initialisation des joueurs
		Joueur j1 = new Joueur (ile.randGetZoneJoueur(),
				new Color(255,69,71),new Color(255,0,0),t1,"ROUGE");
		ile.addJoueur(j1);
		Joueur j2 = new Joueur (ile.randGetZoneJoueur(),
				new Color(124,252,0),new Color(50,205,50),t2,"VERT");
		ile.addJoueur(j2);
		Joueur j3 = new Joueur (ile.randGetZoneJoueur(),
				new Color(255,105,180),new Color(255,20,147),t3,"ROSE");
		ile.addJoueur(j3);
		
		
		//Initialisation des boutons
		FinDeTour fintour = new FinDeTour(ile,tour);
		Deplacements depH = new Deplacements(ile,"haute");
		Deplacements depB = new Deplacements(ile,"basse");
		Deplacements depD = new Deplacements(ile,"droite");
		Deplacements depG = new Deplacements(ile,"gauche");

		//ajout elements de la fenetre
		fenetre.ajouteTexte(tour);
		fenetre.ajouteTexte(new Texte(" "));
		fenetre.ajouteTexte(t1.joueur);
		fenetre.ajouteTexte(t1.notif);
		fenetre.ajouteTexte(t1.actionSpe);
		fenetre.ajouteTexte(t1.cleAir);
		fenetre.ajouteTexte(t1.cleEau);
		fenetre.ajouteTexte(t1.cleFeu);
		fenetre.ajouteTexte(t1.cleTerre);
		fenetre.ajouteTexte(t1.ArteAir);
		fenetre.ajouteTexte(t1.ArteEau);
		fenetre.ajouteTexte(t1.ArteFeu);
		fenetre.ajouteTexte(t1.ArteTerre);
		fenetre.ajouteTexte(t1.sep);
		
		fenetre.ajouteTexte(t2.joueur);
		fenetre.ajouteTexte(t2.notif);
		fenetre.ajouteTexte(t2.actionSpe);
		fenetre.ajouteTexte(t2.cleAir);
		fenetre.ajouteTexte(t2.cleEau);
		fenetre.ajouteTexte(t2.cleFeu);
		fenetre.ajouteTexte(t2.cleTerre);
		fenetre.ajouteTexte(t2.ArteAir);
		fenetre.ajouteTexte(t2.ArteEau);
		fenetre.ajouteTexte(t2.ArteFeu);
		fenetre.ajouteTexte(t2.ArteTerre);
		fenetre.ajouteTexte(t2.sep);
		
		fenetre.ajouteTexte(t3.joueur);
		fenetre.ajouteTexte(t3.notif);
		fenetre.ajouteTexte(t3.actionSpe);
		fenetre.ajouteTexte(t3.cleAir);
		fenetre.ajouteTexte(t3.cleEau);
		fenetre.ajouteTexte(t3.cleFeu);
		fenetre.ajouteTexte(t3.cleTerre);
		fenetre.ajouteTexte(t3.ArteAir);
		fenetre.ajouteTexte(t3.ArteEau);
		fenetre.ajouteTexte(t3.ArteFeu);
		fenetre.ajouteTexte(t3.ArteTerre);
		
		fenetre.ajouteIle(ile);
		fenetre.ajouteFinTour(fintour);
		fenetre.ajouteBouton(depH);
		fenetre.ajouteBouton(depG);
		fenetre.ajouteBouton(depD);
		fenetre.ajouteBouton(depB);	
		
		

		//dessin de la fenetre
		fenetre.dessineFenetre();

		
	}
}