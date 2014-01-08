package individu.combattant;

/**
 * Initialise un Combattant avec les capacités d'un Barde
 */
public class Barde extends Combattant {

    /**
     * Constructeur
     * @param nom Le nom du Barde
     */
    public Barde(String nom) {
        super(nom);
        setVie(10);
        setVitesse(4);
        setDefense(3);
        setAttaque(2);
        _listeEquipement.setNbMaxEq(2);
    }
    
}
