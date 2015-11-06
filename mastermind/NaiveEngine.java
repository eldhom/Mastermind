import java.util.*;

public class NaiveEngine implements GuessEngine {
    private List<Code> codes;
    private Code newGuess;
    private Code[] oldGuesses;
    private Reply[] oldReplies; 
    int guessCount;

    public NaiveEngine() {
	oldGuesses = new Code[10];
	oldReplies = new Reply[10];
    }
				
    public void init() { 
	guessCount = 0;
	codes = new LinkedList<Code>();
	Color[] cols = new Color[4];
	for (Color c0 : Color.values())
	    for (Color c1 : Color.values())
		for (Color c2 : Color.values())
		    for (Color c3 : Color.values()) {
			cols[0] = c0;
			cols[1] = c1;
			cols[2] = c2;
			cols[3] = c3;
			codes.add(new Code(cols));
		    }
	int index = (int)(Math.random()*codes.size());
	newGuess = codes.remove(index);
    }

    public void answerNewGuess(Reply reply)  throws ContradictionException{
	oldGuesses[guessCount] = newGuess;
        oldReplies[guessCount] = reply;
	guessCount++;
	filterCodes(reply);
	int s = codes.size();
	if (s==0) 
	    throw new ContradictionException("No solution");
	else {
	    int index = (int)(Math.random()*s);
	    newGuess = codes.remove(index);
	}
    }

    private void filterCodes(Reply r) {
	// Do nothing ...
    }

    public Code getNewGuess() {return newGuess;}

    public String explainContradiction(Code secret) {
	return "Sorry, I cannot explain."; 
    }

    public boolean moreGuessesAllowed() {return guessCount < oldGuesses.length;}

    public int oldGuesses() {return guessCount;}

    public Code getOldGuess(int i) {return oldGuesses[i];}

    public Reply getOldReply(int i) {return oldReplies[i];}
}
