/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package individu.combattant;

/**
 *
 * @author aroquemaurel
 */
public class Capitaine extends Combattant {

    public Capitaine(String nom) {
        super(nom);
        setVitesse(20);
        setDefense(20);
        setAttaque(30);
        setVie(30);
        _listeEquipement.setNbMaxEq(3);
    }
    
}
