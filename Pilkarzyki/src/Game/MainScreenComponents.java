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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Interface.WinningScreen;

public class MainScreenComponents extends JFrame implements ActionListener {

	private GamePanel gamePanel = new GamePanel();
	private JButton startButton = new JButton("Start");
	private JButton quitButton = new JButton("Quit");
	private boolean running = false;
	private boolean paused = false;
	double centerX;
	double centerY;
	double angle;
	String direction;
	private Player playerOne = new Player(true);
	private Player playerTwo = new Player(false);
	private VisitedList visitedList = new VisitedList();

	Point nowy = new Point();
	VisitedPoints visit = new VisitedPoints();

	boolean canI = true;
	boolean canI2 = true;
	boolean canI3 = true;

	void initiatePoints() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 11; j++) {
				int x = 240 + (i*40);
				int y = 50 + (j*40);
				if (x == 240 || x == 560 || y == 50 || y == 450) {
					visit.setHasBeenVisited(true);
				}
				visit.setVis(x, y);
				System.out.print(visit.vis + "; ");
				visitedList.visited.add(visit);
				visit = new VisitedPoints();
			}
		}
	}
	
	void clearPoints(){
		for (Iterator<VisitedPoints> it3 = visitedList.visited.iterator(); it3.hasNext();) {
			visit = it3.next();
				if (visit.vis.x == 240 || visit.vis.x == 560 || visit.vis.y == 50 || visit.vis.y == 450) {
					visit.setHasBeenVisited(true);
				}else {
					visit.setHasBeenVisited(false);
			}
		}
	}

	void switchPlayers() {
		playerOne.myMove = playerTwo.myMove;
		playerTwo.myMove = !playerTwo.myMove;

	}
	
	int checkWinner() {
		if(centerX >= 360 && centerX <= 440) {
			if (centerY == 10) {
				return 1;
			}else if (centerY == 490) {
				return 2;
			}
		}
		return 0;
	}

	MouseListener mouseL = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("Myszka zwolniona");
			Move move = new Move();
			Move mov;
			move.setPrev(centerX, centerY);
			nowy.x = (int) centerX;
			nowy.y = (int) centerY;

			if (direction.equals("")) {
				System.out.println("Nie da sie wykonac ruchu");
				move.setNext(centerX, centerY);
			} else if (direction.equals("N")) {
				nowy.y -= 40;
			} else if (direction.equals("NE")) {
				nowy.x += 40;
				nowy.y -= 40;
				move.setNext(centerX, centerY);
			} else if (direction.equals("E")) {
				nowy.x += 40;
				move.setNext(centerX, centerY);
			} else if (direction.equals("SE")) {
				nowy.x += 40;
				nowy.y += 40;
				move.setNext(centerX, centerY);
			} else if (direction.equals("S")) {
				nowy.y += 40;
				move.setNext(centerX, centerY);
			} else if (direction.equals("SW")) {
				nowy.x -= 40;
				nowy.y += 40;
				move.setNext(centerX, centerY);
			} else if (direction.equals("W")) {
				nowy.x -= 40;
				move.setNext(centerX, centerY);
			} else if (direction.equals("NW")) {
				nowy.x -= 40;
				nowy.y -= 40;
				move.setNext(centerX, centerY);
			}

			for (Iterator<Move> it = playerOne.moves.iterator(); it.hasNext();) {
				mov = it.next();
				if ((centerX == mov.prev.x && centerY == mov.prev.y && nowy.x == mov.next.x && nowy.y == mov.next.y)
						|| (centerX == mov.next.x && centerY == mov.next.y && nowy.x == mov.prev.x
								&& nowy.y == mov.prev.y)) {
					canI = false;
					break;
				} else {
					canI = true;
				}
			}

			for (Iterator<Move> it = playerTwo.moves.iterator(); it.hasNext();) {
				mov = it.next();
				if ((centerX == mov.prev.x && centerY == mov.prev.y && nowy.x == mov.next.x && nowy.y == mov.next.y)
						|| (centerX == mov.next.x && centerY == mov.next.y && nowy.x == mov.prev.x
								&& nowy.y == mov.prev.y)) {
					canI2 = false;
					break;
				} else {
					canI2 = true;
				}
			}
			if (canI && canI2) {
				move.setNext(nowy.x, nowy.y);
				System.out.println("da sie");

				for (Iterator<VisitedPoints> it2 = visitedList.visited.iterator(); it2.hasNext();) {
					visit = it2.next();
					System.out.println(visit.vis + " " + visit.hasBeenVisited);
					if (centerX == visit.vis.x && centerY == visit.vis.y && visit.hasBeenVisited) {
						canI3 = false;
						break;
					}else if(centerX == visit.vis.x && centerY == visit.vis.y && !visit.hasBeenVisited) {
						visit.setHasBeenVisited(true);
					}
					else {
						canI3 = true;

					}
				}
				if (canI3)
					switchPlayers();

				centerX = nowy.x;
				centerY = nowy.y;

			} else {
				System.out.println(canI + " " + canI2);
				move.setNext(centerX, centerY);
			}

			System.out.println("prev x: " + move.prev.x + " prev y: " + move.prev.y + " next x: " + move.next.x
					+ " next y: " + move.next.y);
			if (checkPlayer(playerOne)) {
				playerOne.moves.add(move);
			} else
				playerTwo.moves.add(move);
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
		super("Gracz vs gracz");
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
		
		initiatePoints();

		startButton.addActionListener(this);
		quitButton.addActionListener(this);
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

		public GamePanel() {
			centerX = 400;
			centerY = 250;
			last = p = MouseInfo.getPointerInfo().getLocation();
		}

		public void setInterpolation(float interp) {
			interpolation = interp;
		}

		public void update() {

			p = MouseInfo.getPointerInfo().getLocation();
		}

		public void paintComponent(Graphics g) {
			// BS way of clearing out the old map to save CPU.
			g.setColor(getBackground());
			// g.fillRect(lastDrawX - 1, lastDrawY - 1, ballWidth + 2, ballHeight + 2);
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 800, 600);

			g.setColor(Color.black);

			if (checkPlayer(playerOne)) {
				g.drawString("Ruch gracza pierwszego", 320, 520);
			} else
				g.drawString("Ruch gracza drugiego", 320, 520);

			for (int i = 0; i < 9; ++i) { //vertical lines
				g.setColor(Color.BLACK);
				int x = 240 + (i * 40);
				int y = 50;
				int w = 240 + (i * 40);
				int h = 450;
				g.drawLine(x, y, w, h);
				g.drawLine(x - 1, y, w - 1, h);
				g.drawLine(x + 1, y, w + 1, h);
			}
			for (int i = 0; i < 11; ++i) { //horizontal lines
				g.setColor(Color.BLACK);
				int x = 240;
				int y = 50 + (i * 40);
				int w = 560;
				int h = 50 + (i * 40);
				g.drawLine(x, y, w, h);
				g.drawLine(x, y - 1, w, h - 1);
				g.drawLine(x, y + 1, w, h + 1);
			}

			g.fillOval((int) (centerX - 4), (int) (centerY - 4), 8, 8);
			// upper Goal Post

			g.drawLine(360, 10, 440, 10);
			g.drawLine(360, 10, 360, 50);
			g.drawLine(440, 10, 440, 50);

			g.drawLine(360, 10 - 1, 440, 10 - 1);
			g.drawLine(360 - 1, 10, 360 - 1, 50);
			g.drawLine(440 - 1, 10, 440 - 1, 50);

			g.drawLine(360, 10 + 1, 440, 10 + 1);
			g.drawLine(360 + 1, 10, 360 + 1, 50);
			g.drawLine(440 + 1, 10, 440 + 1, 50);

			// lower goal post
			g.drawLine(360, 490, 440, 490);
			g.drawLine(360, 490, 360, 450);
			g.drawLine(440, 490, 440, 450);

			g.drawLine(360, 490-1, 440, 490-1);
			g.drawLine(360-1, 490, 360-1, 450);
			g.drawLine(440-1, 490, 440-1, 450);
			
			g.drawLine(360, 490+1, 440, 490+1);
			g.drawLine(360+1, 490, 360+1, 450);
			g.drawLine(440+1, 490, 440+1, 450);
			
			// drawing of visited lines of player one
			Iterator<Move> it;
			Move mov = new Move();
			g.setColor(Color.BLUE);
			for (it = playerOne.moves.iterator(); it.hasNext();) {
				mov = it.next();
				g.drawLine((int) mov.prev.x, (int) mov.prev.y, (int) mov.next.x, (int) mov.next.y);

			}

			// drawing of visited lines of player two
			g.setColor(Color.RED);
			for (it = playerTwo.moves.iterator(); it.hasNext();) {
				mov = it.next();
				g.drawLine((int) mov.prev.x, (int) mov.prev.y, (int) mov.next.x, (int) mov.next.y);

			}

			// lastDrawX = drawX;
			// lastDrawY = drawY;

			if (p.x != last.x || p.y != last.y) {
				last = p;
			}
			g.setColor(Color.black);
			g.drawString("Pozycja myszy: " + (p.x - 285) + " " + (p.y - 105), 600, 10);

			double theta = Math.atan2(p.x - (285 + centerX), p.y - (105 + centerY));
			theta += Math.PI / 2.0;
			angle = Math.toDegrees(theta);

			if (angle < 0) {
				angle += 360;
			}

			direction = new String("");

			g.setColor(Color.ORANGE);
			if (angle > 337.5 || angle <= 22.5) {
				if ((centerX != 240 && centerY != 50 && centerY != 450) || (centerX == 400 && centerY == 50) || (centerX == 440 && centerY == 50) || (centerX == 440 && centerY == 450) || (centerX == 400 && centerY == 450)) {
					g.drawLine((int) centerX, (int) centerY, (int) centerX - 40, (int) centerY);
					direction = "W";
				}
			} else if (angle > 22.5 && angle <= 67.5) {
				if ((centerX != 240 && centerY != 450) || (centerX == 400 && centerY == 450) || (centerX == 440 && centerY == 450)) {
					g.drawLine((int) centerX, (int) centerY, (int) centerX - 40, (int) centerY + 40);
					direction = "SW";
				}
			} else if (angle > 67.5 && angle <= 112.5) {
				if ((centerX != 240 && centerX != 560 && centerY != 450) || (centerX == 400 && centerY == 450)) {
					g.drawLine((int) centerX, (int) centerY, (int) centerX, (int) centerY + 40);
					direction = "S";
				}
			} else if (angle > 112.5 && angle <= 157.5) {
				if ((centerX != 560 && centerY != 450) || (centerX == 400 && centerY == 450) || (centerX == 360 && centerY == 450)) {
					g.drawLine((int) centerX, (int) centerY, (int) centerX + 40, (int) centerY + 40);
					direction = "SE";
				}
			} else if (angle > 157.5 && angle <= 202.5) {
				if ((centerX != 560 && centerY != 50 && centerY != 450) || (centerX == 400 && centerY == 50) || (centerX == 360 && centerY == 50) || (centerX == 360 && centerY == 450) || (centerX == 400 && centerY == 450)) {
					g.drawLine((int) centerX, (int) centerY, (int) centerX + 40, (int) centerY);
					direction = "E";
				}
			} else if (angle > 202.5 && angle <= 247.5) {
				if ((centerX != 560 && centerY != 50) || (centerX == 400 && centerY == 50) || (centerX == 360 && centerY == 50)) {
					g.drawLine((int) centerX, (int) centerY, (int) centerX + 40, (int) centerY - 40);
					direction = "NE";
				}
			} else if (angle > 247.5 && angle <= 292.5) {
				if ((centerX != 240 && centerX != 560 && centerY != 50) || (centerX == 400 && centerY == 50)) {
					g.drawLine((int) centerX, (int) centerY, (int) centerX, (int) centerY - 40);
					direction = "N";
				}
			} else if (angle > 292.5 && angle <= 337.5) {
				if ((centerX != 240 && centerY != 50) || (centerX == 400 && centerY == 50) || (centerX == 440 && centerY == 50)) {
					g.drawLine((int) centerX, (int) centerY, (int) centerX - 40, (int) centerY - 40);
					direction = "NW";
				}
			}

			g.setColor(Color.BLACK);
			g.drawString("FPS: " + fps, 5, 10);

			// g.clearRect(0, 0, 800, 500);
			g.setColor(Color.WHITE);
			if(checkWinner() == 1) {
				running = false;
				//playerOne.moves.clear();
				//playerTwo.moves.clear();
				clearPoints();
				dispose();
				new WinningScreen(400, 200, "jeden.");
			}else if(checkWinner()== 2) {
				running = false;
				//playerOne.moves.clear();
				//playerTwo.moves.clear();
				clearPoints();
				dispose();
				new WinningScreen(400, 200, "dwa.");
			}

			frameCount++;
		}
	}

	Boolean checkPlayer(Player p) {
		if (p.myMove.equals(true))
			return true;
		return false;
	}
}
