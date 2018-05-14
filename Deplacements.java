package ileInterdite;

import java.util.List;

public class Deplacements extends ZoneCliquable {
	 private Ile ile;
	 //haute,basse,droite ou gauche
	 private String direction;
	 
		
	 public Deplacements(Ile ile,String s) {
			// Création d'une zone cliquable de dimensions 80*25 pixels,
		    // et contenant le texte s
			super(s, 80, 25);
			this.ile = ile;
			this.direction = s;
	 }
	 
	 //Action à effectuer suite au clic de la souris
	 @Override
	 public void clicGauche(){
	    	Joueur j = this.ile.joueurs.get(this.ile.tourJoueur);
	    	j.moveJoueur(direction);
	    }

	@Override
	protected void clicDroit() {
		// TODO Auto-generated method stub
		
	}
}
