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
	private String nom;                                                      //le nom de l'element
	private int vie;                                                         //le nombre de vies de l'element
	private ArrayList<Integer> elementsConnus = new ArrayList<Integer>();    //les references des elements avec lesquels on a joue
	
	/**
	 * Constructeur
	 * @param nom le nom de l'element a creer
	 * le nombre de vie est par defaut initialise a 1
	 */
	public Element(String nom){
		this.nom = nom;
		this.vie = 1;
	}
	
	/**
	 * Constructeur
	 * @param nom le nom le l'element a creer
	 * @param vie le nombre de vies initiales
	 */
	public Element(String nom, int vie) {
		this.nom = nom;
		this.vie = vie;
	}

	public String getNom() {
		return this.nom;
	}

	public int getVie() {
		return this.vie;
	}

	public void setVie(int vie){
		this.vie = vie;
	}
	
	public ArrayList<Integer> getElementsConnus() {
		return this.elementsConnus;
	}

	public void ajouterConnu(int ref) {
		elementsConnus.add(ref);		
	}

	public String toString(){
		return this.getNom()+"["+this.getVie()+"]";
	}
}
