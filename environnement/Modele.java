package jeu.environnement;

import madkit.kernel.AbstractAgent;

/**
 * 		#jws simulation.ex06.MySimulationModel jws#
 * 
 *  It is time to display something !!
 *  The only purpose of this class is to show
 *  an example of what could be a launching sequence.
 *  
 *  The display work is done in {@link Viewer}
 *  
 */
public class Modele extends AbstractAgent{

	// Organizational constants
	public static final String MY_COMMUNITY="simu";
	public static final String SIMU_GROUP="simu";
	public static final String AGENT_ROLE = "agent";
	public static final String ENV_ROLE = "environment";
	public static final String SCH_ROLE	= "scheduler";
	public static final String	VIEWER_ROLE	= "viewer";

	@Override
	protected void activate() {
		// 1 : create the simulation group
		createGroup(MY_COMMUNITY, SIMU_GROUP);

		// 2 : create the environment
		Monde environment = new Monde();
		launchAgent(environment);
		
		// 3 : create the scheduler
		SchedulerJeu scheduler = new SchedulerJeu();
		launchAgent(scheduler,true);

		// 3 : create the viewer
		ViewerJeu viewer= new ViewerJeu();
		launchAgent(viewer,true);

		// 2 : launch some simulated agents
		for (int i = 0; i < 10; i++) {
			launchAgent(new SituatedAgent());
		}
	}
	
	public static void main(String[] args) {
		executeThisAgent(1,false); //no gui for me
	}
}
