package individu;

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
}
