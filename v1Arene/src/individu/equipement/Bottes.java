package individu.equipement;

/**
 *  Initialise un équipement avec les capacités d'une botte
 */
public class Bottes extends Equipement{
	
	public Bottes(String nom) {
        super(nom);
        setVitesse(2);
        setDefense(0);
        setAttaque(-1);
        setDuree(3);
	}
}