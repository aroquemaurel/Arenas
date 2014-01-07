package individu.equipement;

import java.util.Iterator;

import individu.Element;

public class Equipement extends Element {
	private	int _duree;
	public Equipement (String nom) {
		super(nom);
	}
	
	public int getDuree() {
		return _duree;
	}
	public void setDuree(int pDuree) {
		_duree = pDuree;
	}
}