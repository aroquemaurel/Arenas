package individu;

import java.util.ArrayList;

/**
 * Interface pour un Element
 */
public interface IElement {
	/**
	 * Retourne le nom de l'element
     * @return Le nom
	 */
	public String getNom();
	
	/**
	 * Retourne le nombre de vies de l'element
     * @return La vie
	 */
	public int getVie();
	
	/**
	 * Reinitialise le nombre de vies de l'element
	 * @param vie le nouveau nombre de vie
	 */
	
	public void setVie(int vie);
	
    /**
     * Retourne la vitesse d'esquive
     * @return La vitesse
     */
    public int getVitesse();
    
    /**
     * Réinitialise la vitesse d'esquive
     * @param pVitesse La nouvelle vitesse
     */
    public void setVitesse(final int pVitesse);
    
    /**
     * Retourne la défense de l'élement
     * @return La défense
     */
    public int getDefense();
    
    /**
     * Réinitialise la défense de l'élément
     * @param pDefense La nouvelle défense
     */
    public void setDefense(final int pDefense);
    
    /**
     * Retourne la capacité d'attaque de l'élément
     * @return L'attaque
     */
    public int getAttaque();
    
    /**
     * Réinitialise l'attaque de l'élément
     * @param pAttaque La nouvelle attaque
     */
    public void setAttaque(final int pAttaque);
    
    /**
	 * Retourne les references des elements avec lesquels l'element courant a joue
     * @return 
	 */
	public ArrayList<Integer> getElementsConnus();
	
	/**
	 * Ajoute a la liste des elements connus (elements avec lesquels l'objet courant a joue) un nouvel element
	 * @param ref le reference de l'objet avec qui il joue
	 */
	public void ajouterConnu(int ref);
		
	/**
	 * Renvoie les informations concernant l'element courant
	 * @return chaine de caractere contenant au moins le nom de l'element et le nombre de vies tel qu'il sera affiche sur l'interface graphique
	 */
    @Override
	public String toString();
    
    /**
     * Retourne vrai si l'équation est respectée
     * @return Vrai si équatino respectée, faux sinon.
     */
    public boolean estCorrect();
}
