public class Reply {
    private int bulls, cows;

    public Reply(int bulls, int cows) {
	this.bulls = bulls;
	this.cows = cows;
    }

    public int getBulls() {return bulls;}

    public int getCows() {return cows;}

    public boolean equals(Reply other) {
	return bulls == other.bulls && cows == other.cows;
    }
    
    public String toString() {
	return bulls + " " + cows;
    }

}
