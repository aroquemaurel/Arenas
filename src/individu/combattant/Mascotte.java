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
public class Mascotte extends Combattant {

    public Mascotte(String nom) {
        super(nom);
        setVitesse(4);
        setDefense(3);
        setAttaque(1);
        setVie(20);
        _listeEquipement.setNbMaxEq(5);
    }
    
}
