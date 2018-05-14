package ileInterdite;

import javax.swing.JComponent;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;

//classe permettant le dessin de l'ile

public class Grille extends JPanel {
	
	//init
    public Grille (int hauteur, int largeur) {
    	setLayout(new GridLayout(hauteur, largeur, 5, 5));
    	this.setBackground(new Color(65,105,225));
    }
    
    //on ajoute les zones
    public void ajouteElement (JComponent element) {
    	element.setBackground(new Color(238,221,130));
    	this.add(element);
    }
}
