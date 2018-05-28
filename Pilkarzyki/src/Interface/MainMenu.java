package Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;



public class MainMenu extends JFrame {

	
    JButton newGame = new JButton("Nowa Gra");
    JButton settings = new JButton("Ustawienia"); //buttony
    JButton exit = new JButton("Wyjscie");

    public MainMenu(int WIDTH, int HEIGHT) {		//glowna klasa mainmanu dziedziczaca po jframe - ustawiawianie jej własciwości i przycisków w odpowiednich miejscach
        newGame.setBounds(WIDTH/2 - 100, 40, 200, 30);
        newGame.setBackground(Color.YELLOW);
        newGame.setForeground(Color.BLUE);
        newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		//action listener -  czeka az przycisk zostanie nacisniety
				dispose();	//usuwamy obiekt mainmenu po klikniecu
				new NewGame();	//tworzymy obiekt okna nowej gry po kliknieciu
			}
		});
        add(newGame);
        
        settings.setBounds(WIDTH/2 - 100, 90, 200, 30);
        settings.setBackground(Color.YELLOW);
        settings.setForeground(Color.BLUE);
        settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Settings();	//tworzymy obiekt okna settings po kliknieciu
			}
		});
        add(settings);
        
        exit.setBounds(WIDTH/2 - 100, 140, 200, 30);
        exit.setBackground(Color.YELLOW);
        exit.setForeground(Color.BLUE);
        exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
        add(exit);
        
       

        setLayout(null);
        setSize(WIDTH, HEIGHT);
        setBackground(Color.BLACK);
        setTitle("Pilkarzyki na kartce");		//główne ustawienia okna w swingu
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu(800, 600);		//utworzenie obiektu
    }
}