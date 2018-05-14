package ileInterdite;

/*classe qui permet d'afficher les caracteristiques du joueur*/

public class TexteJoueur {

	//differentes caracteristiques
	protected Texte joueur;
	protected Texte notif;
	protected Texte actionSpe;
	protected Texte cleAir;
	protected Texte cleEau;
	protected Texte cleFeu;
	protected Texte cleTerre;
	protected Texte ArteAir;
	protected Texte ArteEau;
	protected Texte ArteFeu;
	protected Texte ArteTerre;
	protected Texte sep;
	
	//initialisation des caracteristiques
	public TexteJoueur(String c){
		this.joueur = new Texte("Joueur " + c);
		this.notif = new Texte("Bienvenue sur l'ile interdite !");
		this.actionSpe = new Texte("Pas d'action speciale");
		this.cleAir = new Texte("0 cle AIR");
    	this.cleEau = new Texte("0 cle EAU");
    	this.cleFeu = new Texte("0 cle FEU");
    	this. cleTerre = new Texte("0 cle TERRE");
    	this.ArteAir = new Texte("Pas artefact AIR");
    	this. ArteEau = new Texte("Pas artefact EAU");
    	this.ArteFeu = new Texte("Pas artefact FEU");
    	this.ArteTerre = new Texte("Pas artefact TERRE");
    	this.sep = new Texte(" ");
	}
	
	//mise a jour
	public void updateTexte(Joueur j,int cle){
    	// Mise à jour du texte des cles
		if (j.helico) {
			this.actionSpe.changeTexte("Helicoptere disponible !");
		}
		if(j.sacDeSable) {
			this.actionSpe.changeTexte("Sac de sable disponible !");
		}
		
		if (cle ==0) {
			this.notif.changeTexte(" ");
			return;
		}
    	if (cle == 1){
    		this.cleAir.changeTexte(j.cleAir + " cle(s) AIR");
    		this.notif.changeTexte("Cle AIR obtenue !");
    		return;
    	}
    	if (cle == 2){
    		this.cleEau.changeTexte(j.cleEau + " cle(s) EAU");
    		this.notif.changeTexte("Cle EAU obtenue !");
    		return;
    	}
    	if (cle == 3){
    		this.cleFeu.changeTexte(j.cleFeu + " cle(s) FEU");
    		this.notif.changeTexte("Cle FEU obtenue !");
    		return;
    	}
    	if (cle == 4){
    		this.cleTerre.changeTexte(j.cleTerre + " cle(s) TERRE");
    		this.notif.changeTexte("Cle TERRE obtenue !");
    		return;
    	}
    }
}