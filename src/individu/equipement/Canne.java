package individu.equipement;

import individu.Element;

public class Canne extends Equipement{
	
	public Canne(String nom) {
        super(nom);
        setVitesse(5);
        setDefense(-10);
        setAttaque(5);
        setVie(10);
        setDuree(1);
	}
}