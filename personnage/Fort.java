package jeu.personnage;

public class Fort extends Personnage{

	public Fort(String pseudo) {
		super();
	}
	
	public int attaque(Personnage adversaire){
		int frappe;
		if(adversaire.getClass().equals(Agile.class)){
			frappe = 25;
		}else{
			frappe = 10;
		}
		return frappe;
	}

}
