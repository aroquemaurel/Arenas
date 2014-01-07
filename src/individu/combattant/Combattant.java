/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package individu.combattant;

import individu.Element;

/**
 *
 * @author aroquemaurel
 */
public class Combattant extends Element implements ICombattant {
    private int _argent;
    protected final ListeEquipements _listeEquipement;
    
    public ListeEquipements getListeEquipement() {
		return _listeEquipement;
	}

	public Combattant(String nom) {
        super(nom);
        _argent = 0;
        _listeEquipement = new ListeEquipements(0);
    }
    
    @Override
    public void gagner(int s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void perdre(int s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ramasser(int ref) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNbObjets(int pNbObjets) {
        _listeEquipement.setNbMaxEq(pNbObjets);
    }

    @Override
    public int getNbObjets() {
        return _listeEquipement.getNbMaxEq();
    }

    @Override
    public void setArgent(int pArgent) {
        _argent = pArgent;
    }

    @Override
    public int getArgent() {
        return _argent;
    }
    
    @Override
    public int getAttaque() {
        return super.getAttaque() + _listeEquipement.getSommeAttaque();
    }
    
    @Override
    public int getDefense() {
        return super.getDefense() + _listeEquipement.getSommeDefense();
    }
    
    @Override
    public int getVitesse() {
        return super.getVitesse() + _listeEquipement.getSommeVitesse();
    }
    
    @Override
    public int getVie() {
        return super.getVie() + _listeEquipement.getSommeVie();
    }

}
