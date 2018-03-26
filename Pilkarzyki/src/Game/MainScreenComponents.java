package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainScreenComponents extends JPanel implements ActionListener{
	
	private final int DELAY = 150;
    private Timer timer;
    Graphics g = null;
	
	public MainScreenComponents() {
		initTimer();
		paintComponent(g);
	}

	private void initTimer() {

        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public Timer getTimer() {
        
        return timer;
    }
	
	private void draw(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawString("Pilkarzyki na kartce", 300, 50);
		g2d.drawLine(10, 10, 100, 10);
		
	}
	
	

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		
	}
}

