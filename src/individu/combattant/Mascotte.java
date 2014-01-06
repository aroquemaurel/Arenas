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
        setVitesse(40);
        setDefense(30);
        setAttaque(10);
        setVie(20);
    }
    
}
