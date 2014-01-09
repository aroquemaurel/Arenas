package individu.combattant;

public interface ICombattant {

	/**
	 * Mets a jour l'argent que le combattant a gagne
	 * @param s le montant gagne
	 */
	public void gagner(int s);
	
	/**
	 * Mets a jour l'argent que le combattant a perdu
	 * @param s l'argent perdu
	 */
	public void perdre(int s);
	
	/** 
	 * Mets a jour la liste des objets ramasses par le combattant
	 * @param ref la reference (serveur) d'un equipement a ramasser
	 */
	public void ramasser(int ref);
    
    /**
     * Réinitialise le nombre d'objets maximum que peut porter un combattant
     * @param pNbObjets Le nouveau nombre d'objet
     */
    public void setNbObjets(final int pNbObjets);
    
    /**
     * Retourne le nombre d'objets maximum que peut porter un combattant
     * @return Le nombre d'objet
     */
    public int getNbObjets();
    
    /**
     * Réinitialie la quantité d'argent d'un combattant
     * @param pArgent La nouvelle quantité d'argent
     */
    public void setArgent(final int pArgent);
    
    /**
     * Retourne la quantité d'argent du combattant
     * @return La quantité d'argent
     */
    public int getArgent();
}
