import java.awt.Color;

public class Zone extends ZoneCliquable{
	
	// le numero de la zone (son identité) 
	private int num;
	
	// les etats : 0 normale, 1 innondée, 2 submergée
	private int etat = 0;
	
	private Ile ile;
	
	private boolean heliport = false;
	
	// les 4 artefacts : 0 rien, 1 air,2 eau, 3 feu, 4 terre
	private int artefact = 0;
	
	// les lignes et les colonnes
	private int l,c;
	
	// les zones adjacentes
	Zone droite, gauche, haute, basse;
	
	// Si la zone est occupée par un joueur ou non 
	private boolean occupe = false;
	
	
	
	public Zone (int num, Ile ile, int i, int j){
		super(50,50);
		this.num = num;
		this.ile = ile;
		this.l = i;
		this.c = j;
		this.droite = this;
		this.droite.l = this.l + 1;
		this.gauche = this;
		this.gauche.l = this.l - 1;
		this.haute = this;
		this.haute.c = this.c - 1;
		this.basse = this;
		this.basse.c = this.c +1;
	}
	
	
	public void setEtat(int e){
		this.etat = e;
		switch (e){
			case 1:
				this.setBackground(Color.GRAY);
				break;
			case 2:
				this.setBackground(Color.BLACK);
				break;
			default:
				this.setBackground(Color.WHITE);
				break;
		}
	}
	
	public int getNum(){
		return this.num;
	}
	
	public int getEtat(){
		return this.etat;
	}
	
	public void setartefact(int e){
		this.artefact = e;
	}
	
	public void setHeliport(){
		this.heliport = true;
	}
	
	public String toString(){
		String etat = "";
		switch (this.getEtat()){
			case 0 :
				etat = "normale";
				break;
			case 1 :
				etat = "inondée";
				break;
			case 2 :
				etat = "submergée";
				break;
		}
		
		return "Zone " + Integer.toString(this.num) + " : " + etat;
	}

	@Override
	protected void clicGauche() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void clicDroit() {
		// TODO Auto-generated method stub
		
	}

}
