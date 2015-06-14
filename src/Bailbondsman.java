
/**
 * 
 * @author Kihaya
 * agent who generates tasks and raise their bounty up.
 */

import java.util.ArrayList;
import java.util.Random;


/**
 * @author kihaya
 * @date 2015
 * bailbonds man do>
 *    gen tasks
 *       bounty
 *       pos x
 *       pos y
 */
public class Bailbondsman {
	
	
	private static final int DEFAULT_BOUNTY = 100;
	private static final int BAUNTY_RAISE_RATE = 1;
	private Random random;
	public ArrayList<Ball> balls;
	
	Bailbondsman(){
		balls = new ArrayList<>();
		random = new Random();
	}
	public void generateTaskRandom(Cells cell){
		int xpos = random.nextInt(60);
		int ypos = random.nextInt(40);
		while(cell.getValue(xpos, ypos) >0 && cell.getValue(xpos, ypos)<=5){
			xpos = random.nextInt(60);
			ypos = random.nextInt(40);
		}
		// bouty set
		int bounty = DEFAULT_BOUNTY;
		Ball ball = new Ball(bounty,xpos,ypos);
		balls.add(ball);
	}
	//give agent an bounty
	public void giveBounty(HunterAgent ha,Ball b){
		ha.setBounty(b.getBaunty());
		balls.remove(b);
	}
	public void raiseBounty(Ball b){
		b.setBaunty(b.getBaunty() + BAUNTY_RAISE_RATE);
	}
	public ArrayList<Ball> getBallCollection(){
		return this.balls;
	}
}
