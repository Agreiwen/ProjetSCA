package jeu.personnage;

public class Intelligent extends Personnage{

	public Intelligent(String pseudo) {
		super();
	}
	
	public int attaque(Personnage adversaire){
		int frappe;
		if(adversaire.getClass().equals(Fort.class)){
			frappe = 25;
		}else{
			frappe = 10;
		}
		return frappe;
	}

	
	@Override
	protected void activate() {
		// TODO Auto-generated method stub
		super.activate();
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		super.end();
	}

}
