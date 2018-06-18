package Game;

import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class hardAi extends Move {

	String chooseMove() {
		int rand = ThreadLocalRandom.current().nextInt(0, 28);
		System.out.println(rand);
		String dir = new String();
		if (rand == 1) {
			dir = "N";
		} else if (rand == 2 || rand == 3) {
			dir = "NE";
		} else if (rand == 4 || rand == 5) {
			dir = "NW";
		} else if (rand >= 6 && rand <= 8) {
			dir = "E";
		} else if (rand >= 9 && rand <= 11) {
			dir = "W";
		} else if (rand >= 12 && rand <= 15) {
			dir = "SE";
		} else if (rand >= 16 && rand <= 19) {
			dir = "SW";
		} else if (rand >= 20 && rand <= 27) {
			dir = "S";
		}
		return dir;
	}

	ArrayList<Move> moves = new ArrayList<Move>();
	
	ArrayList<ArrayList<Move>> listOfMoves = new ArrayList<ArrayList<Move>>();

}
