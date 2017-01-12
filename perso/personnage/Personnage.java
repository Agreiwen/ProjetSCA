package perso.personnage;

import java.awt.Dimension;
import java.util.logging.Level;

import perso.environnement.Modele;
import perso.environnement.Monde;
import madkit.kernel.AbstractAgent;
import madkit.kernel.AgentAddress;
import madkit.kernel.Message;
import madkit.kernel.AbstractAgent.ReturnCode;
import madkit.kernel.Agent;
import madkit.message.StringMessage;
import madkit.testing.util.agent.KillTargetAgent;

public class Personnage extends AbstractAgent {

	/**
	 * The agent's environment. Here it is just used to know its boundaries. It
	 * will be automatically set by the environment agent itself: No need to
	 * instantiate anything here.
	 */
	private Monde environment;

	/**
	 * agent's position
	 */
	private Dimension location = new Dimension();

	/**
	 * agent's team
	 */
	public int pas = 1;
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
		location.width = (int) (Math.random() * envDim.width);
		location.height = (int) (Math.random() * envDim.height);
	}

	//@Override
	protected void liver() {
		//setLogLevel(Level.FINEST);
		Dimension envDim = environment.getDimension();
		if(this.isAlive()) {
			String TeamATuer = rechercheAdversaire();
			AgentAddress other = null;
			if (other == null) {
				other = environment.getAgentWithRole(Modele.MY_COMMUNITY, Modele.SOLDAT, TeamATuer);
				//System.out.println("Je suis " + this.getName() + " et j'ai trouvé " + other);
			//	sendMessage(other, new Message());
				sendMessage(other, new StringMessage(location.width+":"+location.height+":"+this));
				//broadcastMessage(Modele.MY_COMMUNITY, Modele.SOLDAT, TeamATuer, new StringMessage(location.width+":"+location.height+":"+this));
			}
			// System.out.println("moi je sors");
			Message m = nextMessage();
			
			//int cpt = 0;
			int newwidth = location.width;
			int newheight = location.height;
			
			if (m != null) {
				StringMessage sm = (StringMessage)m;
				System.out.println("Je suis " + this.getName() + " et mon message est : " + sm.getContent());
				newwidth = getCoordx(sm.getContent(),newwidth);
				newheight = getCoordy(sm.getContent(),newheight);
				System.out.println("Je suis en x :"+location.width+" y :"+location.height);
				System.out.println("Je vais en x :"+newwidth+" y :"+newheight);
				//if (logger != null)
				//	logger.info("I have to thank " + m.getSender());
				//sendReply(m, new StringMessage("thanks"));
				
				
				// TEST VIE adversaire ou kill pour test convergence
				location.width = newwidth;
				location.height = newheight;
				location.width %= envDim.width;
				location.height %= envDim.height;
				
			}else{
				location.width += Math.random() * 4 - 1;
				location.height += Math.random() * 4 - 1;
				location.width %= envDim.width;
				location.height %= envDim.height;
			}
			
			System.out.println("Je bouge random");
			
		}
	}

	private int getCoordy(String string, int newheight) {
		String[] Coord = string.toString().split(":");
		int coordy = Integer.parseInt(Coord[1]);
		//System.out.println("Je suis en y : "+coordy);
		int newcoordy = 0;
		if(newheight < coordy){
			newcoordy = newheight+1;
		}else if(newheight > coordy){
			newcoordy = newheight-1;
		}
		else{
			newcoordy = newheight;
		}
		return newcoordy;
	}

	private int getCoordx(String string, int newwidth) {
		String[] Coord = string.toString().split(":");
		//System.out.println("Je suis en x : "+Coord[0]);
		int coordy = Integer.parseInt(Coord[1]);
		//System.out.println("Je suis en x : "+coordy);
		int newcoordy = 0;
		if(newwidth < coordy){
			newcoordy = newwidth+1;
		}else if(newwidth > coordy){
			newcoordy = newwidth-1;
		}
		else{
			newcoordy = newwidth;
		}
		return newcoordy;
	}

	private String rechercheAdversaire() {
		String groupeatuer = "";

		// This way, I wait for another coming into play
		if (this.role.equals("AGENTROUGE")) {
			//this.pas = 2;
			groupeatuer = "AGENTBLEU";
		} else {
			groupeatuer = "AGENTROUGE";
		}

		return groupeatuer;
	}

}