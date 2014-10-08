package individu;

public class Equipement extends Element {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int bonusForce;                             //la force supplementaire qu'il fournit
	private int bonusDefense;                           //la defense supplementaire qu'il fournit
	private int bonusVie;                               //les vies supplementaies qu'il fournit
	private int bonusEsquive;
	private int bonusInventaire;
	
	
	
	/**
	 * @param nom
	 * @param bonusForce
	 * @param bonusDefense
	 * @param bonusVie
	 * @param bonusEsquive
	 * @param bonusInventaire
	 */
	public Equipement(String nom, int bonusForce, int bonusDefense,
			int bonusVie, int bonusEsquive, int bonusInventaire) {
		super(nom);
		this.bonusForce = bonusForce;
		this.bonusDefense = bonusDefense;
		this.bonusVie = bonusVie;
		this.bonusEsquive = bonusEsquive;
		this.bonusInventaire = bonusInventaire;
	}
	/**
	 * @return the bonusForce
	 */
	public int getBonusForce() {
		return bonusForce;
	}
	/**
	 * @param bonusForce the bonusForce to set
	 */
	public void setBonusForce(int bonusForce) {
		this.bonusForce = bonusForce;
	}
	/**
	 * @return the bonusDefense
	 */
	public int getBonusDefense() {
		return bonusDefense;
	}
	/**
	 * @param bonusDefense the bonusDefense to set
	 */
	public void setBonusDefense(int bonusDefense) {
		this.bonusDefense = bonusDefense;
	}
	/**
	 * @return the bonusVie
	 */
	public int getBonusVie() {
		return bonusVie;
	}
	/**
	 * @param bonusVie the bonusVie to set
	 */
	public void setBonusVie(int bonusVie) {
		this.bonusVie = bonusVie;
	}
	/**
	 * @return the bonusEsquive
	 */
	public int getBonusEsquive() {
		return bonusEsquive;
	}
	/**
	 * @param bonusEsquive the bonusEsquive to set
	 */
	public void setBonusEsquive(int bonusEsquive) {
		this.bonusEsquive = bonusEsquive;
	}
	/**
	 * @return the bonusInventaire
	 */
	public int getBonusInventaire() {
		return bonusInventaire;
	}
	/**
	 * @param bonusInventaire the bonusInventaire to set
	 */
	public void setBonusInventaire(int bonusInventaire) {
		this.bonusInventaire = bonusInventaire;
	}
	
	/**
	 * Calcul du poids de cet equipement
	 * @return
	 */
	public int totalEffetInventaire() {
		int limiteInventaire = 0;
		if (bonusForce >0) limiteInventaire+= bonusForce;
		if (bonusDefense >0) limiteInventaire+= bonusDefense;
		if (bonusVie >0) limiteInventaire+= bonusVie;
		if (bonusEsquive >0) limiteInventaire+= bonusEsquive;
		if (bonusInventaire >0) limiteInventaire+= bonusInventaire;
		return limiteInventaire*3/4;
	}
	/**
	 * Utilise par la methode parler de la console
	 */
	public String toString() {
        return this.getNom() +
            formatbonus('f', bonusForce) +
            formatbonus('d', bonusDefense) +
            formatbonus('v', bonusVie) +
            formatbonus('e', bonusEsquive) +
            formatbonus('i', bonusInventaire) +
            "";
    }

	/**
	 * Auxiliaire pour l'affichage
	 * @param lettre
	 * @param val
	 * @return
	 */
    private String formatbonus(char lettre, int val) {
        if (val == 0) return "";
        else return String.format(" %c%+d", lettre, val);
    }
	

}
