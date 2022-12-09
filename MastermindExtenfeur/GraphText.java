import java.util.ArrayList;
import java.util.List;

public class GraphText {
	private int x;
	private int y;
	private String text;
	private static List<GraphText> InstanceList = new ArrayList<>();

	public GraphText(String text, int x, int y) {
		this.text = text;
		this.x = x;
		this.y = y;
		InstanceList.add(this);
		draw();
	}

	public int getPosx() {
		return x;
	}

	public int getPosy() {
		return y;
	}

	public String getText() {
		return text;
	}

	public static GraphText getInstance(int id) {
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
