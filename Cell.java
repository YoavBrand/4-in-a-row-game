package Q1;
import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {
	
    private final int DISK_DIAMETER = 50;
    private final int X_POSITION = 10;
    private final int Y_POSITION = 10;
    
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(getColor());
        g.fillOval(X_POSITION, Y_POSITION, DISK_DIAMETER, DISK_DIAMETER);
    }
}
