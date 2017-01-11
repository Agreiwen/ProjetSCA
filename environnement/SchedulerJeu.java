package jeu.environnement;

import madkit.gui.ConsoleAgent;
import madkit.kernel.AbstractAgent;
import madkit.kernel.Agent;
import madkit.kernel.Madkit;
import madkit.kernel.Scheduler;
import madkit.simulation.activator.GenericBehaviorActivator;

/**
 * 		#jws simulation.ex06.MySimulationModel jws#
 * 
 *  Nothing really new here, except that we define
 *  an additional Activator which is used to schedule the display.
 *  Especially, this is about calling the "observe" method of
 *  agents having the role of viewer in the organization
 * 
 */
@SuppressWarnings("serial")
public class SchedulerJeu extends Scheduler {
	
	protected GenericBehaviorActivator<Agent> agents;
	protected GenericBehaviorActivator<Agent> viewers;
	
	@Override
	protected void activate() {

		// 1 : request my role
		requestRole(Modele.MY_COMMUNITY,
				Modele.SIMU_GROUP,
				Modele.SCH_ROLE); 
		
		
		// 3 : initialize the activators
		// by default, they are activated once each in the order they have been added
		agents = new GenericBehaviorActivator<Agent>(Modele.MY_COMMUNITY, Modele.SIMU_GROUP, Modele.AGENT_ROLE, "live");
		addActivator(agents);
		viewers = new GenericBehaviorActivator<Agent>(Modele.MY_COMMUNITY, Modele.SIMU_GROUP, Modele.VIEWER_ROLE, "observe");
		addActivator(viewers);
		
		setDelay(20);

		//4 : let us start the simulation automatically
		setSimulationState(SimulationState.RUNNING);
	}

}
