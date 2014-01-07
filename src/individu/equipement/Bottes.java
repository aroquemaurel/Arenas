package individu.equipement;

import individu.Element;

public class Bottes extends Equipement{
	
	public Bottes(String nom) {
        super(nom);
        setVitesse(2);
        setDefense(0);
        setAttaque(-1);
        setDuree(3);
	}
}