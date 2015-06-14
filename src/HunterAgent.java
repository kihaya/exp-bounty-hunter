import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * 
 * @author kihaya
 *
 */

import java.math.*;


/**
 * 
 * @author kihaya
 * my hunter agent
 *    Think
 *    findTask : this should be implemented following paper."Bounty Hunter and~"
 *               one is random and other is euqrides distance,
 *               taking account of other agents behavior or bounty.
 *    delay
 */
public class HunterAgent {
	
   private int agent_id;
   
   private double estimate_time;//pp389
   private double estimate_prob;//pp389
   private int taken_time;
   
   private int current_bounty;
   
   private LinkedList<Integer> next_direction;
   
   public int[] target = {0,0};//target task position.
   
   private int xpos;
   private int ypos;
   
   private int thinking;
   private int eval;
   
   private Eval evaluation_algorithm;
   
   public Random r;
   
   HunterAgent(int id,int xpos,int ypos,int thinking,int eval){
	   agent_id = id;
	   this.xpos = xpos;
	   this.ypos = ypos;
	   r = new Random();
	   next_direction = new LinkedList<>();
	   
	   this.thinking = thinking;
	   this.eval = eval;
   }
   
   /*
    * if Simple method,
    * agents think which tasks to commit.
    * considering distance between me and ball
    * or bounty.
    * if complex method,
    * agents consider what others doing.
    */
   public void Think(Bailbondsman bm){
	   
	   next_direction = new LinkedList<>();
	   
	   //System.out.println("#AGENT" + getAgentId() + "thinks.");
	   
	   if(this.thinking == 0){
	     /* this is random . */
	     findTaskRandom(bm);
	   }else if(this.thinking == 2){
		 findTaskGreedy(bm);
	   }else if(this.thinking == 1){
		 findTaskDist(bm);
	   }
	   
	   //TODO: sooner this will be implemented as findTask()
	   
	   if((this.xpos - this.target[0]) < 0){
		   int count = Math.abs(this.xpos - this.target[0]);
		   for(int i=0;i<count;++i){
		      next_direction.add(Consts.RIGHT);
		   }
	   }else if((this.xpos -this.target[0]) > 0 ){
		   int count = Math.abs(this.xpos - this.target[0]);
		   for(int i=0;i<count;++i){
		      next_direction.add(Consts.LEFT);
		   }
	   }
	   
	   if((this.ypos - this.target[1]) < 0){
		   int count = Math.abs(this.ypos - this.target[1]);
		   for(int i=0;i<count;++i){
			      next_direction.add(Consts.BOTTOM);
			   }
	   }else if((this.ypos - this.target[1]) > 0){
		   int count = Math.abs(this.ypos - this.target[1]);
		   for(int i=0;i<count;++i){
			      next_direction.add(Consts.TOP);
			   }
	   }
   }
   
   /**
    * method to find task taking account the points of view.
    *    
    */
   public void findTaskGreedy(Bailbondsman bm){
	   
	   int[] taget = {0,0};
	   double value = 0;
	   Ball max = null;
	   
	   //get all tasks
	   ArrayList<Ball> tasks = bm.getBallCollection();
	   
	   //search max.
	   for(Ball b:tasks){
		   //b(i) / E(T(i))
		   if(value <= (b.getBaunty()/calcDistance(xpos,ypos,b.getX(),b.getY()))){
			   value = (b.getBaunty()/calcDistance(xpos,ypos,b.getX(),b.getY()));
			   max = b;
		   }
	   } 
	
	   target[0] = max.getX();
	   target[1] = max.getY();
   }
   /*
    * decide task by distance
    */
public void findTaskDist(Bailbondsman bm){
	   
	   int[] taget = {0,0};
	   double distance = 999999;
	   Ball minimum = null;
	   
	   //get all tasks
	   ArrayList<Ball> tasks = bm.getBallCollection();
	   
	   //search max.
	   for(Ball b:tasks){
		   //b(i) / E(T(i))
		   if(distance >= calcDistance(xpos,ypos,b.getX(),b.getY())){
			   distance = calcDistance(xpos,ypos,b.getX(),b.getY());
			   minimum = b;
		   }
	   } 
	
	   target[0] = minimum.getX();
	   target[1] = minimum.getY();
   }
   
