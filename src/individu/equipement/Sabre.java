package individu.equipement;

import individu.Element;

public class Sabre extends Equipement{
	
	public Sabre(String nom) {
        super(nom);
        setVitesse(-1);
        setDefense(0);
        setAttaque(2);
        setDuree(4);
	}
}
