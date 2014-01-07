package individu.equipement;

import individu.Element;

public class Plastron extends Equipement{
	
	public Plastron(String nom) {
        super(nom);
        setVitesse(-1);
        setDefense(2);
        setAttaque(0);
        setDuree(5);
	}
}
