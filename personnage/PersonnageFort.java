package jeu.personnage;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import jeu.environnement.Modele;
import jeu.environnement.Monde;
import madkit.kernel.AbstractAgent;
import madkit.kernel.Message;
import madkit.message.StringMessage;

public class PersonnageFort extends AbstractAgent {

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
	
	int nombre = 0;
	int nbAdversaire = 0;
	int pointDeVie = 100;
	String teamATuer;
	Map<String, int[]> ennemis;

	public PersonnageFort(String team, String role) {
		this.team = team;
		this.role = role;
		ennemis = new HashMap<String, int[]>();
		teamATuer = rechercheAdversaire();
		if(teamATuer == "AGENTROUGE"){
			nbAdversaire = Modele.NB_ROUGE;
		}else{
			nbAdversaire = Modele.NB_BLEU;
		}
	}

	/**
	 * initialize my role and fields
	 */
	@Override
	protected void activate() {
		requestRole(Modele.MY_COMMUNITY, team, role);
		Dimension envDim = environment.getDimension();
		/*int xMinBleu = 0;
		int xMaxBleu = envDim.width;
		int yMinBleu = 0;
		int yMaxBleu = (envDim.height)/3;
		
		int xMinRouge = 0;
		int xMaxRouge = envDim.width;
		int yMinRouge = envDim.height - (envDim.height)/3;
		int yMaxRouge = envDim.height;
		
		if(this.role.equals("AGENTBLEU")){
			location.width = xMinBleu + (int)(Math.random() * ((xMaxBleu - xMinBleu) + 1));
			location.height = yMinBleu + (int)(Math.random() * ((yMaxBleu - yMinBleu) + 1));
		}else{
			location.width = xMinRouge + (int)(Math.random() * ((xMaxRouge - xMinRouge) + 1));
			location.height = yMinRouge + (int)(Math.random() * ((yMaxRouge - yMinRouge) + 1));
		}*/
		
		location.width = (int)(Math.random() * envDim.width);
		location.height = (int)(Math.random() * envDim.height);
	}

	//@Override
	protected void liver() {
		Dimension envDim = environment.getDimension();
		if(this.isAlive()) {
			//System.out.println("Salut, je suis "+this.getName());
			broadcastMessage(Modele.MY_COMMUNITY, Modele.SOLDAT, teamATuer, new StringMessage(location.width+":"+location.height+":"+this.getName()));
			while(nombre < nbAdversaire){
				Message m = nextMessage();
				if(m!=null){
					StringMessage sm = (StringMessage)m;
					remplirMap(sm);
					//System.out.println(sm.getContent());
				}
				nombre++;
			}
			//System.out.println("*************************************************");
			nombre = 0;
			/*for (String ennemi : ennemis.keySet()) {
				System.out.println("Je suis "+this.getName()+" -> Ennemi : "+ennemi+" en ("+ennemis.get(ennemi)[0]+","+ennemis.get(ennemi)[1]+")");
			}*/
			
			if(ennemis.size() != 0){
				int[] plusProcheEnnemi = coordPlusProcheEnnemi();
				deplacement(plusProcheEnnemi);
			}else{
				location.width += Math.random() * 4 - 1;
				location.height += Math.random() * 4 - 1;
				location.width %= envDim.width;
				location.height %= envDim.height;
			}
			tueAgent();
			effaceMap();
			System.out.println(this.pointDeVie);
		}
	}

	private void tueAgent() {
		for (String ennemi : ennemis.keySet()) {
			if(location.width == ennemis.get(ennemi)[0] && location.height == ennemis.get(ennemi)[1] && this.isAlive()){
				this.pointDeVie -= 25;
			}
		}
		if(pointDeVie <= 0){
			killAgent(this);
		}
	}
	
	private void effaceMap(){
		ennemis.clear();
	}

	private void remplirMap(StringMessage sm) {
		String s = sm.getContent();
		//System.out.println(s);
		String[] splitArray = s.split(":");
		/*for (int i = 0; i < splitArray.length; i++) {
			System.out.print(splitArray[i]+" ");
		}*/
		int coordX = Integer.parseInt(splitArray[0]);
		int coordY = Integer.parseInt(splitArray[1]);
		String ennemi = splitArray[2];
		//System.out.println("Ennemi : "+ennemi+" en ("+coordX+","+coordY+")");
		int[] coordonneesXY = new int[2];
		coordonneesXY[0] = coordX;
		coordonneesXY[1] = coordY;
		ennemis.put(ennemi, coordonneesXY);
	}
	
	private void deplacement(int[] coordonnesEnnemi){
		int coordEnnemiX = coordonnesEnnemi[0];
		int coordEnnemiY = coordonnesEnnemi[1];
		if(this.role == "AGENTROUGE"){
			//Mise a jour X
			if(location.width < coordEnnemiX){
				location.width = location.width+1;
			}else if(location.width > coordEnnemiX){
				location.width = location.width-1;
			}
			//Mise a jour Y
			if(location.height < coordEnnemiY){
				location.height = location.height+1;
			}else if(location.height > coordEnnemiY){
				location.height = location.height-1;
			}
		}else if(this.role == "AGENTBLEU"){
			//Mise a jour X
			if(location.width < coordEnnemiX){
				location.width = location.width+1;
			}else if(location.width > coordEnnemiX){
				location.width = location.width-1;
			}
			//Mise a jour Y
			if(location.height < coordEnnemiY){
				location.height = location.height+1;
			}else if(location.height > coordEnnemiY){
				location.height = location.height-1;
			}
		}
		
		location.width = (location.width + environment.getDimension().width) % environment.getDimension().width;
		location.height = (location.height + environment.getDimension().height) % environment.getDimension().height;
		if(location.width < 0 || location.width > 400){
			System.out.println(location.width);
		}
	}
	
	private int[] coordPlusProcheEnnemi(){
		int[] res = new int[2];
		int plusProcheEnnemiX = 0;
		int plusProcheEnnemiY = 0;
		double distanceMin = Double.MAX_VALUE;
		for (String ennemi : ennemis.keySet()) {
			double distance = distance(location.width, location.height, ennemis.get(ennemi)[0], ennemis.get(ennemi)[1]);
			if(distance < distanceMin){
				distanceMin = distance;
				plusProcheEnnemiX = ennemis.get(ennemi)[0];
				plusProcheEnnemiY = ennemis.get(ennemi)[1];
			}
		}
		res[0] = plusProcheEnnemiX;
		res[1] = plusProcheEnnemiY;
		return res;
	}

	private double distance(int xA, int yA, int xB, int yB) {
		return Math.sqrt((xB-xA)*(xB-xA) + (yB-yA)*(yB-yA));
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