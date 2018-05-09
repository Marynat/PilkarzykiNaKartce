package Game;

import java.util.concurrent.ThreadLocalRandom;

public class eazyAI extends Move{
	
	String move(){
		int rand = ThreadLocalRandom.current().nextInt(0, 25);
		String dir = new String();
		if (rand == 1) {
			dir = "N";
		}else if(rand == 2 || rand == 3) {
			dir = "NE";
		}else if(rand == 4 || rand == 5) {
			dir = "NW";
		}else if(rand == 6 || rand == 7 || rand == 8) {
			dir = "E";
		}else if(rand == 9 || rand == 10 || rand == 11) {
			dir = "W";
		}else if(rand == 12 || rand == 13 || rand == 14 || rand == 15) {
			dir = "SE";
		}else if(rand == 16 || rand == 17 || rand == 18 || rand == 19) {
			dir = "SW";
		}else if(rand == 20 || rand == 21 || rand == 22 || rand == 23 || rand == 24) {
			dir = "S";
		}
		return dir;
	}

}
