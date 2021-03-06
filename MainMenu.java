package Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class MainMenu extends JFrame {


    JButton newGame = new JButton("Nowa Gra");
    JButton settings = new JButton("Ustawienia");
    JButton exit = new JButton("Wyj�cie");

    public MainMenu() {
        newGame.setBounds(300, 40, 200, 30);
        newGame.setBackground(Color.YELLOW);
        newGame.setForeground(Color.BLUE);
        newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new NewGame();
			}
		});
        add(newGame);
        
        settings.setBounds(300, 90, 200, 30);
        settings.setBackground(Color.YELLOW);
        settings.setForeground(Color.BLUE);
        settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Settings();
			}
		});
        add(settings);
        
        exit.setBounds(300, 140, 200, 30);
        exit.setBackground(Color.YELLOW);
        exit.setForeground(Color.BLUE);
        exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
        add(exit);
       

        setLayout(null);
        setSize(800, 600);
        setBackground(Color.BLACK);
        setTitle("Pilkarzyki na kartce");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
