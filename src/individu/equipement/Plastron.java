package individu.equipement;

import individu.Element;

public class Plastron extends Equipement{
	
	public Plastron(String nom) {
        super(nom);
        setVitesse(-10);
        setDefense(20);
        setAttaque(0);
        setVie(0);
        setDuree(5);
	}
}
