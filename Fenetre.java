package ileInterdite;



import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;


/*
La fenetre permet l'affichage de l'ile, des boutons et du texte 
 */

public class Fenetre extends JFrame {
	
    private JPanel elements;
    
    //séparation des elements pour affichage(le texte, l'ile, les boutons)
    private JPanel texte;
    private JPanel ile;
    private JPanel ft;
    private JPanel b;
    
    //initialisation de la fenetre
    public Fenetre(String nom) {
    	//init
    	this.setTitle("L'ile Interdite");
    	this.setSize(1000,650);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setLocationRelativeTo(null);
    	texte = new JPanel();
    	
    	//init des elem
    	texte.setLayout(new BoxLayout(texte,BoxLayout.Y_AXIS));
    	ile = new JPanel();
    	ile.setLayout(new BoxLayout(ile,BoxLayout.LINE_AXIS));
    	ft = new JPanel();
    	ft.setLayout(new BoxLayout(ft,BoxLayout.LINE_AXIS));
    	b = new JPanel();
    	b.setLayout(new BoxLayout(b,BoxLayout.LINE_AXIS));;
    }

    //ajout de texte
    public void ajouteTexte(JComponent element) {
    	texte.add(element);
    }
    
    //ajout ile
    public void ajouteIle(JComponent element){
    	ile.add(element);
    }
    
    //ajout bouton fin de tour
    public void ajouteFinTour(JComponent element){
    	element.setBackground(new Color(0,255,255));
    	ft.add(element);
    }
    
    //ajout bouton directions
    public void ajouteBouton(JComponent element){
    	element.setBackground(new Color(0,206,209));
    	b.add(element);
    }
    
    //dessine la fenetre
    public void dessineFenetre() {
    	
    	//on rassemble les elements
	    JPanel a = new JPanel();
	    a.setBackground(new Color(65,105,225));
	    texte.setBackground(new Color(170,200,250));
	    a.add(texte,BorderLayout.WEST);
	    a.add(ile,BorderLayout.CENTER);
	    a.add(ft,BorderLayout.EAST);
	    a.add(b,BorderLayout.SOUTH);
	    
	    //on affiche
	    this.getContentPane().add(a);
		this.setVisible(true);
	
    }
}
