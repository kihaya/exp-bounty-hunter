import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 
 * @author Kihaya 
 * BountyHunting MultiagentSimulator Runner
 * 
 *         TODO or Issues: balls places at same point. balls places at the place
 *         agents already exist. Wants UI that shows all agents position and
 *         target. Wants change simulator speed.
 *         
 *         simulator will be run in multi-thread environment.
 */
public class Simulator implements Runnable {

	public static final int NUM_HUNTERS = 4;
	public static final int INITIAL_NUM_BALLS = 20;
	public static final int SPEED = 10;

	public MainUI mainui;// graphical ui
	
	public Cells cells;// cells to share situation
	public HunterAgent[] hunter_agents; // hunter agents container
	public Bailbondsman bailbondsman; // task generator

	public Rule rule;
	
	/*difine flags */
	private int nogui;
	private int thinking;
	private int eval;
	private int environment;
	private int try_count;
    
	Simulator(int nogui,int thinking,int eval,
			            int environment,int try_count) {
		
		this.nogui = nogui;
		this.thinking = thinking;
		this.eval = eval;
		this.environment = environment;
		this.try_count = try_count;
		
		//all_setup
		setUp();
		//show grid,tasks and agents.
		sync();
	}
	
	private void setUp() {
        
		/* all gui setup.if flag is on,run with nogui.*/
		mainui = new MainUI("#ExpBountyHunter");
		if(nogui == 1){
			System.out.println("No GUI mode.");
			mainui.setVisible(false);
		}
		
		cells = new Cells();
		bailbondsman = new Bailbondsman();
		rule = new Rule();
		// place hunter agents
		hunter_agents = new HunterAgent[NUM_HUNTERS];
		hunter_agents[0] = new HunterAgent(Consts.AGENT_1, 0, 0,0,0);
		hunter_agents[1] = new HunterAgent(Consts.AGENT_2, 59, 0,0,0);
		hunter_agents[2] = new HunterAgent(Consts.AGENT_3, 0, 39,0,0);
		hunter_agents[3] = new HunterAgent(Consts.AGENT_4, 59, 39,0,0);

		// place 20 balls.
		for (int i = 0; i < INITIAL_NUM_BALLS; ++i) {
			bailbondsman.generateTaskRandom(cells);
		}
	}

	/**
	 * run simulation
	 */
	public void simulate(int algo) {
		
      for(int i=0;i<this.try_count;++i){
    	 System.out.println(i);
		for (HunterAgent ha : hunter_agents) {
			// set algo to find task
			ha.setAlgo(algo);
			ha.Think(bailbondsman);
		}

		// move all agents to target

		  while (true) {
			try {
				//clearTime();
				Thread.sleep(SPEED);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (rule.rule1(hunter_agents, bailbondsman, cells)) {
				//print_agents_bounty();
				break;
			}

			for (HunterAgent ha : hunter_agents) {
				try {
					move_agent(ha, ha.getNextDirection());
				} catch (Exception e) {
					// do nothing.
					
				}
				sync();
			}
			sync();
		}
		sync();
		
		//raiseBounty
		clearTime();
		//print_agents_bounty();
      }
	}
		

	/*
	 * synchronize all agents position and cell postion and UI.
	 */
	public void sync() {
		//for agent
		for (HunterAgent agent : hunter_agents) {
			//
			cells.setValue(agent.getX(), agent.getY(), agent.getAgentId());
			//
			mainui.setCellColor(agent);
		}
		//for ball 
		ArrayList<Ball> balls = bailbondsman.getBallCollection();
		for (Ball ball : balls) {
			cells.setValue(ball.getX(), ball.getY(), Consts.BALL);
			//change ball color depending on ball bounty.
			if(ball.getBaunty() >= 300){
			   mainui.setCellColor(ball,true);
			}else{
			   mainui.setCellColor(ball,false);
			}
		}
		//for all cell
		for (int i = 0; i < 60; ++i) {
			for (int j = 0; j < 40; ++j) {
				if (cells.getValue(i, j) == Consts.EMPTY) {
					mainui.setCellColor(i, j, Consts.EMPTY);
				}
			}
		}
	}

	/**
	 * this is clearing time to raise bounty or tune a,b,c
	 */
	public void clearTime() {
		// if task is not occupied by someone,
		// raise its bounty.
		for (Ball ball : bailbondsman.getBallCollection()) {
			for (HunterAgent ha : hunter_agents) {
				if (ball.getX() != ha.target[0] && ball.getY() != ha.target[1]) {
					// raise bounty.
					bailbondsman.raiseBounty(ball);
				}
			}
		}
	}
    
	/*
	 * this is for ControllPane to simulate
	 * @see java.lang.Runnable#run()
	 */
	public void run(){
		simulate(0);
	}
	/**
	 * clear position and set agent to target position.
	 * 
	 * @param ha
	 * @param direction
	 */
	public void move_agent(HunterAgent ha, int direction) {
		cells.setValue(ha.getX(), ha.getY(), Consts.EMPTY);
		ha.move(direction);
	}

	/*
	 * print targets.
	 */
	public void print_agents_target() {
		System.out.println("#ALL AGENT'S Targets are BELOW");
		for (HunterAgent ha : hunter_agents) {
			System.out.println(ha.getAgentId() + ":(" + ha.target[0] + ","
					+ ha.target[1] + ")");
		}
		System.out.println("-----");
	}

	public void print_agents_bounty() {
		System.out.println("#bounty");
		for (HunterAgent ha : hunter_agents) {
			System.out.println(ha.getAgentId() + ":" + ha.getBaunty());
		}
	}

}
