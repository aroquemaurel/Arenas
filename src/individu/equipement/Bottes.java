package individu.equipement;

import individu.Element;

public class Bottes extends Equipement{
	
	public Bottes(String nom) {
        super(nom);
        setVitesse(20);
        setDefense(0);
        setAttaque(-5);
        setVie(-5);
        setDuree(3);
	}
}
