package Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Settings extends JFrame {
	JButton graph = new JButton("Grafika");
	JButton sound = new JButton("Dzwiek");
	JButton back = new JButton("Wstecz");

	public Settings() {
		graph.setBounds(300, 40, 200, 30);
		graph.setBackground(Color.YELLOW);
		graph.setForeground(Color.BLUE);
		graph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(graph);

		sound.setBounds(300, 90, 200, 30);
		sound.setBackground(Color.YELLOW);
		sound.setForeground(Color.BLUE);
		sound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(sound);

		back.setBounds(300, 140, 200, 30);
		back.setBackground(Color.YELLOW);
		back.setForeground(Color.BLUE);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainMenu();
			}
		});
		add(back);

		setLayout(null);
		setSize(800, 600);
		setBackground(Color.BLACK);
		setTitle("Pilkarzyki na kartce");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}