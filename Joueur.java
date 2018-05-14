package ileInterdite;
import java.awt.Color;
import java.lang.Object;
import java.util.LinkedHashSet;
import java.util.Set;


public class Joueur extends ZoneCliquable{

	private Ile ile;
	private Zone localisation;

	protected int nbActions = 3;

	protected String couleur;
	
	protected TexteJoueur t;
	
	//les cle possedees
	protected int cleAir = 0;
	protected int cleEau = 0;
	protected int cleFeu = 0;
	protected int cleTerre = 0;
	protected boolean recevoirCle = false;
	
	//les artefacts possedes
	protected boolean artefactAir = false;
	protected boolean artefactEau = false;
	protected boolean artefactFeu = false;
	protected boolean artefactTerre = false;
	
	//les actions speciales
	protected boolean sacDeSable = false;
	protected boolean helico = false;

	//couleurs (sur case normale et submergée)
	protected Color color1;
	protected Color color2;
	
	//initialisation du joueur
	public Joueur(Zone localisation,Color c1, Color c2,TexteJoueur t,String c) {
		super(50,50);
		this.localisation = localisation;
        this.color1 = c1;
        this.color2 = c2;
        //on place le joueur sur la zone
        localisation.setJoueur(this);
        this.t = t;
        this.couleur = c;
    }
	
	public Zone getLocalisation(){
		return this.localisation;
	}
	
	public void setLocalisation(Zone z) {
		this.localisation = z;
	}
	

	//donne les artefacts au joueur
	public void setArtefactAIR(){
		this.artefactAir = true;
		this.t.ArteAir.changeTexte("Possede l'artefact AIR");
		this.t.notif.changeTexte("Artefact AIR trouve !");
		this.cleAir--;
	}
	public void setArtefactEAU(){
		this.artefactEau = true;
		this.t.ArteEau.changeTexte("Possede l'artefact EAU");
		this.t.notif.changeTexte("Artefact EAU trouve !");
		this.cleEau--;
	}
	public void setArtefactFEU(){
		this.artefactFeu = true;
		this.t.ArteFeu.changeTexte("Possede l'artefact Feu");
		this.t.notif.changeTexte("Artefact FEU trouve !");
		this.cleFeu--;
	}
	public void setArtefactTERRE(){
		this.artefactTerre = true;
		this.t.ArteTerre.changeTexte("Possede l'artefact TERRE");
		this.t.notif.changeTexte("Artefact TERRE trouve !");
		this.cleTerre--;
	}
	
	//verifie s'il possede tous les artefacts
	public boolean allArtefact(){
		return (this.artefactAir && this.artefactEau && 
				this.artefactFeu && this.artefactTerre);
	}
	
	//mouvement du joueur
	public void moveJoueur(String move){
		//s'il va a droite
		if (move.equals("droite")) {
			//on verifie s'il peut bouger et si la case est valide
			if (nbActions>0 && (this.localisation.hasDroite()) && 
					(this.localisation.droite.getEtat() != 2)){
				//on retire le joueur de l'ancienne zone
				this.localisation.removeJoueur(this);
				//on le place dans la nouvelle
				this.localisation = this.localisation.droite;
				this.localisation.setJoueur(this);
				//il ne peut plus bouger
				nbActions--;
			}
		}
		
		else if (move.equals("gauche")) {
			if (nbActions>0 &&(this.localisation.hasGauche()) &&
					(this.localisation.gauche.getEtat() != 2)){
				this.localisation.removeJoueur(this);
				this.localisation = this.localisation.gauche;
				this.localisation.setJoueur(this);
				nbActions--;
			}
		}
		
		else if (move.equals("haute")) {
			if (nbActions>0 &&(this.localisation.hasHaute()) &&
					(this.localisation.haute.getEtat() != 2)){
				this.localisation.removeJoueur(this);
				this.localisation = this.localisation.haute;
				this.localisation.setJoueur(this);
				nbActions--;
			}
		}
		
		else if (move.equals("basse")) {
			if (nbActions>0 && (this.localisation.hasBasse()) &&
					(this.localisation.basse.getEtat() != 2)){
				this.localisation.removeJoueur(this);
				this.localisation = this.localisation.basse;
				this.localisation.setJoueur(this);
				nbActions--;
			}
		}
	}
	
	//met son texte a jour
	public void updateTexteJoueur(int cle){
		this.t.updateTexte(this,cle);
	}
	
	//verifie s'il a perdu
	public boolean gameOver(){
		if (this.localisation.getEtat()==2){
			this.t.notif.changeTexte("Vous etes mort...");
			return true;
		}
		return false;
	}

	@Override
	protected void clicGauche() {
	}

	@Override
	protected void clicDroit() {}
}
