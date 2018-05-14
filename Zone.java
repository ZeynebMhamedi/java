package ileInterdite;
import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Zone extends ZoneCliquable{
	
	// le numero de la zone (son identité) 
	private int num;
	
	// les etats : 0 normale, 1 innondée, 2 submergée
	private int etat = 0;
	
	private Ile ile;
	
	private boolean heliport = false;
	
	// Si la zone est occupée par un joueur ou non 
	protected ArrayList<Joueur> joueur;
	
	//s'il a un artefact
	private boolean hasArtefact = false;
	
	// les 4 artefacts : 0 rien, 1 air,2 eau, 3 feu, 4 terre
	private int artefact = 0;
	
	// les lignes et les colonnes
	private int l,c;
	
	// les zones adjacentes
	Zone droite, gauche, haute, basse;
	
	private Boolean hasGauche = false;
	private Boolean hasDroite = false;
	private Boolean hasHaute = false;
	private Boolean hasBasse = false;
	

	
	
	
	//initialisation de la zone
	public Zone (int num, Ile ile, int i, int j){
		super(50,50);
		this.num = num;
		this.ile = ile;
		this.l = i;
		this.c = j;
		this.joueur = new ArrayList<Joueur>();
	}
	
	public int getLigne(){
		return this.l;
	}
	
	public int getColonne(){
		return this.c;
	}
	
	public int getNum(){
		return this.num;
	}
	
	public int getEtat(){
		return this.etat;
	}
	
	public boolean isHeliport(){
		return this.heliport;
	}
	
	public int getArtefact(){
		return this.artefact;
	}
	
	//verifie si cette zone a un artefact
	public boolean hasArtefact(){
		if (this.hasArtefact){
		 return true ; 
		}
		return false;
	}
	
	//on met un artefact
	public void setArtefact(int e){
		this.artefact = e;
		this.hasArtefact = true;
	}
	
	//on met l'heliport et on le colorie en violet
	public void setHeliport(){
		this.heliport = true;
		this.setBackground(new Color(118,0,181));
	}
	
	//definit l'etat de la zone (normale,innondee ou submergee et la
	//colorie
	public void setEtat(int e){
		this.etat = e;
		switch (e){
			case 1:
				this.setBackground(new Color(170,200,250));;
				break;
			case 2:
				this.setBackground(new Color(65,105,225));
				break;
			default:
				this.setBackground(new Color(238,221,130));
				break;
		}
		if((!this.joueur.isEmpty()) && e !=2){
			this.setJoueur(this.getJoueur());
		}
	}
	
	
	public boolean hasJoueur(){
		return !this.joueur.isEmpty();
	}
	
	
	public Joueur getJoueur(){
		for(Joueur j : this.joueur){
			return j;
		}
		return null;
	}
		
	//on place un joueur et on colorie la zone
	public void setJoueur(Joueur j){
		this.joueur.add(j);
		if (this.getEtat() != 2){
			this.setBackground(j.color1);
		}
		else {this.setBackground(j.color2);}
	}
	
	//on retire le joueur de la zone (deplacement ou mort)
	//on remet la zone de la bonne couleur
	public void removeJoueur(Joueur j){
		this.joueur.remove(j);
		
		if (this.isHeliport()&&this.joueur.isEmpty()) {
			this.setBackground(new Color(118,0,181));
			return;
		}
		if (this.hasArtefact() && this.joueur.isEmpty()){
			switch(this.artefact){
			case 1:
				this.setBackground(this.ile.cAir);
				break;
			case 2:
				this.setBackground(this.ile.cEau);
				break;
			case 3:
				this.setBackground(this.ile.cFeu);
				break;
			case 4:
				this.setBackground(this.ile.cTerre);
				break;
			}
			return;
		}
		if(! this.joueur.isEmpty()){
				this.setBackground(this.getJoueur().color1);
				return;
			}
		this.setEtat(this.getEtat());
	}
	

	//on verifie s'il a des zones adjacentes
	public Boolean hasGauche(){
		return this.hasGauche;
	}
	public Boolean hasDroite(){
		return this.hasDroite;
	}
	public Boolean hasHaute(){
		return this.hasHaute;
	}
	public Boolean hasBasse(){
		return this.hasBasse;
	}
	
	//on definit la zone adjacente
	public void setGauche(Ile i) {
		int a = this.c-1;
		if (a >= 0){
			this.gauche = i.ile[this.l][a];
			this.hasGauche = true;
			return;
		}
	}
	public void setDroite(Ile i) {
		int a = this.c+1;
		if (a < i.getLargeur()){
			this.droite = i.ile[this.l][a];
			this.hasDroite = true;
			return;
		}
	}
	public void setHaute(Ile i) {
		if (this.l != 0){
			this.haute = i.ile[this.l-1][this.c];
			this.hasHaute = true;
		}
	}
	public void setBasse(Ile i) {
		if (this.l != i.getHauteur()-1){
			this.basse = i.ile[this.l+1][this.c];
			this.hasBasse = true;
		}
	}
	
	//on verifie que les zones sont egales(meme ligne et meme colonne
	@Override
	public boolean equals(Object o){
		if (!(o instanceof Zone)){
			return false;
		}
		Zone z = (Zone) o;
		if(z.l == this.l && z.c==this.c){
			return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		String etat = "";
		switch (this.getEtat()){
			case 0 :
				etat = "normale";
				break;
			case 1 :
				etat = "inondee";
				break;
			case 2 :
				etat = "submergee";
				break;
		}
		return "Zone " + Integer.toString(this.num) + " : " + etat;
	}
	
	@Override
	protected void clicGauche() {
		Joueur j = this.ile.joueurs.get(ile.tourJoueur);
		//permet de finir le jeu sur l'heliport
		if (this.heliport && j.allArtefact()) {
			j.t.notif.changeTexte("Vous avez gagne !!!");
			this.ile.removeJoueur(j);
			this.setBackground(new Color(118,0,181));
		}
		//permet d'assecher une zone
		if ((j.getLocalisation().equals(this) ||
			j.getLocalisation().equals(this.gauche) ||
			j.getLocalisation().equals(this.droite) ||
			j.getLocalisation().equals(this.haute) ||
			j.getLocalisation().equals(this.basse)) &&
			this.getEtat() == 1 && j.nbActions>0)
		{
			this.setEtat(0);
			j.nbActions--;
			if (j.getLocalisation() == this){
				this.setJoueur(j);
			}
			return;
		}
		//utilisation de l'action de sac de sable
		if (j.sacDeSable && this.getEtat()==1) {
			this.setEtat(0);
			j.sacDeSable=false;
			j.t.actionSpe.changeTexte("Pas d'action speciale");
		}
		
		//utilisation de l'action speciale helicoptere
		if(j.helico){
			ArrayList<Joueur> origine = (ArrayList<Joueur>) j.getLocalisation().joueur.clone();
			for (Joueur a : origine) {
				a.getLocalisation().removeJoueur(a);
				a.setLocalisation(this);
				
				this.setJoueur(a);
			}
			j.helico = false;
			j.t.actionSpe.changeTexte("Pas d'action speciale");
		}
	}

	@Override
	protected void clicDroit() {
		Joueur j = this.ile.joueurs.get(ile.tourJoueur);
		if (ile.RecupereArtefact(j)){
			j.nbActions --;
		}
	}
}