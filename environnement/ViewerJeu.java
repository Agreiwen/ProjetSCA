package jeu.environnement;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import madkit.kernel.AbstractAgent;
import madkit.kernel.Watcher;
import madkit.simulation.probe.PropertyProbe;
import madkit.simulation.probe.SingleAgentProbe;
import madkit.simulation.viewer.SwingViewer;

/**
 * This class will be used to display the simulation.
 * We could have extended the {@link Watcher} class, but there are a lot of
 * things already defined in {@link SwingViewer}. So why not use it.
 * 
 */
public class ViewerJeu extends SwingViewer {

	/**
	 * environment's size, probed using a {@link SingleAgentProbe}.
	 */
	private Dimension											envSize;

	/**
	 * The probe by which we will get the agents' location
	 */
	protected PropertyProbe<AbstractAgent, Dimension>	 agentsRED, agentsBLUE;

	@Override
	protected void activate() {
		// 1 : request my role so that the scheduler can take me into account
		requestRole(Modele.MY_COMMUNITY, Modele.SOLDAT,
				Modele.VIEWER_ROLE);

		// 2 : adding the probes 
		
		// probing the environment using an anonymous inner class
		SingleAgentProbe<Monde, Dimension> envProbe = new SingleAgentProbe<Monde, Dimension>(
				Modele.MY_COMMUNITY, 
				Modele.SOLDAT,
				Modele.ENV_ROLE, 
				"dimension") {
				protected void adding(Monde agent) {
					super.adding(agent);
					envSize = getPropertyValue();
				}
		};
		addProbe(envProbe);

		// probing agents' location
		agentsRED = new PropertyProbe<AbstractAgent, Dimension>(
				Modele.MY_COMMUNITY, Modele.SOLDAT,
				Modele.AGENTROUGE, "location");
		
		agentsBLUE = new PropertyProbe<AbstractAgent, Dimension>(
				Modele.MY_COMMUNITY, Modele.SOLDAT,
				Modele.AGENTBLEU, "location");
		
		addProbe(agentsRED);
		addProbe(agentsBLUE);

		// 3 : Now that the probes are added,
		// we can setup the frame for the display according to the environment's properties
		getDisplayPane().setPreferredSize(envSize);
		getFrame().pack();

		// 4 (optional) set the synchronous painting mode: The display will be updated
		// for each step of the simulation.
		// Here it is useful because the simulation goes so fast that the agents
		// are almost invisible
		setSynchronousPainting(true);
	}

	/**
	 * render is the method where the custom painting has to be done.
	 * Here, we just draw red points at the agents' location
	 */
	@Override
	protected void render(Graphics g) {
		//System.out.println("Je fais le rendu");
		g.setColor(Color.RED);
		for (AbstractAgent a : agentsRED.getCurrentAgentsList()) {
			Dimension location = agentsRED.getPropertyValue(a);
			g.drawOval(location.width, location.height, 5, 5);
			//g.drawRect(location.width, location.height, 3, 3);
		}
		
		g.setColor(Color.BLUE);
		for (AbstractAgent a : agentsBLUE.getCurrentAgentsList()) {
			Dimension location = agentsBLUE.getPropertyValue(a);
			g.drawOval(location.width, location.height, 5, 5);
			//g.drawRect(location.width, location.height, 3, 3);
		}
	}

}
