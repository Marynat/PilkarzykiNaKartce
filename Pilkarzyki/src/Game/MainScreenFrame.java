package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

public class MainScreenFrame extends JFrame implements KeyListener{
	/*
	 * public MainScreenFrame() { init(); }
	 * 
	 * private void init() {
	 * 
	 * final MainScreenComponents surface = new MainScreenComponents();
	 * this.add(surface);
	 * 
	 * addWindowListener(new WindowAdapter() {
	 * 
	 * @Override public void windowClosing(WindowEvent e) { Timer timer =
	 * surface.getTimer(); timer.stop(); } });
	 * 
	 * setTitle("Pilkarzyki na kartce"); setLayout(null); setSize(800, 600);
	 * setLocationRelativeTo(null); setIgnoreRepaint( true );
	 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setVisible(true); }
	 */

	Canvas canvas = new Canvas();
	
	JButton exit = new JButton("Wyjscie");
	
	Boolean mainLoop = true;
	

	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice gd = ge.getDefaultScreenDevice();
	GraphicsConfiguration gc = gd.getDefaultConfiguration();

	Graphics graphics = null;
	Graphics2D g2d = null;
	Color background = Color.GREEN;
	Random rand = new Random();

	BufferedImage bi = gc.createCompatibleImage(800, 400);

	public MainScreenFrame() {
		setIgnoreRepaint(true);
		

		// Create canvas for painting...
		canvas.setIgnoreRepaint(true);
		canvas.setSize(800, 600);

		// Add canvas to game window...
		add(canvas);
		
		pack();
		setVisible(true);

		// Create BackBuffer...
		canvas.createBufferStrategy(2);

		// Get graphics configuration...

		// Create off-screen drawing surface

		// Objects needed for rendering...

		// Variables for counting frames per seconds
		int fps = 0;
		int frames = 0;
		long totalTime = 0;
		long curTime = System.currentTimeMillis();
		long lastTime = curTime;
		
		double centerX = 400;
		double centerY = 150;
		
		Point last = MouseInfo.getPointerInfo().getLocation();

		while (mainLoop) {
			try {
				// count Frames per second...
				lastTime = curTime;
				curTime = System.currentTimeMillis();
				totalTime += curTime - lastTime;
				if (totalTime > 1000) {
					totalTime -= 1000;
					fps = frames;
					frames = 0;
				}
				++frames;

				addKeyListener(this);

				// clear back buffer...
				g2d = bi.createGraphics();
				g2d.setColor(background);
				g2d.fillRect(0, 0, 800, 600);

				// draw some rectangles...
				
				for (int i = 0; i < 9; ++i) {
					g2d.setColor(Color.BLACK);
					int x = 320 + (i* 20);
					int y = 50;
					int w = 320 + (i* 20);
					int h = 250;
					g2d.drawLine(x, y, w, h);
				}
				for (int i = 0; i < 11; ++i) {
					g2d.setColor(Color.BLACK);
					int x = 320;
					int y = 50 + (i*20);
					int w = 480;
					int h = 50 + (i*20);
					g2d.drawLine(x, y, w, h);
				}
				//upper Goal Post
				g2d.drawLine(380, 30, 420, 30);
				g2d.drawLine(380, 30, 380, 50);
				g2d.drawLine(420, 30, 420, 50);
				
				//lower goal post
				g2d.drawLine(380, 270, 420, 270);
				g2d.drawLine(380, 270, 380, 250);
				g2d.drawLine(420, 270, 420, 250);
				
				
				g2d.fill(new Ellipse2D.Double(centerX - 2.5, centerY - 2.5, 5, 5));

				// display frames per second...
				//g2d.setFont(new Font("Courier New", Font.PLAIN, 12));
				g2d.setColor(Color.RED);
				g2d.drawString(String.format("FPS: %s", fps), 20, 20);
				
				Point p = MouseInfo.getPointerInfo().getLocation();
                if(p.x != last.x || p.y != last.y)
                {
                    last = p;
                }
                
                g2d.drawString(String.format("Mysz x: %s y: %s", p.x, p.y), 700, 20);
                
                
                double theta = Math.atan2(p.x - 960, p.y - 390);
                theta += Math.PI/2.0;
                double angle = Math.toDegrees(theta);

                    if(angle < 0){
                        angle += 360;
                    }

                
                if(angle > 337.5 || angle <= 22.5 ) {
                	g2d.drawLine((int)centerX, (int)centerY, (int)centerX-20, (int)centerY);
                }else if(angle > 22.5 && angle <= 67.5 ) {
                	g2d.drawLine((int)centerX, (int)centerY, (int)centerX-20, (int)centerY+20);
                }else if(angle > 67.5 && angle <= 112.5 ) {
                	g2d.drawLine((int)centerX, (int)centerY, (int)centerX, (int)centerY+20);
                }else if(angle > 112.5 && angle <= 157.5 ) {
                	g2d.drawLine((int)centerX, (int)centerY, (int)centerX+20, (int)centerY+20);
                }else if(angle > 157.5 && angle <= 202.5 ) {
                	g2d.drawLine((int)centerX, (int)centerY, (int)centerX+20, (int)centerY);
                }else if(angle > 202.5 && angle <= 247.5 ) {
                	g2d.drawLine((int)centerX, (int)centerY, (int)centerX+20, (int)centerY-20);
                }else if(angle > 247.5 && angle <= 292.5 ) {
                	g2d.drawLine((int)centerX, (int)centerY, (int)centerX, (int)centerY-20);
                }else if(angle > 292.5 && angle <= 337.5 ) {
                	g2d.drawLine((int)centerX, (int)centerY, (int)centerX-20, (int)centerY-20);
                }
                
                if(p.x >= 1315 && p.x <= 1360 && p.y >= 205 && p.y <= 235) {
                	mainLoop = false;
                	System.out.println("Zatrzymano petle gg");
                }
                


				// Blit image and flip...
				BufferStrategy buffer = canvas.getBufferStrategy();
				if (buffer == null) {
				       this.createBufferStrategy(3); 
				       buffer = this.getBufferStrategy(); 
				}
				graphics = buffer.getDrawGraphics();
				graphics.drawImage(bi, 0, 0, null);
				if (!buffer.contentsLost())
					buffer.show();


				// Let the OS have a little time...
				Thread.yield();
			} finally {
				// release resources
				if (graphics != null)
					graphics.dispose();
				if (g2d != null)
					g2d.dispose();
			}
			
			
			
	        setLocationRelativeTo(null);
	        setLayout(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
	}
	
	@Override
    public void keyPressed(KeyEvent evt) {
		char esc = evt.getKeyChar();
		System.out.println("nacisnieto klawisz: " + esc);
        if(esc == 'r') {
            mainLoop = true;
        }
    }
 
    @Override
    public void keyReleased(KeyEvent evt) {
    }
 
    @Override
    public void keyTyped(KeyEvent evt) {

    }
	
}
