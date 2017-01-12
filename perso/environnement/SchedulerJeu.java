package perso.environnement;

import madkit.gui.ConsoleAgent;
import madkit.kernel.AbstractAgent;
import madkit.kernel.Madkit;
import madkit.kernel.Scheduler;
import madkit.simulation.activator.GenericBehaviorActivator;

@SuppressWarnings("serial")
public class SchedulerJeu extends Scheduler {
	
	protected GenericBehaviorActivator<AbstractAgent> agentsRED, agentsBLUE;
	protected GenericBehaviorActivator<AbstractAgent> viewers;
	
	@Override
	protected void activate() {

		// 1 : request my role
		requestRole(Modele.MY_COMMUNITY,
				Modele.SOLDAT,
				Modele.SCH_ROLE); 
		requestRole(Modele.MY_COMMUNITY,
				Modele.SOLDAT,
				Modele.SCH_ROLE); 
		
		// 3 : initialize the activators
		// by default, they are activated once each in the order they have been added
		
		agentsRED = new GenericBehaviorActivator<AbstractAgent>(Modele.MY_COMMUNITY, Modele.SOLDAT, Modele.AGENTBLEU, "liver");
		agentsBLUE = new GenericBehaviorActivator<AbstractAgent>(Modele.MY_COMMUNITY, Modele.SOLDAT, Modele.AGENTROUGE, "liver");
		
		addActivator(agentsRED);
		addActivator(agentsBLUE);
		
		viewers = new GenericBehaviorActivator<AbstractAgent>(Modele.MY_COMMUNITY, Modele.SOLDAT, Modele.VIEWER_ROLE, "observe");
		addActivator(viewers);
		
		setDelay(20);

		//4 : let us start the simulation automatically
		setSimulationState(SimulationState.RUNNING);
	}

}
