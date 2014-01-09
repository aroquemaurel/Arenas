package individu.combattant;

/**
 * Initialise un Combattant avec les capacités d'un Cyborg
 */
public class Cyborg extends Combattant {

        /**
     * Constructeur
     * @param nom Le nom du Cyborg
     */
    public Cyborg(String nom) {
        super(nom);
        setVie(20);
        setVitesse(1);
        setDefense(4);
        setAttaque(3);
        _listeEquipement.setNbMaxEq(4);
    }
    
}
