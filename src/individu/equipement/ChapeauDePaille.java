package individu.equipement;

import individu.Element;

public class ChapeauDePaille extends Equipement{
	
	public ChapeauDePaille(String nom) {
        super(nom);
        setVitesse(1);
        setDefense(-1);
        setAttaque(1);
        setDuree(2);
	}
}
