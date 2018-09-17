package Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import Game.Pvp;
import Game.MainScreenFrame;
import Game.Pvee;
//import Game.Pveh;

public class NewGame extends JFrame{

	JButton pvp = new JButton("Gracz vs Gracz");
	JButton pvee = new JButton("Gracz vs Komputer");
	JButton pveh = new JButton("Gracz vs Komputer (trudny)");
	JButton back = new JButton("Wstecz");

	public NewGame() {
		pvp.setBounds(300, 40, 200, 30);
		pvp.setBackground(Color.YELLOW);
		pvp.setForeground(Color.BLUE);
		pvp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Pvp comp = new Pvp();
				comp.setVisible(true);
			}
		});
		add(pvp);

		pvee.setBounds(300, 90, 200, 30);
		pvee.setBackground(Color.YELLOW);
		pvee.setForeground(Color.BLUE);
		pvee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Pvee comp2 = new Pvee();
				comp2.setVisible(true);
			}
		});
		add(pvee);


		back.setBounds(300, 190, 200, 30);
		back.setBackground(Color.YELLOW);
		back.setForeground(Color.BLUE);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainMenu(800,600);
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