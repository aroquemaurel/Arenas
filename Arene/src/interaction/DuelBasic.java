package interaction;

import individu.Personne;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Random;

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
	 * Realise le combat avec equilibre
	 */
	public int realiserCombat() throws RemoteException {
		Remote ratt = this.getRefAttaquant();
        Personne patt = (Personne) ((IConsole) ratt).getElement();
        int atqAtt = patt.getForce();
        
        Remote rdef = this.getRefDefenseur();    
        Personne pdef = (Personne) ((IConsole) rdef).getElement();
        IConsole cdef = (IConsole) rdef;
        
        int defDef = pdef.getDefense();
        int esquiveDef = pdef.getEsquive()*(4/5);

        Random r=new Random();

        int hasard=r.nextInt(100);
        int forfait=(atqAtt / 10);  /* coup par défaut */
        
        /* si on ne peut esquiver on applique l'attaque en fonction de la defense
         * sinon, on n'applique pas d'attaque*/
        if (hasard>esquiveDef)
        {
        	/*On a pas réussi a esquiver */
            if (defDef>atqAtt)
                cdef.perdreVie(forfait);
            else
                cdef.perdreVie(forfait+(atqAtt - ((90*defDef)/100)));
        }
        else cdef.parler(": Nananère ! J'ai esquivé");
            return 0;
        }


	public Remote getRefAttaquant() throws RemoteException {
		return refAttaquant;
	}

	public Remote getRefDefenseur() throws RemoteException {
		return refDefenseur;
	}

}
