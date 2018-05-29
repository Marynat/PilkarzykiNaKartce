package Game;

import java.awt.Point;

public class VisitedPoints {
	Point vis = new Point();
	boolean hasBeenVisited = false;  //standardowo false - true, pole jest zajete
	
	public void setVis(int x, int y) {
		vis.x = x;
		vis.y = y;
	}
	
	public boolean isHasBeenVisited() {
		return hasBeenVisited;
	}
	public void setHasBeenVisited(boolean hasBeenVisited) {
		this.hasBeenVisited = hasBeenVisited;
	}

}
