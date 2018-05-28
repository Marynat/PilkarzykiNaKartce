package Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class WinningScreen extends JFrame {

	JLabel winner = new JLabel();
    JButton ok = new JButton("OK");
    String winn;
    
    public WinningScreen(int WIDTH, int HEIGHT, String win ) {
    	
    	winn = "Gratulacje!! Wygral‚ gracz nr " + win;
    	
    	System.out.println(winn);
    	
    	winner.setBounds(WIDTH/2 - 110, 10, 300, 30);
    	winner.setText(winn);
    	
    	add(winner);
    	
        ok.setBounds(WIDTH/2 - 50, 40, 100, 30);
        ok.setBackground(Color.YELLOW);
        ok.setForeground(Color.BLUE);
        ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainMenu(800,600);
			}
		});
        add(ok);        
       

        setLayout(null);
        setSize(WIDTH, HEIGHT);
        setBackground(Color.BLACK);
        setTitle("Pilkarzyki na kartce");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
