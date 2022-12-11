import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Point {

	private Color c;
	private int x;
	private int y;
	private static List<Point> InstanceList = new ArrayList<>();

	public Point(int posx, int posy, Color c) {
		this.x = posx;
		this.y = posy;
		this.c = c;
		InstanceList.add(this);
		draw();
	}

	public Point(Point p) {
		this.x = p.x;
		this.y = p.y;
		this.c = new Color(p.getColor().getRGB());
		draw();
	}

	public Color getColor() {
		return c;
	}

	public int getPosx() {
		return x;
	}

	public int getPosy() {
		return y;
	}

	public static Point getInstance(int id) {
		return InstanceList.get(id);
	}

	public static int numberInstances() {
		return InstanceList.size();
	}

	public static void clear() {
		InstanceList = new ArrayList<>();
	}

	private void draw() {
		Fenetre.colorsPanel.repaint();
	}
}
