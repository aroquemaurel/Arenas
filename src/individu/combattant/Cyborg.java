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
public class Cyborg extends Combattant {

    public Cyborg(String nom) {
        super(nom);
        setVie(20);
        setVitesse(10);
        setDefense(40);
        setAttaque(30);
        _listeEquipement.setNbMaxEq(4);
    }
    
}
