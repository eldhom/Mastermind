public interface GuessEngine { 

    // For documentation, see lab instruction.

    public void init();
    public Code getNewGuess();
    public void answerNewGuess(Reply reply) throws ContradictionException;
    public String explainContradiction(Code secret);

    public boolean moreGuessesAllowed();
    public int oldGuesses();
    public Code getOldGuess(int i);
    public Reply getOldReply(int i);

}
