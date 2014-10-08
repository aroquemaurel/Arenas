package individu.combattant;

/**
 * Initialise un Combattant avec les capacités d'une Mascotte
 */
public class Mascotte extends Combattant {

        /**
     * Constructeur
     * @param nom Le nom du Mascotte
     */
    public Mascotte(String nom) {
        super(nom);
        setVitesse(4);
        setDefense(3);
        setAttaque(1);
        setVie(20);
        _listeEquipement.setNbMaxEq(5);
    }
    
}
