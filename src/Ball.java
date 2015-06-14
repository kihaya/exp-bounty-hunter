
public class Ball {
	
	/**
	 * @author kihaya
	 * This is implementation of each tasks that is
	 * placed randomly by bailbondsman class.
	 * All Ball is created and managed by bailbondsman obj.
	 * Ball can be removed from grid by bailbondsman.
	 * 
	 * All Task has an probability to conplete for agents.
	 */
	
	private int task_id;
	private final double bounty_increase_rate = 1;
	private int bounty;
	private int xpos;
	private int ypos;
	
	public int p; // this is task completion prob.
	
	Ball(int bounty,int x,int y){
		task_id = this.hashCode();
		this.bounty = bounty;
		//locked = Consts.EMPTY;
		xpos = x;
		ypos = y;
	}
	
	public int getBaunty(){
		return this.bounty;
	}
	public void setBaunty(int value){
		this.bounty = value;
	}
	public void setPos(int x,int y){
		this.xpos = x;
		this.ypos = y;
	}
	public int getX(){
		return xpos;
	}
	public int getY(){
		return ypos;
	}
}
