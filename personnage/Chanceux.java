package jeu.personnage;

public class Chanceux extends Personnage{

	public Chanceux(String pseudo) {
		super(pseudo);
	}
	
	public int attaque(Personnage adversaire){
		int frappe;
		if(adversaire.getClass().equals(Intelligent.class)){
			frappe = 25;
		}else{
			frappe = 10;
		}
		return frappe;
	}

}
