package individu.equipement;

import individu.Element;

public class Canne extends Equipement{
	
	public Canne(String nom) {
        super(nom);
        setVitesse(0);
        setDefense(-1);
        setAttaque(2);
        setDuree(1);
	}
}