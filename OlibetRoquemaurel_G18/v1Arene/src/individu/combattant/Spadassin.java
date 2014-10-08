package individu.combattant;

/**
 * Initialise un Combattant avec les capacités d'un Spadassin
 */
public class Spadassin extends Combattant {

    /**
     * Constructeur
     * @param nom Le nom du Spadassin
     */
    public Spadassin(String nom) {
        super(nom);
        setVitesse(3);
        setDefense(1);
        setAttaque(4);
        setVie(20);
        _listeEquipement.setNbMaxEq(1);
    }
    
}
