package jeu.personnage;

public class Agile extends Personnage{

	public Agile(String pseudo) {
		super(pseudo);
	}

	public int attaque(Personnage adversaire){
		int frappe;
		if(adversaire.getClass().equals(Chanceux.class)){
			frappe = 25;
		}else{
			frappe = 10;
		}
		return frappe;
	}
}