   /**
    * method to find task random.
    */
   public void findTaskRandom(Bailbondsman bm){
	   //get all tasks
	   ArrayList<Ball> tasks = bm.getBallCollection();
	   /* this is random . */
	   Ball target = tasks.get(r.nextInt(tasks.size()));
	   this.target[0] = target.getX();
	   this.target[1] = target.getY();
   }
   
   /**
    * agent moves.
    * @param direction
    * @return
    */
   public boolean move(int direction){
	   if(direction == Consts.TOP){
		   if((ypos - 1) < 0){
			   return false;
		   }
		   ypos = ypos - 1;
		   return true;
	   }else if(direction == Consts.RIGHT){
		   if((xpos + 1) >= 60){
			   return false;
		   }
		   xpos = xpos + 1;
	   }else if(direction == Consts.BOTTOM){
		   if((ypos + 1) >= 40){
			   return false;
		   }
		   ypos = ypos + 1;
	   }else if(direction == Consts.LEFT){
		   if((xpos -1) < 0){
			   return false;
		   }
		   xpos = xpos -1;
	   }
	   
	   return false;
   }
   
   /*
    * moving back to default position.
    */
   public void teleport(Cells c){
	   if(getAgentId() == Consts.AGENT_1 ){
		   c.setValue(xpos, ypos, 0);
		   setX(0);
		   setY(0);
		   //c.setValue(xpos, ypos, 0);
	   }else if(getAgentId() == Consts.AGENT_2){
		   c.setValue(xpos, ypos, 0);
		   setX(59);
		   setY(0);
		   //c.setValue(xpos, ypos, 0);
	   }else if(getAgentId() == Consts.AGENT_3){
		   c.setValue(xpos, ypos, 0);
		   setX(0);
		   setY(39);
		   //c.setValue(xpos, ypos, 0);
	   }else if(getAgentId() == Consts.AGENT_4){
		   c.setValue(xpos, ypos, 0);
		   setX(59);
		   setY(39);
		   //c.setValue(xpos, ypos, 0);
	   }
   }
   
   public void setAlgo(int algo){
	   this.thinking = algo;
   }
   
   /*
    * utilyty function to calculate task distance from agent.
    */
   private double calcDistance(int a,int b,int c ,int d){
	   double inner_root1 = (c - a) * (c - a);
	   double inner_root2 = (d - b) * (d - b);
	   double dis = Math.sqrt(inner_root1 + inner_root2);
	   return dis;
   }
   
   public int getNextDirection(){
	   return next_direction.pollFirst();
   }
   
   public int getAgentId(){
	   return agent_id;
   }
   public int getBaunty(){
	   return current_bounty;
   }
   public int getX(){
	   return xpos;
   }
   public int getY(){
	   return ypos;
   }
   
   public void setX(int x){
	   this.xpos = x;
   }
   public void setY(int y){
	   this.ypos = y;
   }
   
   public void setBounty(int bounty){
	   this.current_bounty = this.current_bounty + bounty;
   }
   
   /*
    * implementation of simple method
    */
   class Eval{ 
	   /*
	    * next_task (b / T )*P
	    */
	   private final int INITIAL_T = 1;
	   private final int INITIAL_P = 1;
	   private int b;
	   private int T;
	   private double P;
	   public Ball next_task;
	   
	   Eval(int bounty,int time){
		   this.b = b;
		   this.T = INITIAL_T;
		   this.P = INITIAL_P;
		   next_task = evaluate();
	   }
	   
	   /* find maximum task that is in ball array by using evaluateOne()*/
	   protected Ball evaluate(){
		   Ball maximum;
		   
		   return null;
	   }
	   protected double evaluateOne(int bounty,int time){
		   return (bounty/time)*this.P;
	   }
   }
}
