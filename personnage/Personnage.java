package perso;

import static communication.ex01.Society.COMMUNITY;
import static communication.ex01.Society.GROUP;
import static communication.ex01.Society.ROLE;

import java.awt.Dimension;

import jeu.environnement.Modele;
import madkit.kernel.AbstractAgent;
import madkit.kernel.AgentAddress;
import madkit.kernel.Message;
import madkit.kernel.AbstractAgent.ReturnCode;

public class Personnage extends AbstractAgent {
	
	/**
	 * The agent's environment. 
	 * Here it is just used to know its boundaries. 
	 * It will be automatically set
	 * by the environment agent itself: No need to instantiate anything here.
	 */
	private Monde environment;
	
	/**
	 * agent's position
	 */
	private Dimension location = new Dimension();
	
	/**
	 * agent's team
	 */
	private String team = "";
	
	
	private String role = "";

	public Personnage(String team, String role) {
		this.team = team;
		this.role = role;
	}																																	

	/**
	 * initialize my role and fields
	 */
	@Override
	protected void activate() {
		requestRole(Modele.MY_COMMUNITY, team, role);
		Dimension envDim = environment.getDimension();
		location.width = (int) (Math.random()*envDim.width);
		location.height = (int) (Math.random()*envDim.height);
	}
	
	@SuppressWarnings("unused")
	private void action() {
		Dimension envDim = environment.getDimension();
		
		String TeamATuer = rechercheAdversaire();
		AgentAddress other = null;
		while(other == null){
			other = environment.getAgentWithRole(Modele.MY_COMMUNITY, TeamATuer, role);
		}
		ReturnCode code = null;
		
		//until I find someone having role
		while(code != ReturnCode.SUCCESS){
			//This will randomly choose a receiver having this role
			code = sendMessage(COMMUNITY, GROUP, ROLE, new Message());
			
		}
		location.width += Math.random()*4 - 1;
		location.height += Math.random()*4 - 1;
		location.width %= envDim.width;
		location.height %= envDim.height;
	}

	private String rechercheAdversaire() {
		String groupeatuer = "";
		
			// This way, I wait for another coming into play
			if(this.team.equals("RED")){
				groupeatuer = "BLUE";
			}else{
				groupeatuer = "RED";
			}
			
			
		
		return groupeatuer;
	}

}
