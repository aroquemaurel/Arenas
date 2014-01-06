package individu;

import java.util.ArrayList;

public interface IElement {
	/**
	 * Retourne le nom de l'element
     * @return 
	 */
	public String getNom();
	
	/**
	 * Retourne le nombre de vies de l'element
     * @return 
	 */
	public int getVie();
	
	/**
	 * Reinitialise le nombre de vies de l'element
	 * @param vie le nouveau nombre de vie
	 */
	
	public void setVie(int vie);
	
    /**
     * 
     * @return 
     */
    public int getVitesse();
    /**
     * 
     * @param pVitesse
     * @return 
     */
    public void setVitesse(final int pVitesse);
    /**
     * 
     * @return 
     */
    public int getDefense();
    /**
     * 
     * @param pDefense
     * @return 
     */
    public void setDefense(final int pDefense);
    /**
     * 
     * @return 
     */
    public int getAttaque();
    /**
     * 
     * @param pAttaque
     * @return 
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
}
