package jeu.environnement;

import jeu.personnage.Personnage;
import madkit.kernel.AbstractAgent;

public class Modele extends AbstractAgent{

	// Organizational constants
	public static final String MY_COMMUNITY	= "simu";
	
//	public static final String RED	= "RED";
	public static final String SOLDAT	= "soldat";
	
	public static final String SARGE = "sarge";
	public static final String AGENTBLEU = "AGENTBLEU";
	public static final String AGENTROUGE = "AGENTROUGE";
	
	public static final String ENV_ROLE		= "environment";
	public static final String SCH_ROLE		= "scheduler";
	public static final String VIEWER_ROLE	= "viewer";

	@Override
	protected void activate() {
		// 1 : create the simulation group
		createGroup(MY_COMMUNITY, SOLDAT);
		//createGroup(MY_COMMUNITY, BLUE);

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
			launchAgent(new Personnage(SOLDAT, AGENTROUGE));
			launchAgent(new Personnage(SOLDAT, AGENTBLEU));
		}
		
		
	}
	
	public static void main(String[] args) {
		executeThisAgent(1, false);
	}
}
