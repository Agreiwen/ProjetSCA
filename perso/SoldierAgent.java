package perso;

import java.awt.Dimension;

import madkit.kernel.AbstractAgent;
import madkit.kernel.Message;
import madkit.message.StringMessage;

public class SoldierAgent extends AbstractAgent {

	/**
	 * The agent's environment. 
	 * Here it is just used to know its boundaries. 
	 * It will be automatically set
	 * by the environment agent itself: No need to instantiate anything here.
	 */
	private EnvironmentAgent environment;

	/**
	 * agent's position
	 */
	private Dimension location = new Dimension();

	/**
	 * agent's team
	 */
	private String team = "";


	private String role = "";

	public SoldierAgent(String team, String role) {
		this.team = team;
		this.role = role;
	}																																	

	/**
	 * initialize my role and fields
	 */
	@Override
	protected void activate() {
		requestRole(MySimulationModel.MY_COMMUNITY, team, role);
		Dimension envDim = environment.getDimension();
		location.width = (int) (Math.random()*envDim.width);
		location.height = (int) (Math.random()*envDim.height);
	}

	@SuppressWarnings("unused")
	private void action() {
		Dimension envDim = environment.getDimension();


		// until I found someone having that role
		broadcastMessage(MySimulationModel.MY_COMMUNITY, team, role, new StringMessage(location.width + ":" + location.height));

		Message m = nextMessage();
		if (m != null) {
			StringMessage sm = (StringMessage)m;
			if (logger != null)
				logger.info(m.getSender() + " - " + sm.getContent());
		}

		location.width += Math.random()*4 - 5;
		location.height += Math.random()*4 - 5;
		location.width = (location.width + envDim.width) % envDim.width;
		location.height = (location.height + envDim.height) % envDim.height;

	}

}
