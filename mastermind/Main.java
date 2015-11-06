import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	JFrame f = new JFrame("MasterMind");
	JOptionPane.showMessageDialog(f,
				      "Welcome to MasterMind.\nChoose your secret!",
				      "MasterMind",
				      JOptionPane.PLAIN_MESSAGE);

	GuessEngine engine = new NaiveEngine();
	engine.init();
	MasterMindView view = new MasterMindView(engine);
	f.getContentPane().add(view);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.pack(); 
	f.setVisible(true);
    }
}