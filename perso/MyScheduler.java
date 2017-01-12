package perso;

import madkit.gui.ConsoleAgent;
import madkit.kernel.AbstractAgent;
import madkit.kernel.Madkit;
import madkit.kernel.Scheduler;
import madkit.simulation.activator.GenericBehaviorActivator;

@SuppressWarnings("serial")
public class MyScheduler extends Scheduler {
	
	protected GenericBehaviorActivator<AbstractAgent> agentsRED, agentsBLUE;
	protected GenericBehaviorActivator<AbstractAgent> viewers;
	
	@Override
	protected void activate() {

		// 1 : request my role
		requestRole(MySimulationModel.MY_COMMUNITY,
				MySimulationModel.RED,
				MySimulationModel.SCH_ROLE); 
		requestRole(MySimulationModel.MY_COMMUNITY,
				MySimulationModel.BLUE,
				MySimulationModel.SCH_ROLE); 
		
		// 3 : initialize the activators
		// by default, they are activated once each in the order they have been added
		
		agentsRED = new GenericBehaviorActivator<AbstractAgent>(MySimulationModel.MY_COMMUNITY, MySimulationModel.RED, MySimulationModel.AGENT, "action");
		agentsBLUE = new GenericBehaviorActivator<AbstractAgent>(MySimulationModel.MY_COMMUNITY, MySimulationModel.BLUE, MySimulationModel.AGENT, "action");
		
		addActivator(agentsRED);
		addActivator(agentsBLUE);
		
		
		viewers = new GenericBehaviorActivator<AbstractAgent>(MySimulationModel.MY_COMMUNITY, MySimulationModel.SIMU, MySimulationModel.VIEWER_ROLE, "observe");
		addActivator(viewers);
		
		setDelay(20);

		//4 : let us start the simulation automatically
		setSimulationState(SimulationState.RUNNING);
	}

}
