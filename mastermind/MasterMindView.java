import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class MasterMindView extends JPanel implements ActionListener {

    private GuessEngine engine;
    private JComboBox bullCombo, cowCombo;

    public MasterMindView (GuessEngine engine) {
	this.engine = engine;
	setBackground(ColorUtils.BROWN);
	NewGuessPanel newPanel = new NewGuessPanel(engine);
	OldGuessPanel oldPanel = new OldGuessPanel(engine);
        Integer[] choices = {0,1,2,3,4};
	bullCombo = new JComboBox(choices);
	cowCombo = new JComboBox(choices);
	JButton okButton = new JButton("Reply OK");
	// Sun recommends not to put borders directly on JComboBoxes; 
	// hence we place them in JPanels, to which we add borders.
	JPanel bullPanel = new JPanel();
	JPanel cowPanel = new JPanel();
	Border b1 = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	Border b2 = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	Border tb1 = BorderFactory.createTitledBorder(b1,"Bulls");
	Border tb2 = BorderFactory.createTitledBorder(b2,"Cows");
	bullPanel.setBackground(ColorUtils.BROWN);
	cowPanel.setBackground(ColorUtils.BROWN);
	bullPanel.setBorder(tb1);
	cowPanel.setBorder(tb2);
	bullPanel.add(bullCombo); 
	cowPanel.add(cowCombo);
	setLayout(new BorderLayout());
	add(oldPanel,BorderLayout.NORTH);
	add(newPanel,BorderLayout.WEST);
	add(bullPanel,BorderLayout.CENTER);
	add(cowPanel,BorderLayout.EAST);
	add(okButton,BorderLayout.SOUTH);
	okButton.addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) {
	int bulls = bullCombo.getSelectedIndex();
	int cows = cowCombo.getSelectedIndex();
	if (bulls==4 && cows==0) checkAgain("Yippee!\n");
        else if (engine.moreGuessesAllowed()) {
	    try {
		engine.answerNewGuess(new Reply(bulls,cows));
	    } catch (ContradictionException ex) {
		String secret = JOptionPane.showInputDialog(this,"Contradictory answers! Tell me your secret\n (e.g. Blue Yellow Red Blue)");
		Color[] cols = new Color[4]; 
		Scanner s = new Scanner(secret);
		for (int i=0; i<cols.length; i++)
		    cols[i] = Enum.valueOf(Color.class,s.next().toUpperCase());
		String explanation = engine.explainContradiction(new Code(cols));
		JOptionPane.showMessageDialog(this,explanation,"Explanation",JOptionPane.PLAIN_MESSAGE);
		checkAgain("");
	    }
	} 
	else checkAgain("I cannot guess your secret.\n");
	repaint();
    }

    private void checkAgain(String messInit) {
	int yesno = JOptionPane.showConfirmDialog(this,messInit + "Do you want to play again?\nIf so,choose your secret.",
						  "Play again?",JOptionPane.YES_NO_OPTION);
	if (yesno==JOptionPane.YES_OPTION) {
	    engine.init();
	} else
	    System.exit(0);
    }
}

    
	
    