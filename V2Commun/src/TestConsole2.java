

import individu.Element;
import individu.Personne;

import java.rmi.RemoteException;

import controle.Console;

/**
 * Test de la Console avec un Element qui s'ajoute a l'Arene (apres lancement Arene et IHM). A lancer en plusieurs exemplaires.
 */
public class TestConsole2 {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		int port=5099;	//par defaut, port de l'arene=5099
		if (args.length!=0) port=Integer.parseInt(args[0]);
		Personne bidule = new Personne("Truc", 20, 0, 0,20);
		new Console(bidule, 40, 40,port);
	}

}
