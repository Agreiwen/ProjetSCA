package perso;

import madkit.kernel.AbstractAgent;

public class MySimulationModel extends AbstractAgent{

	// Organizational constants
	public static final String MY_COMMUNITY	= "simu";
	
	public static final String SIMU	= "simu";
	
	public static final String RED	= "RED";
	public static final String BLUE	= "BLUE";
	
	public static final String SARGE	= "sarge";
	public static final String AGENT	= "agent";
	
	
	public static final String ENV_ROLE		= "environment";
	public static final String SCH_ROLE		= "scheduler";
	public static final String VIEWER_ROLE	= "viewer";

	@Override
	protected void activate() {
		// 1 : create the simulation group
		createGroup(MY_COMMUNITY, SIMU);
		
		createGroup(MY_COMMUNITY, RED);
		createGroup(MY_COMMUNITY, BLUE);

		// 2 : create the environment
		EnvironmentAgent environment = new EnvironmentAgent();
		launchAgent(environment);
		
		// 3 : create the scheduler
		MyScheduler scheduler = new MyScheduler();
		launchAgent(scheduler,true);

		// 3 : create the viewer
		Viewer viewer= new Viewer();
		launchAgent(viewer,true);

		// 2 : launch some simulated agents
		for (int i = 0; i < 10; i++) {
			launchAgent(new SoldierAgent(RED, AGENT));
		}
		
		for (int i = 0; i < 10; i++) {
			launchAgent(new SoldierAgent(BLUE, AGENT));
		}
	}
	
	public static void main(String[] args) {
		executeThisAgent(1, false);
	}
}
