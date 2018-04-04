package Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import Game.MainScreenComponents;
import Game.MainScreenFrame;

public class NewGame extends JFrame{

	JButton pvp = new JButton("Gracz vs Gracz");
	JButton pve = new JButton("Gracz vs Komputer (£atwy)");
	JButton back = new JButton("Wstecz");

	public NewGame() {
		pvp.setBounds(300, 40, 200, 30);
		pvp.setBackground(Color.YELLOW);
		pvp.setForeground(Color.BLUE);
		pvp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainScreenComponents comp = new MainScreenComponents();
				comp.setVisible(true);
			}
		});
		add(pvp);

		pve.setBounds(300, 90, 200, 30);
		pve.setBackground(Color.YELLOW);
		pve.setForeground(Color.BLUE);
		pve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(pve);

		back.setBounds(300, 140, 200, 30);
		back.setBackground(Color.YELLOW);
		back.setForeground(Color.BLUE);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainMenu(800, 600);
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