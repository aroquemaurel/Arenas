package individu.equipement;

import individu.Element;

/**
 * Equipement générique
 */
public class Equipement extends Element {
	private	int _duree; //!< Durée de vie d'un équipement
    
    /**
     * Constructeur
     * @param nom Le nom de l'équipement
     */
	public Equipement (String nom) {
		super(nom);
	}
	
    /**
     * Retourne la durée de vie d'un équipement
     * @return La durée de vie
     */
	public int getDuree() {
		return _duree;
	}
    
    /**
     * Réinitialise la durée de vie d'un équipement
     * @param pDuree La nouvelle durée de vie
     */
	public void setDuree(int pDuree) {
		_duree = pDuree;
	}
    
    /**
     * Retourne vrai si l'équation est respectée
     * @return Vrai si équatino respectée, faux sinon.
     */    
    @Override
    public boolean estCorrect() {
        return getDefense() + getAttaque() + getVitesse() == 1;
    }

}
