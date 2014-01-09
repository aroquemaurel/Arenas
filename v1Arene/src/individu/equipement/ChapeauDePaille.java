package individu.equipement;

/**
 *  Initialise un équipement avec les capacités d'un chapeau de paill
 */
public class ChapeauDePaille extends Equipement {
	
	public ChapeauDePaille(String nom) {
        super(nom);
        setVitesse(1);
        setDefense(-1);
        setAttaque(1);
        setDuree(2);
	}
}
