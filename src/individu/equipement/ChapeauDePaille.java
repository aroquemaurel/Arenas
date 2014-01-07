package individu.equipement;

import individu.Element;

public class ChapeauDePaille extends Equipement{
	
	public ChapeauDePaille(String nom) {
        super(nom);
        setVitesse(-5);
        setDefense(-5);
        setAttaque(10);
        setVie(10);
        setDuree(2);
	}
}
