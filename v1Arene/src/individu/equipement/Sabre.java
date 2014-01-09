package individu.equipement;

/**
 *  Initialise un équipement avec les capacités d'un sabre
 */
public class Sabre extends Equipement {
	
	public Sabre(String nom) {
        super(nom);
        setVitesse(-1);
        setDefense(0);
        setAttaque(2);
        setDuree(4);
	}
}
