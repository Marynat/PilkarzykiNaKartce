package Game;

import java.awt.Point;

public class Move {
	
	Point prev = new Point();
	Point next = new Point();
	
	public Point getPrev() {
		return prev;
	}
	public void setPrev(Point prev) {
		this.prev = prev;
	}
	public Point getNext() {
		return next;
	}
	public void setNext(Point next) {
		this.next = next;
	}
	
	

}
