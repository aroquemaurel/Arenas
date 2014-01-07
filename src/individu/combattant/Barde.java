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
public class Barde extends Combattant {

    public Barde(String nom) {
        super(nom);
        setVie(10);
        setVitesse(4);
        setDefense(3);
        setAttaque(2);
        _listeEquipement.setNbMaxEq(2);
    }
    
}
