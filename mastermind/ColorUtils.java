public class ColorUtils {

    // Conversion bewtween our enumeration type Color and
    // java.awt.Color.

    public static java.awt.Color awtColor(Color c) { 
	switch (c) {
	case BLACK:  return java.awt.Color.BLACK;
	case WHITE:  return java.awt.Color.WHITE;
	case BLUE:  return java.awt.Color.BLUE;
	case RED:  return java.awt.Color.RED;
	case GREEN:  return java.awt.Color.GREEN;
	default:  return java.awt.Color.YELLOW;
	}
    }

    public final static java.awt.Color BROWN = new java.awt.Color(180,100,20);
}

