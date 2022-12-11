import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ColorsPanel extends JPanel {
	public void paintComponent(Graphics g) {
		for (int i = 0; i < Point.numberInstances(); i++) {
			Point p = Point.getInstance(i);
			g.setColor(p.getColor());
			g.fillOval(p.getPosx(), p.getPosy(), 20, 20);
		}
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 14));
		for (int i = 0; i < GraphText.numberInstances(); i++) {
			GraphText t = GraphText.getInstance(i);
			g.drawString(t.getText(), t.getPosx(), t.getPosy());
		}
	}
}
