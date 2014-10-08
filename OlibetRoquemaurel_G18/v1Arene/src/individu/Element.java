package individu;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe générique pour les combattants et les Equipement implémentant IElement. 
 * @see Combattant
 * @see Equipement
 */
public class Element implements IElement, Serializable {
	/**
	 * vie/10 + vitesse + attaque + defense = 10
	 */
	private static final long serialVersionUID = 1L;
	private final String nom; //!<le nom de l'element
	private int vie; //!<le nombre de vies de l'element
    private int _vitesse; //!< Vitesse, soit une capacité d'esquive
    private int _defense; //!< Défene
    private int _attaque; //!< Attaque de l'élément
	private final ArrayList<Integer> elementsConnus;    //!<les references des elements avec lesquels on a joue
	
	/**
	 * Constructeur initialisant toutes les valeurs à 0
	 * @param nom le nom de l'element a creer
	 * le nombre de vie est par defaut initialise a 1
	 */
	public Element(String nom){
        this.elementsConnus = new ArrayList<Integer>();
		this.nom = nom;
		this.vie = 1;
        _vitesse = 0;
        _defense = 0;
        _attaque = 0;
	}

	/**
	 * Constructeur initialisant les valeurs à l'aide des paramètres
	 * @param pNom Le nom le l'element a creer
     * @param pVie Le nombre de vies initiales
     * @param pVitesse La vitesse permettant l'esquive
     * @param pDefense La défense intiale
     * @param pAttaque L'attaque initiale
	 */
	public Element(String pNom, final int pVie, final int pVitesse, final int pDefense, final int pAttaque) {
        elementsConnus = new ArrayList<Integer>();
		nom = pNom;
		vie = pVie;
        _vitesse = pVitesse;
        _defense = pDefense;
        _attaque = pAttaque;
	}

    /**
     * 
     * @return 
     */
    @Override
	public String getNom() {
		return this.nom;
	}

    /**
     * 
     * @return 
     */
    @Override
	public int getVie() {
		return this.vie;
	}

    /**
     * 
     * @param vie 
     */
    @Override
	public void setVie(int vie){
		this.vie = vie;
	}
	
    /**
     * 
     * @return 
     */
    @Override
	public ArrayList<Integer> getElementsConnus() {
		return this.elementsConnus;
	}

    /**
     * 
     * @param ref 
     */
    @Override
	public void ajouterConnu(int ref) {
		elementsConnus.add(ref);		
	}

    /**
     * 
     * @return 
     */
    @Override
	public String toString(){
		return this.getNom()+"["+this.getVie()+"]";
	}

    /**
     * 
     * @return 
     */
    @Override
    public int getVitesse() {
        return _vitesse;
    }

    /**
     * 
     * @param pVitesse 
     */
    @Override
    public void setVitesse(final int pVitesse) {
        _vitesse = pVitesse;
    }

    /**
     * 
     * @return 
     */
    @Override
    public int getDefense() {
        return _defense;
    }

    /**
     * 
     * @param pDefense 
     */
    @Override
    public void setDefense(int pDefense) {
        _defense = pDefense;
    }

    /**
     * 
     * @return 
     */
    @Override
    public int getAttaque() {
        return _attaque;
    }

    /**
     * 
     * @param pAttaque 
     */
    @Override
    public void setAttaque(int pAttaque) {
        _attaque = pAttaque;
    }

    /**
     * Retourne vrai si l'équation est respectée
     * @return Vrai si équation respectée, faux sinon.
     */
    @Override
    public boolean estCorrect() {
        System.out.println(_defense + " " + _attaque + " "+ _vitesse);
        return _defense+_attaque+_vitesse+(getVie()/10) == 10;
    }
}
