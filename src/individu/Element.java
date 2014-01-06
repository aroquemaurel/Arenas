/**
 * 
 */
package individu;

import java.io.Serializable;
import java.util.ArrayList;


public class Element implements IElement, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String nom;                                                      //le nom de l'element
	private int vie;                                                         //le nombre de vies de l'element
	private final ArrayList<Integer> elementsConnus;    //les references des elements avec lesquels on a joue
	
	/**
	 * Constructeur
	 * @param nom le nom de l'element a creer
	 * le nombre de vie est par defaut initialise a 1
	 */
	public Element(String nom){
        this.elementsConnus = new ArrayList<>();
		this.nom = nom;
		this.vie = 1;
	}
	
	/**
	 * Constructeur
	 * @param nom le nom le l'element a creer
	 * @param vie le nombre de vies initiales
	 */
	public Element(String nom, int vie) {
        this.elementsConnus = new ArrayList<Integer>();
		this.nom = nom;
		this.vie = vie;
	}

    @Override
	public String getNom() {
		return this.nom;
	}

    @Override
	public int getVie() {
		return this.vie;
	}

    @Override
	public void setVie(int vie){
		this.vie = vie;
	}
	
    @Override
	public ArrayList<Integer> getElementsConnus() {
		return this.elementsConnus;
	}

    @Override
	public void ajouterConnu(int ref) {
		elementsConnus.add(ref);		
	}

    @Override
	public String toString(){
		return this.getNom()+"["+this.getVie()+"]";
	}
}
