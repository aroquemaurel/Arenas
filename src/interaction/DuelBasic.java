package interaction;

import java.rmi.Remote;
import java.rmi.RemoteException;

import controle.IConsole;

import serveur.Arene;

public class DuelBasic implements IDuel {

	private Arene arene;          //l'arene sur laquelle le jeu a lieu
	private Remote refAttaquant;  //la reference de l'attaquant
	private Remote refDefenseur;  //la reference du defenseur
	
	/**
	 * Constructeur
	 * @param arene l'arene du jeu
	 * @param refAttaquant la reference de l'attaquant
	 * @param refDefenseur la reference du defenseur
	 * @throws RemoteException
	 */
	public DuelBasic(Arene arene, Remote refAttaquant, Remote refDefenseur) throws RemoteException{
		this.arene = arene;
		this.refAttaquant = refAttaquant;
		this.refDefenseur = refDefenseur;
	}

	/**
	 * Realise le combat 
	 */
	//TODO ajouter plus de caractéristiques car spadassin vs spadassin : mort directe.
	public int realiserCombat() throws RemoteException {
		Remote ratt = this.getRefAttaquant();
		IConsole catt = (IConsole) ratt;
		int atqAtt = catt.getElement().getAttaque();
		//int vitAtt = catt.getElement().getVitesse();
		
		Remote rdef = this.getRefDefenseur();	
		IConsole cdef = (IConsole) rdef;
		int defDef = cdef.getElement().getDefense();
		//int vitDef = catt.getElement().getVitesse();
		
		if(atqAtt <= defDef)
			cdef.perdreVie(1);
		else
			catt.perdreVie(atqAtt - defDef);
			
		return 0;
	}


	public Remote getRefAttaquant() throws RemoteException {
		return refAttaquant;
	}

	public Remote getRefDefenseur() throws RemoteException {
		return refDefenseur;
	}

}
