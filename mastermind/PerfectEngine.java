import java.util.*;

public class PerfectEngine implements GuessEngine {
    private List<Code> codes;
    private Code newGuess;
    private Code[] oldGuesses;
    private Reply[] oldReplies; 
    int guessCount;

    public PerfectEngine() {
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


	//Compares the number of cows and bulls to the reply
	//And removes the code if they don't match
    private void filterCodes(Reply r) {
		for(int i=codes.size()-1; i>=0; i--) {
			int cows 	= getCows(newGuess, codes.get(i));
			int bulls 	= getBulls(newGuess, codes.get(i));
			if(cows != r.getCows() || bulls != r.getBulls()) {
				codes.remove(i);
			}
		}
    }

    public Code getNewGuess() {return newGuess;}

	//Compares the code to the secret and returns the number of bulls
	private int getBulls(Code secret, Code compare) {
		int bulls = 0;
		for(int i=0; i<4; i++) {
			if(secret.getColor(i) == compare.getColor(i)) {
				bulls++;
			}
		}
		return bulls;
	}

	//Compares the code to the secret and returns the number of cows
	private int getCows(Code secret, Code compare) {
		int cows = 0;
		List<Integer> skipIndex = new ArrayList<Integer>();
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(!skipIndex.contains(new Integer(j))) {
					if(secret.getColor(i) == compare.getColor(j)) {
						if(secret.getColor(j) != compare.getColor(j)) {
							if(secret.getColor(i) != compare.getColor(i)) {
								cows++;
								skipIndex.add(j);
								break;	
							}
						}
					}
				}
			}
		}
		return cows;
	}

	//Compares the secret to the old guesses
	//And compares the result to the old reply the user made
    public String explainContradiction(Code secret) {
		if(codes.contains(secret)) {
			return "I made a bad guess";
		} else {
				for(int i=0; i<guessCount; i++) {
				int cows 	= getCows(secret, oldGuesses[i]);
				int bulls 	= getBulls(secret, oldGuesses[i]);
				if(cows != oldReplies[i].getCows() || bulls != oldReplies[i].getBulls()) {
					return "Guess " + (i+1) + " gave wrong reply. Should have given " + bulls + " Bulls and " + cows + " Cows." ;
				}
			}
		}

	    
		return "Sorry, I cannot explain."; 
    }

    public boolean moreGuessesAllowed() {return guessCount < oldGuesses.length;}

    public int oldGuesses() {return guessCount;}

    public Code getOldGuess(int i) {return oldGuesses[i];}

    public Reply getOldReply(int i) {return oldReplies[i];}
}
