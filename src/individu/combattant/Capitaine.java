package individu.combattant;

/**
 * Initialise un Combattant avec les capacités d'un Capitaine
 */
public class Capitaine extends Combattant {

        /**
     * Constructeur
     * @param nom Le nom du Capitaine
     */
    public Capitaine(String nom) {
        super(nom);
        setVitesse(2);
        setDefense(2);
        setAttaque(3);
        setVie(30);
        _listeEquipement.setNbMaxEq(3);
    }
    
}
