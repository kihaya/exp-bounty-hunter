import java.util.ArrayList;


/**
 * Rule rules every restriction written in paper.
 * @author kihaya
 *
 */
public class Rule {
	Rule(){	}
	/*
	 * Rule1:
	 * if agents reached ball(task complete),teleportation occur.
	 * he gets "bounty".
	 */
	public boolean rule1(HunterAgent[] hunter_agents,
			Bailbondsman bm,Cells c){
		
		ArrayList<HunterAgent> waits = new ArrayList<>();
		for(HunterAgent ha:hunter_agents){
			for(Ball b:bm.getBallCollection()){
				//exists in same position
				if(ha.getX() == b.getX() && ha.getY() == b.getY()){
				  //waits.add(ha);
					//check agent id and teleport him to proper position.
					//acquire bounty
					bm.giveBounty(ha, b);
					//ball remove.
					//all agents back to first position.
					for(HunterAgent has:hunter_agents){
						has.teleport (c);
					}
					//generate new task
					bm.generateTaskRandom(c);
					
					return true;
				}
			}
			}
		return false;
	}
}
