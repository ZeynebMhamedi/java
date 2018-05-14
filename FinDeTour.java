package ileInterdite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/*
Bouton fin de tour. Un clic sur ce bouton permet de passer d'un joueur a un autre et 
d'inonder 3 zones aleatoires a la fin d'un tour
	
On fait appel a la classe abstraite [ZoneCliquable].
Pour cela, il faut definir deux methodes correspondant aux
actions a effectuer lors d'un clic avec le bouton gauche ou
avec le bouton droit. Leurs signatures sont :

public void clicGauche();
public void clicDroit();

ici on utilise que le clic gauche
*/
public class FinDeTour extends ZoneCliquable {
	
	 private Ile ile;
	 
	 //indique quel joueur joue
	 private Texte tour;

	
	 public FinDeTour(Ile ile,Texte t) {
			// Création d'une zone cliquable de dimensions 80*25 pixels,
			// et contenant le texte "FinDeTour".
			super("FinDeTour", 80, 25);
			this.ile = ile;
			this.tour = t;
		    }
	 
	 //action à effectuer quand on clique sur le bouton
	 @Override
	 public void clicGauche(){
		
		//on passe au joueur suivant
    	ile.tourJoueur++;
    	
    	//si tous les joueurs ont joue, on innonde
    	if (ile.tourJoueur == ile.nbJoueurs){
    		ile.randInond();
    		ile.tourJoueur = 0;
    	}
    	
    	//on retire les joueurs submerge
    	List<Zone> perdu = new ArrayList<Zone>();
    	for (Joueur j : ile.joueurs){
    		if (j.gameOver()){
    			perdu.add(j.getLocalisation());
    		}
    	}
    	for (Zone z : perdu){
    		ile.removeJoueur(z.getJoueur());
    	}
    	
    	//si plus de joueur, c'est fini
    	if(ile.joueurs.isEmpty()){
    		return;
    	}
    	
    	//on selectionnne le joueur qui joue, on l'autorise a bouger
    	Joueur j = ile.joueurs.get(ile.tourJoueur);
    	j.nbActions = 3;
    	tour.changeTexte("Tour de " + j.couleur);
    	
    	//recevoir les cles avec une probabilite de 40%
    	int x=(int)( Math.random()*100);
    	int cleRecu = 0;
    	if (x<=40){
    		j.recevoirCle = true;
    		int cleAlea =  new Random().nextInt(4)+1;
    		cleRecu = cleAlea;
    		if (cleRecu == 1){
    			j.cleAir++;
    		}
    		else if (cleRecu == 2){
    			j.cleEau++;
    		}
    		else if (cleRecu == 3){
    			j.cleFeu++;
    		}
    		else if (cleRecu == 4){
    			j.cleTerre++;
    		}
    	}
		
    	//on recoit une action speciale si on en a pas avec une proba de 10%
    	if (!(j.helico || j.sacDeSable)){
    		int actionSpe = (int)(Math.random()*100);
    		if (actionSpe <= 5) {
    			j.helico = true;
    		}
    		if (actionSpe >= 95) {
    			j.sacDeSable = true;
    		}
    	}
    	
		//on met a jour son texte
    	j.updateTexteJoueur(cleRecu);
    }

	@Override
	protected void clicDroit() {}
}
