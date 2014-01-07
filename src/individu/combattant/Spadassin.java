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
public class Spadassin extends Combattant {

    public Spadassin(String nom) {
        super(nom);
        setVitesse(3);
        setDefense(1);
        setAttaque(4);
        setVie(20);
        _listeEquipement.setNbMaxEq(1);
    }
    
}
