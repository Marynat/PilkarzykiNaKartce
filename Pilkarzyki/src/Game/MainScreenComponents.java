package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainScreenComponents extends JFrame implements ActionListener {

	private GamePanel gamePanel = new GamePanel();
	private JButton startButton = new JButton("Start");
	private JButton quitButton = new JButton("Quit");
	private boolean running = false;
	private boolean paused = false;
	MouseListener mouseL = new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("Myszka nacisnieta");
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
	private Point last, p;
	private int fps = 60;
	private int frameCount = 0;

	public MainScreenComponents() {
			super("Fixed Timestep Game Loop Test");
			Container cp = getContentPane();
			cp.setLayout(new BorderLayout());
			JPanel p = new JPanel();
			p.setLayout(new GridLayout(1, 2));
			p.add(startButton);
			p.add(quitButton);
			cp.add(gamePanel, BorderLayout.CENTER);
			cp.add(p, BorderLayout.SOUTH);
			setSize(800, 600);
			
			gamePanel.addMouseListener(mouseL);

			startButton.addActionListener(this);
			quitButton.addActionListener(this);
			//setIgnoreRepaint(true);
			setLocationRelativeTo(null);
		}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		if (s == startButton) {
			running = !running;
			if (running) {
				startButton.setText("Stop");
				runGameLoop();
			} else {
				startButton.setText("Start");
			}
		} else if (s == quitButton) {
			System.exit(0);
		}
	}

	// Starts a new thread and runs the game loop in it.
	public void runGameLoop() {
		Thread loop = new Thread() {
			public void run() {
				gameLoop();
			}
		};
		loop.start();
	}

	// Only run this in another Thread!
	private void gameLoop() {
		// This value would probably be stored elsewhere.
		final double GAME_HERTZ = 30.0;
		// Calculate how many ns each frame should take for our target game hertz.
		final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
		// At the very most we will update the game this many times before a new render.
		// If you're worried about visual hitches more than perfect timing, set this to
		// 1.
		final int MAX_UPDATES_BEFORE_RENDER = 5;
		// We will need the last update time.
		double lastUpdateTime = System.nanoTime();
		// Store the last time we rendered.
		double lastRenderTime = System.nanoTime();

		// If we are able to get as high as this FPS, don't render again.
		final double TARGET_FPS = 200;
		final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

		// Simple way of finding FPS.
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);

		while (running) {
			double now = System.nanoTime();
			int updateCount = 0;

			if (!paused) {
				// Do as many game updates as we need to, potentially playing catchup.
				while (now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) {
					updateGame();
					lastUpdateTime += TIME_BETWEEN_UPDATES;
					updateCount++;
				}

				// If for some reason an update takes forever, we don't want to do an insane
				// number of catchups.
				// If you were doing some sort of game that needed to keep EXACT time, you would
				// get rid of this.
				if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
					lastUpdateTime = now - TIME_BETWEEN_UPDATES;
				}

				// Render. To do so, we need to calculate interpolation for a smooth render.
				float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES));
				drawGame(interpolation);
				lastRenderTime = now;

				// Update the frames we got.
				int thisSecond = (int) (lastUpdateTime / 1000000000);
				if (thisSecond > lastSecondTime) {
					System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
					fps = frameCount;
					frameCount = 0;
					lastSecondTime = thisSecond;
				}

				// Yield until it has been at least the target time between renders. This saves
				// the CPU from hogging.
				while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS
						&& now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
					Thread.yield();

					// This stops the app from consuming all your CPU. It makes this slightly less
					// accurate, but is worth it.
					// You can remove this line and it will still work (better), your CPU just
					// climbs on certain OSes.
					// FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a
					// look at different peoples' solutions to this.
					try {
						Thread.sleep(1);
					} catch (Exception e) {
					}

					now = System.nanoTime();
				}
			}
		}
	}

	private void updateGame() {
		gamePanel.update();
	}

	private void drawGame(float interpolation) {
		gamePanel.setInterpolation(interpolation);
		gamePanel.repaint();
	}

	private class GamePanel extends JPanel {
		float interpolation;
		float ballX, ballY, lastBallX, lastBallY;
		int ballWidth, ballHeight;
		float ballXVel, ballYVel;
		float ballSpeed;
		
		double centerX = 400;
		double centerY = 150;

		int lastDrawX, lastDrawY;
		

		public GamePanel() {
			centerX = 400;
			centerY = 150;
			last = p = MouseInfo.getPointerInfo().getLocation();
		}

		public void setInterpolation(float interp) {
			interpolation = interp;
		}

		public void update() {
			
			p = MouseInfo.getPointerInfo().getLocation();

			ballX += ballXVel;
			ballY += ballYVel;

			if (ballX + ballWidth / 2 >= getWidth()) {
				ballXVel *= -1;
				ballX = getWidth() - ballWidth / 2;
				ballYVel = (float) Math.random() * ballSpeed * 2 - ballSpeed;
			} else if (ballX - ballWidth / 2 <= 0) {
				ballXVel *= -1;
				ballX = ballWidth / 2;
			}

			if (ballY + ballHeight / 2 >= getHeight()) {
				ballYVel *= -1;
				ballY = getHeight() - ballHeight / 2;
				ballXVel = (float) Math.random() * ballSpeed * 2 - ballSpeed;
			} else if (ballY - ballHeight / 2 <= 0) {
				ballYVel *= -1;
				ballY = ballHeight / 2;
			}
		}

		public void paintComponent(Graphics g) {
			// BS way of clearing out the old rectangle to save CPU.
			g.setColor(getBackground());
			//g.fillRect(lastDrawX - 1, lastDrawY - 1, ballWidth + 2, ballHeight + 2);
			g.fillRect(5, 0, 75, 30);
			g.fillRect(500, 0, 400, 30);
			g.fillRect((int)centerX - 20, (int)centerY - 20, 40, 40);

			for (int i = 0; i < 9; ++i) {
				g.setColor(Color.BLACK);
				int x = 320 + (i* 20);
				int y = 50;
				int w = 320 + (i* 20);
				int h = 250;
				g.drawLine(x, y, w, h);
			}
			for (int i = 0; i < 11; ++i) {
				g.setColor(Color.BLACK);
				int x = 320;
				int y = 50 + (i*20);
				int w = 480;
				int h = 50 + (i*20);
				g.drawLine(x, y, w, h);
			}
			//upper Goal Post
			
			g.drawLine(380, 30, 420, 30);
			g.drawLine(380, 30, 380, 50);
			g.drawLine(420, 30, 420, 50);
			
			//lower goal post
			g.drawLine(380, 270, 420, 270);
			g.drawLine(380, 270, 380, 250);
			g.drawLine(420, 270, 420, 250);

			
			//lastDrawX = drawX;
			//lastDrawY = drawY;
			
			if(p.x != last.x || p.y != last.y)
            {
                last = p;
            }
            g.setColor(Color.black);
            g.drawString("Pozycja myszy: " + p.x+ " " + p.y, 600, 20);
            
            
            double theta = Math.atan2(p.x - 685, p.y - 255);
            theta += Math.PI/2.0;
            double angle = Math.toDegrees(theta);

                if(angle < 0){
                    angle += 360;
                }

            g.setColor(Color.RED);
            if(angle > 337.5 || angle <= 22.5 ) {
            	g.drawLine((int)centerX, (int)centerY, (int)centerX-20, (int)centerY);
            }else if(angle > 22.5 && angle <= 67.5 ) {
            	g.drawLine((int)centerX, (int)centerY, (int)centerX-20, (int)centerY+20);
            }else if(angle > 67.5 && angle <= 112.5 ) {
            	g.drawLine((int)centerX, (int)centerY, (int)centerX, (int)centerY+20);
            }else if(angle > 112.5 && angle <= 157.5 ) {
            	g.drawLine((int)centerX, (int)centerY, (int)centerX+20, (int)centerY+20);
            }else if(angle > 157.5 && angle <= 202.5 ) {
            	g.drawLine((int)centerX, (int)centerY, (int)centerX+20, (int)centerY);
            }else if(angle > 202.5 && angle <= 247.5 ) {
            	g.drawLine((int)centerX, (int)centerY, (int)centerX+20, (int)centerY-20);
            }else if(angle > 247.5 && angle <= 292.5 ) {
            	g.drawLine((int)centerX, (int)centerY, (int)centerX, (int)centerY-20);
            }else if(angle > 292.5 && angle <= 337.5 ) {
            	g.drawLine((int)centerX, (int)centerY, (int)centerX-20, (int)centerY-20);
            }

			g.setColor(Color.BLACK);
			g.drawString("FPS: " + fps, 5, 10);
			
			//g.clearRect(0, 0, 800, 500);

			frameCount++;
		}
	}
}
