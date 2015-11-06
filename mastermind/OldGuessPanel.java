import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;


 
public class OldGuessPanel extends JPanel {
    private GuessEngine engine;

    public OldGuessPanel(GuessEngine engine) {
	this.engine = engine;
	setPreferredSize(new Dimension(240,600));
	setBackground(ColorUtils.BROWN);
	setOpaque(true);
	Border b = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	Border tb = BorderFactory.createTitledBorder(b,"Old guesses");
	setBorder(tb);
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	for (int k=0; k<10; k++) {
	    for (int i=0; i<4; i++) {
		if (k<engine.oldGuesses()) {
		    g.setColor(ColorUtils.awtColor(engine.getOldGuess(k).getColor(i)));
		    g.fillOval(5+40*i,20+60*k,30,30);
		g.setColor(java.awt.Color.BLACK);
		Reply r = engine.getOldReply(k);
		g.drawString(r.getBulls() + " bull(s) and " + r.getCows() + " cow(s)",180,40+60*k);

		}
		g.drawOval(5+40*i,20+60*k,30,30);
	    }
	}
    }
}
