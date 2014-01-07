package individu.equipement;

import individu.Element;

public class Sabre extends Equipement{
	
	public Sabre(String nom) {
        super(nom);
        setVitesse(-10);
        setDefense(0);
        setAttaque(20);
        setVie(4);
        //setDuree(4);
	}
}
