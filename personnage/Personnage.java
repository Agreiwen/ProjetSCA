package jeu.personnage;

import java.awt.Dimension;

import jeu.environnement.Modele;
import jeu.environnement.Monde;
import madkit.kernel.AbstractAgent;
import madkit.kernel.Agent;
import madkit.kernel.AgentAddress;
import madkit.kernel.Madkit;
import madkit.kernel.Message;


public class Personnage extends AbstractAgent {
	String pseudo;
	int pointDeVie = 100;
	/**
	 * agent's position
	 */
	private Dimension location = new Dimension();
	private Monde environment;
	
	public Personnage() {
		this.pseudo = "perso";
	}

	
	protected void liver() {
		Dimension envDim = environment.getDimension();
		location.width += Math.random()*4 - 1;
		location.height += Math.random()*4 - 1;
		location.width %= envDim.width;
		location.height %= envDim.height;
	}

	@Override
	protected void activate() {
		// TODO Auto-generated method stub
		requestRole(Modele.MY_COMMUNITY, Modele.BLUE_GROUP, Modele.AGENT_ROLE);
		Dimension envDim = environment.getDimension();
		location.width = (int) (Math.random()*envDim.width);
		location.height = (int) (Math.random()*envDim.height);
	}

/*	@Override
	protected void end() {
		// TODO Auto-generated method stub
		super.end();
	}*/

}
