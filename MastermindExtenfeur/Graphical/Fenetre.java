package Graphical;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import java.awt.*;

public class Fenetre extends JFrame {

	private static boolean thereIsInstance = false;
	private static Fenetre THEFENETRE = null;
	private static JLabel round = new JLabel("Vous êtes le décodeur", SwingConstants.CENTER);
	public static JLabel score = new JLabel("Ordi 0 - Humain 4", SwingConstants.CENTER);
	private static JPanel codePanel = new JPanel(new BorderLayout());;
	private static JPanel numberPanel = new JPanel(new BorderLayout());
	private static JPanel mainPanel = new JPanel(new BorderLayout());

	private Fenetre(int w, int h, String titre) {
		setTitle(titre);
		setSize(w, h);
		JButton button = Button("Ok");
		JButton button2 = Button("Ok");
		JPanel codeField = textField("code", 10, "^[a-zA-Z]{0,4}$");
		JPanel numberField = textField("nombre", 10, "^[0-9]*$");

		codePanel.add(button, BorderLayout.LINE_END);
		codePanel.add(codeField, BorderLayout.LINE_START);

		numberPanel.add(button2, BorderLayout.LINE_END);
		numberPanel.add(numberField, BorderLayout.LINE_START);

		JPanel scorePanel = new JPanel(new BorderLayout());
		scorePanel.add(score, BorderLayout.PAGE_END);
		scorePanel.add(round, BorderLayout.CENTER);

		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

		mainPanel.setBorder(padding);
		mainPanel.add(codePanel, BorderLayout.PAGE_END);
		mainPanel.add(scorePanel, BorderLayout.PAGE_START);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(mainPanel);
		setLocationRelativeTo(null);
		setVisible(true);
		setAlwaysOnTop(true);
		setResizable(false);
		thereIsInstance = true;
	}

	private JButton Button(String text) {
		Font font = new Font("Arial", Font.BOLD, 15);
		JButton button = new JButton(text);
		button.setFont(font);
		button.setBackground(Color.GRAY);
		button.setForeground(Color.WHITE);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		return button;
	}

	private JPanel textField(String text, int columns, String regex) {
		JPanel panel = new JPanel(new BorderLayout());
		Font fieldFont = new Font("Arial", Font.PLAIN, 20);
		JTextField field = new JTextField();
		field.setFont(fieldFont);
		field.setBackground(Color.white);
		field.setForeground(Color.gray.brighter());
		field.setForeground(Color.BLACK);
		field.setColumns(columns);
		JLabel label = new JLabel(text);
		panel.add(label, BorderLayout.PAGE_START);
		panel.add(field, BorderLayout.PAGE_END);
		((AbstractDocument) field.getDocument()).setDocumentFilter(new RegexFilter(regex));
		return panel;
	}

	public static void createFenetre(int w, int h, String titre) {
		if (!thereIsInstance)
			THEFENETRE = new Fenetre(w, h, titre);
	}

	public static Fenetre getInstance() {
		return Fenetre.THEFENETRE;
	}

	public static void setRoundType(boolean isHumanRound) {
		if (isHumanRound) {
			round.setText("Vous êtes le codeur");
			mainPanel.remove(codePanel);
			mainPanel.add(numberPanel, BorderLayout.PAGE_END);
		} else {
			round.setText("Vous êtes le décodeur");
			mainPanel.remove(numberPanel);
			mainPanel.add(codePanel, BorderLayout.PAGE_END);
		}
	}

	public static void showEnd(int scoreOrdi, int scoreHum) {
		getInstance().getContentPane().remove(mainPanel);
		mainPanel = new JPanel(new BorderLayout());
		String endText = "Egalité !";
		if (scoreOrdi < scoreHum)
			endText = "Victoire de l'ordinateur !";
		else if (scoreOrdi > scoreHum)
			endText = "Vous avez gagné !";
		JLabel label = new JLabel(endText);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		mainPanel.add(label, BorderLayout.CENTER);
		getInstance().getContentPane().add(mainPanel);
	}
}
