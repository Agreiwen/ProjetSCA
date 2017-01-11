package jeu.personnage;

public class Intelligent extends Personnage{

	public Intelligent(String pseudo) {
		super(pseudo);
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

}
