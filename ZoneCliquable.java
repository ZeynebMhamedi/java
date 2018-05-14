package ileInterdite;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*permet interaction avec les clics de la souris*/

public abstract class ZoneCliquable extends JPanel implements MouseListener {

    private Texte texte;

    public ZoneCliquable(int x, int y) {
	setPreferredSize(new Dimension(x, y));
	addMouseListener(this);
	setBackground(Color.WHITE);
    }
    
    public ZoneCliquable(String texte, int x, int y) {
	this(x, y);
	this.texte = new Texte(texte);
	this.add(this.texte);
    }

    public void changeTexte(String texte) {
	this.texte.changeTexte(texte);
    }
    
    protected abstract void clicGauche();
    protected abstract void clicDroit();
    
    public void mouseClicked(MouseEvent e) {
    	if (SwingUtilities.isRightMouseButton(e)) {
    	    this.clicDroit();
    	} else {
    	    this.clicGauche();
    	}
     }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.red);

        for (int i = 0; i <= 100000; i++) {
          Dimension size = getSize();
          int w = size.width ;
          int h = size.height;

          g2d.drawLine(w, h, w, h);
        }
      }
}