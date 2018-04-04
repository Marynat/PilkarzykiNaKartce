package Game;

import java.awt.Point;
import java.awt.geom.Point2D.Double;

public class Move {

	Point.Double prev = new Point.Double();
	Point.Double next = new Point.Double();

	public Double getPrev() {
		return prev;
	}

	public void setPrev(double x, double y) {
		this.prev.x = x;
		this.prev.y = y;
	}

	public Double getNext() {
		return next;
	}

	public void setNext(double x, double y) {
		this.next.x = x;
		this.next.y = y;
	}

}
