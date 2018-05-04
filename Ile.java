import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class Ile extends Grille {
	private Zone[][] ile;
	private int hauteur;
	private int largeur;
	
	private static Random rand = new Random();
	
	public Ile (int h, int l){
		super(h,l);
		this.hauteur = h;
		this.largeur = l;
		
		this.ile = new Zone[h][l];
		int cpt = 0;
		for (int i = 0; i < h; i++){
			for (int j = 0; j < l; j++){
				Zone z = new Zone(cpt++,this,i,j);
				ile[i][j] = z;
				this.ajouteElement(z);
			}
		}
	}
	
	// Recupérer la zone correspondant à l'identifiant num
	public Zone getZone(int num){
		//System.out.println(num);
		//System.out.println(num/(this.largeur+1));
		//System.out.println(num % (this.largeur+1));
		return this.ile[num/(this.largeur+1)][num % (this.largeur)];
	}
	
	// Inonder les zones aléatoirement lors de l'initialisation 
	public void randInond(){
		Set <Zone> zones = new LinkedHashSet<Zone>();
		do {
			int a = rand.nextInt((this.hauteur)*(this.largeur));
			if (getZone(a).getEtat() != 2){
				zones.add(getZone(a));
			}
		} while (zones.size() < 3);
		
		for(Zone z : zones){
			z.setEtat(z.getEtat()+1);
		}
	}
	
	// Prendre une zone aléatoire de l'île
	public int randGetZone(){
		return rand.nextInt((this.hauteur)*(this.largeur));
	}
	
	public int getHauteur(Ile ile){
		return ile.hauteur;
	}
	
	
	public void afficheIle (){
		for (Zone[] tabz : this.ile){
			for (Zone z : tabz){
				System.out.println(z.toString());
			}
		}
	}
	
	public Zone zoneDroite(Zone z){
		for (int i = 0; i < this.hauteur; i++){
			for (int j = 0; j < this.largeur; j++){
				if (ile[i][j] == z){
					return ile[i][j+1];	
				}
			}
		}
		return z;
	}
	
	// Submerge une zone aléatoire
	public void submergeFinDeTour(){
		int a = rand.nextInt((this.hauteur)*(this.largeur));
		getZone(a).setEtat(2);		
	}
	
}
