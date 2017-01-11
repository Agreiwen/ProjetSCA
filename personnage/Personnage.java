package jeu.personnage;

import java.awt.Dimension;

import jeu.environnement.Monde;
import madkit.kernel.Agent;
import madkit.kernel.AgentAddress;
import madkit.kernel.Madkit;
import madkit.kernel.Message;
import simulation.ex06.EnvironmentAgent;
import simulation.ex06.MySimulationModel;

public class Personnage extends Agent {
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

	@Override
	protected void live() {
		Dimension envDim = environment.getDimension();
		location.width += Math.random()*4 - 1;
		location.height += Math.random()*4 - 1;
		location.width %= envDim.width;
		location.height %= envDim.height;
	}

	@Override
	protected void activate() {
		// TODO Auto-generated method stub
		requestRole(MySimulationModel.MY_COMMUNITY, MySimulationModel.SIMU_GROUP, MySimulationModel.AGENT_ROLE);
		Dimension envDim = environment.getDimension();
		location.width = (int) (Math.random()*envDim.width);
		location.height = (int) (Math.random()*envDim.height);
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		super.end();
	}

}
