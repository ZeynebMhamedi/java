package ileInterdite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Ile extends Grille {
	Zone[][] ile;
	private int hauteur;
	private int largeur;
	
	protected Color cAir = new Color(245,245,245);
	protected Color cEau = new Color(185,248,248);
	protected Color cFeu = new Color(255,215,0);
	protected Color cTerre = new Color(205,183,108);
	
	
	protected List<Joueur> joueurs;
	int nbJoueurs = 0;
	int tourJoueur = 0;
	
	private static Random rand = new Random();
	
	public Ile (int h, int l){
		super(h,l);
		this.hauteur = h;
		this.largeur = l;
		this.joueurs= new ArrayList<Joueur>();
		
		
		
		this.ile = new Zone[h][l];
		int cpt = 0;
		for (int i = 0; i < h; i++){
			for (int j = 0; j < l; j++){
				Zone z = new Zone(cpt,this,i,j);
				cpt++;
				ile[i][j] = z;
				this.ajouteElement(z);
			}
		}
		for (int i = 0; i<h; i++){
			for (int j = 0; j < l; j++){
				ile[i][j].setGauche(this);
				ile[i][j].setDroite(this);
				ile[i][j].setHaute(this);
				ile[i][j].setBasse(this);
			}
		}
		
		//positionnement des elements de l'ile
		this.setZonesArtefacts();
		this.setZoneHeliport();
	}
	
	public int getHauteur(){
		return this.hauteur;
	}
	
	public int getLargeur(){
		return this.largeur;
	}	
	
	// Initialise une zone héliport
	public void setZoneHeliport(){
		int a = randGetZone();
		Zone zoneHeliport = getZone(a);
		zoneHeliport.setHeliport();
	}
	
	
	// Recupérer la zone correspondant à l'identifiant num
	public Zone getZone(int num){
		return this.ile[num/(this.largeur)][num % (this.largeur)];
	}
	
	// Prendre une zone aléatoire de l'île
	public int randGetZone(){
		return rand.nextInt((this.hauteur)*(this.largeur));
	}
	
	//place aleatoirement le joueur
	public Zone randGetZoneJoueur(){
		int res = rand.nextInt((this.hauteur)*(this.largeur));
		Zone z = this.getZone(res);
		while(z.hasJoueur() || z.isHeliport()){
			res = rand.nextInt((this.hauteur)*(this.largeur));
			z = this.getZone(res);
		}
		return z;
	}	
	
	// Inonder les zones aléatoirement lors de l'initialisation 
	public void randInond(){
		Set <Zone> zones = new LinkedHashSet<Zone>();
		do {
			int a = randGetZone();
			if (getZone(a).getEtat() != 2 && !(getZone(a).isHeliport())
					&& !(getZone(a).hasArtefact())){
				zones.add(getZone(a));
			}
		} while (zones.size() < 3);
		
		for(Zone z : zones){
			z.setEtat(z.getEtat()+1);
		}
	}
	
	
	//ajoute un joueur
	public void addJoueur(Joueur j){
		this.joueurs.add(j);
		this.nbJoueurs++;
	}
	
	//retire un joueur de l'ile
	public void removeJoueur(Joueur j){
		this.joueurs.remove(j);
		j.getLocalisation().joueur.remove(j);
		j.getLocalisation().setEtat(2);
		this.nbJoueurs--;
	}
	
	
	// Initialise 4 zones spéciales correspondant aux 4 Artefacts
	public void setZonesArtefacts(){
		Set <Zone> zones = new LinkedHashSet<Zone>();
		
		//on selectionne 4 zones aleatoirement
		do {
			int a = randGetZone();
				zones.add(getZone(a));
		} while (zones.size() < 4);
		
		//on place les artefacts
		int i=0;
		for(Zone z : zones){
			z.setArtefact(++i);
			
			//colorie la case
			switch(i){
			case 1:
				z.setBackground(this.cAir);
				break;
			case 2:
				z.setBackground(this.cEau);
				break;
			case 3:
				z.setBackground(this.cFeu);
				break;
			case 4:
				z.setBackground(this.cTerre);
				break;
			}
		}
		}
		
		

		
		
		// Met à jour les artefacts du Joueur 
		public boolean RecupereArtefact(Joueur j){
			if (j.nbActions ==0) {
				return false;
			}
			Zone z = j.getLocalisation();
			if (z.hasArtefact()){
				if ( (z.getArtefact()==1) && (j.cleAir != 0)){
					j.setArtefactAIR();
					j.t.cleAir.changeTexte(j.cleAir + " cle(s) AIR");
					return true;
				}
				else if ( (z.getArtefact()==2)  && (j.cleEau != 0)){
					j.setArtefactEAU();
					j.t.cleEau.changeTexte(j.cleEau + " cle(s) EAU");
					return true;
				}
				else if ( (z.getArtefact()==3) && (j.cleFeu != 0)){
					j.setArtefactFEU();
					j.t.cleFeu.changeTexte(j.cleFeu + " cle(s) FEU");
					return true;
				}
				else if ( (z.getArtefact()==4) && (j.cleTerre != 0)){
					j.setArtefactTERRE();
					j.t.cleTerre.changeTexte(j.cleTerre + " cle(s) TERRE");
					return true;
				}
			}
			return false;
		}
		
		//affiche l'ile dans la console
		public void afficheIle (){
			for (Zone[] tabz : this.ile){
				for (Zone z : tabz){
					System.out.println(z.toString());
				}
			}
		}
}