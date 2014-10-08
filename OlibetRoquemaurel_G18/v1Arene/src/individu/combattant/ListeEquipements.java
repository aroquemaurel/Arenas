package individu.combattant;

import individu.equipement.Equipement;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Collection d'équipement possédant une valeur maximum d'élément.
 */
public class ListeEquipements extends ArrayList<Equipement> {
    private int _nbMaxEq; //!< Le nombre max d'équipement

    /**
     * Constructeur
     * @param pNbMaxEq Initialisation du nombre max d'équipement
     */
    public ListeEquipements(final int pNbMaxEq) {
        super();
        _nbMaxEq = pNbMaxEq;
    }
    
    /**
     * Ajoute un nouvel équipement
     * @param pEleme L'élément à ajouter
     * @return true si l'élément à été ajouté, 0 si le nombre max est atteind ou si une erreur s'est produite.
     */
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
    
    /**
     * Retourne si le nombre max d'équipement est atteind ou non.
     * @return  vrai si le nombre max d'équipement est atteind, faux sinon.
     */
    public boolean nbMaxAtteind() {
    	return size() >= _nbMaxEq;
    }
    
    /**
     * Retourne la somme du bonus d'attaque des équipements de la lite.
     * @return La somme d'attaque
     */
    public int getSommeAttaque() {
        Iterator<Equipement> it = iterator();
        int ret = 0;
        
        while(it.hasNext()) {
            ret += it.next().getAttaque();
        }
        
        return ret;
    }

    /**
     * Retourne la somme du bonus de défense des équipements de la liste.
     * @return La somme d'attaque
     */
    public int getSommeDefense() {
        Iterator<Equipement> it = iterator();
        int ret = 0;
        
        while(it.hasNext()) {
            ret += it.next().getDefense();
        }
        
        return ret;
    }
    
    /**
     * Retourne la somme du bonus de vitesse des équipements de la liste.
     * @return La somme d'attaque
     */
    public int getSommeVitesse() {
        Iterator<Equipement> it = iterator();
        int ret = 0;
        
        while(it.hasNext()) {
            ret += it.next().getVitesse();
        }
        
        return ret;
    }
    
    /**
     * Retourne la durée d'un équipement après le combat. 
     * Si la durée atteind 0, alors l'équipement est détruis de la liste.
     * 
     * @return La nouvelle durée
     */
    public int dureeApresCombat() {
        Iterator<Equipement> it = iterator();
        Equipement buff;
        int ret = 0;
        
        while(it.hasNext()) {
        	buff = it.next();
            buff.setDuree(buff.getDuree()-1);
            if(buff.getDuree() <= 0) {
            	remove(buff);
            }
        }
        
        return ret;
    }
    
    /**
     * Retourne le nombre maxium d'équipement possible
     * @return La nombre d'équipement
     */
    public int getNbMaxEq() {
        return _nbMaxEq;
    }

    /**
     * Réinitialise le nombre d'équipement maximum
     * @param  pNbMaxEq Le nouveau nombre maximum d'équipement
     */
    public void setNbMaxEq(int pNbMaxEq) {
        _nbMaxEq = pNbMaxEq;
    }
}
