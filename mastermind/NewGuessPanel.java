import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class NewGuessPanel extends JPanel { 
    private GuessEngine engine;

    public NewGuessPanel(GuessEngine engine) {
	this.engine = engine;
	setPreferredSize(new Dimension(160,70));
	setBackground(ColorUtils.BROWN);
	setOpaque(true);
	Border b = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	Border tb = BorderFactory.createTitledBorder(b,"New guess");
	setBorder(tb);
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Code guess = engine.getNewGuess();
	for (int i=0; i<4; i++) {
	    g.setColor(ColorUtils.awtColor(guess.getColor(i)));
	    g.fillOval(5+40*i,20,30,30);
	    g.setColor(java.awt.Color.BLACK);
	    g.drawOval(5+40*i,20,30,30);
	}
    }
}

