public class Code {
    private Color[] cols = new Color[4];

    public Code(Color[] cols) {
	for(int i=0; i<cols.length; i++) 
	    this.cols[i] = cols[i];
    }

    public Color getColor(int i) {return cols[i];}

    public String toString() {
	return cols[0] + " " + cols[1] + " " + cols[2] + " " + cols[3];
    }
}
	