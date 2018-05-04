
/* 
   Classe principale ileInterdite.
   Ici on initialise le jeu et l'affichage.

   Pour l'affichage, on fait appel à la classe [IG.Fenetre],
   et on utilise deux méthodes de cette classe dont les
   signatures sont :

   public void ajouteElement([composant graphique]);
   public void dessineFenetre();
*/
public class IleInterdite {

	public static void main(String[] args) {
		Fenetre fenetre = new Fenetre("Ile interdite");
		Ile ile = new Ile (4,8);
		FinDeTour fintour = new FinDeTour(ile);
		
		fenetre.ajouteElement(ile);
		fenetre.ajouteElement(fintour);
		fenetre.dessineFenetre();
		
		
		// Test
		int i = 0;
		while(i !=5){
			ile.afficheIle();
			ile.randInond();
			i++;
		}
	}
}


/*
Bouton « fin de tour ». Un clic sur ce bouton procède à l’inondation de
trois zones aléatoires.
	
	On fait appel à la classe abstraite [IG.ZoneCliquable].
Pour cela, il faut définir deux méthodes correspondant aux
actions à effectuer lors d'un clic avec le bouton gauche ou
avec le bouton droit. Leurs signatures sont :

public void clicGauche();
public void clicDroit();

*/

class FinDeTour extends ZoneCliquable {
	
	 private Ile ile;
	
	 public FinDeTour(Ile ile) {
			// Création d'une zone cliquable de dimensions 80*25 pixels,
			// et contenant le texte "Valider".
			super("FinDeTour", 80, 25);
			this.ile = ile;
		    }
	 
	    public void clicGauche(){
	    	for(int i=0; i<3; i++){
	    	   ile.submergeFinDeTour();
	    	}
	    }
	    public void clicDroit() {}
}


