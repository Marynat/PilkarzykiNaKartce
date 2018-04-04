package Game;

import java.util.ArrayList;

public class Player extends Move{
	
	ArrayList<Move> moves = new ArrayList<Move>();
	Boolean myMove = false;
	
	Player(Boolean b){
		this.myMove = b;
	}

}
