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
	public int realiserCombat() throws RemoteException {
		Remote ratt = this.getRefAttaquant();
		IConsole catt = (IConsole) ratt;
		int vieAtt = catt.getElement().getVie();
		
		Remote rdef = this.getRefDefenseur();	
		IConsole cdef = (IConsole) rdef;
		int vieDef = cdef.getElement().getVie();
		
		if(vieAtt < vieDef)
			catt.perdreVie(1);
		else
			cdef.perdreVie(1);
		
		return 0;
	}


	public Remote getRefAttaquant() throws RemoteException {
		return refAttaquant;
	}

	public Remote getRefDefenseur() throws RemoteException {
		return refDefenseur;
	}

}
