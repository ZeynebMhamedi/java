
import java.awt.Color;
import java.lang.Object;


public class Joueur{

	private Ile ile;
	private Zone localisation;
	private int cle;
	private int artefact;
	
	public Joueur(Zone localisation, int cle, int artefact) {
        this.localisation = localisation;
        this.cle = cle;
        this.artefact = artefact;
    }
    
	
	public Joueur moveJoueur(String move){
		if (move.equals("droite")) {
			if ((this.localisation.droite.getEtat() != 2) && (this.localisation.droite.getEtat() != 1)){
				return new Joueur(this.localisation.droite, this.cle, this.artefact);
			}
		}
		
		else if (move.equals("gauche")) {
			if ((this.localisation.droite.getEtat() != 2) && (this.localisation.droite.getEtat() != 1)){
				return new Joueur(this.localisation.gauche, this.cle, this.artefact);
			}
		}
		
		else if (move.equals("haute")) {
			if ((this.localisation.droite.getEtat() != 2) && (this.localisation.droite.getEtat() != 1)){
				return new Joueur(this.localisation.haute, this.cle, this.artefact);}
		}
		
		else if (move.equals("basse")) {
			if ((this.localisation.droite.getEtat() != 2) && (this.localisation.droite.getEtat() != 1)){
				return new Joueur(this.localisation.basse, this.cle, this.artefact);}
		}
		
		return this;
	}
	
	
}
