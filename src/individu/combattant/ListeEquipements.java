/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package individu.combattant;

import individu.equipement.Equipement;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author aroquemaurel
 */
public class ListeEquipements extends ArrayList<Equipement> {
    private int _nbMaxEq;

    public ListeEquipements(final int pNbMaxEq) {
        super();
        _nbMaxEq = pNbMaxEq;
    }
    
    @Override
    public boolean add(Equipement pEleme) {
        boolean ret;
        if(!nbMaxAtteind()) {
            ret = super.add(pEleme);
        } else {
            ret = false;
        }
        
        return ret;
    }
    public boolean nbMaxAtteind() {
    	return size() >= _nbMaxEq;
    }
    public int getSommeAttaque() {
        Iterator<Equipement> it = iterator();
        int ret = 0;
        
        while(it.hasNext()) {
            ret += it.next().getAttaque();
        }
        
        return ret;
    }
    
    public int getSommeDefense() {
        Iterator<Equipement> it = iterator();
        int ret = 0;
        
        while(it.hasNext()) {
            ret += it.next().getDefense();
        }
        
        return ret;
    }
    
    public int getSommeVitesse() {
        Iterator<Equipement> it = iterator();
        int ret = 0;
        
        while(it.hasNext()) {
            ret += it.next().getVitesse();
        }
        
        return ret;
    }
    
    public int getSommeVie() {
        Iterator<Equipement> it = iterator();
        int ret = 0;
        
        while(it.hasNext()) {
            ret += it.next().getVie();
        }
        
        return ret;
    }
    
    public int getNbMaxEq() {
        return _nbMaxEq;
    }

    public void setNbMaxEq(int pNbMaxEq) {
        _nbMaxEq = pNbMaxEq;
    }
    

}
