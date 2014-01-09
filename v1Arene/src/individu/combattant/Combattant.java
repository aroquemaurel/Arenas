package individu.combattant;

import individu.Element;

/**
 * Combattant générique
 * @see ListeEquipements
 */
public class Combattant extends Element implements ICombattant {
    private int _argent; //!< Argent du combattant
    protected final ListeEquipements _listeEquipement; //!< Liste contenant les équipements que possède le combattant
    
    /**
     * Constructeur initialiant toutes les capacités à 0
     * @param nom Nom
     */
	public Combattant(String nom) {
        super(nom);
        _argent = 0;
        _listeEquipement = new ListeEquipements(0);
    }

    /**
     * Constructeur initialisant les valeurs du combattant
     * @param pNom Nom
     * @param pVie Vie
     * @param pVitesse Vitesse
     * @param pDefense Défense 
     * @param pAttaque Attaque
     * @param nbObjetsMax Nombre maximum d'objets
     */
    public Combattant(String pNom, final int pVie, final int pVitesse, final int pDefense, final int pAttaque, final int nbObjetsMax) {
        super(pNom, pVie, pVitesse, pDefense, pAttaque);
        _argent = 0;
        _listeEquipement = new ListeEquipements(0);
    }
    
    /**
     * 
     * @return 
     */
    public ListeEquipements getListeEquipement() {
		return _listeEquipement;
	}

    /**
     * 
     * @param s 
     */
    @Override
    public void gagner(int s) {
        // Nous n'utilisons pas d'argent
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    /**
     * 
     * @param s 
     */
    @Override
    public void perdre(int s) {
        // Nous n'utilisons pas d'argent
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    /**
     * 
     * @param ref 
     */
    @Override
    public void ramasser(int ref) {
        throw new UnsupportedOperationException("Not supported yet.");  // TODO
    }

    /**
     * Réinitialise le nombre d'objets maximum
     * @param pNbObjets Nouveau nombre d'objet
     */
    @Override
    public void setNbObjets(final int pNbObjets) {
        _listeEquipement.setNbMaxEq(pNbObjets);
    }

    /**
     * Retourne le nombre d'objets maximum du combattant
     * @return Le nombre d'objet
     */
    @Override
    public int getNbObjets() {
        return _listeEquipement.getNbMaxEq();
    }

    /**
     * Réinitialie l'argent du combattant
     * @param pArgent La nouvelle valeure
     */
    @Override
    public void setArgent(int pArgent) {
        _argent = pArgent;
    }

    /**
     * Retourne l'argent du combattant
     * @return L'argent
     */
    @Override
    public int getArgent() {
        return _argent;
    }
    
    /**
     * Retourne la capacité d'attaque du combattant en prenant en compte son équipement
     * @return La capacité d'attaque
     */
    @Override
    public int getAttaque() {
        return super.getAttaque() + _listeEquipement.getSommeAttaque();
    }
    
    /**
     * Retourne la défense du combattant en prenant en compte son équipement
     * @return La défense
     */
    @Override
    public int getDefense() {
        return super.getDefense() + _listeEquipement.getSommeDefense();
    }
    
    /**
     * Retourne la vitesse d'esquive en prenant en compte son équipement
     * @return La vitesse
     */
    @Override
    public int getVitesse() {
        return super.getVitesse() + _listeEquipement.getSommeVitesse();
    }
    
    /**
     * Met à jour la durée de vie de l'équipement du combattant
     * @return La nouvelle durée
     */
    public int majDureeEquip() {
    	return _listeEquipement.dureeApresCombat();
    }
    
}
