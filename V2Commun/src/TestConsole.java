

import individu.Personne;

import java.rmi.RemoteException;

import controle.Console;
import java.util.Random;

/**
 * Test de la Console avec un Element qui s'ajoute a l'Arene (apres lancement Arene et IHM). A lancer en plusieurs exemplaires.
 */
public class TestConsole {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		int port=5099;	//par defaut, port de l'arene=5099
		if (args.length!=0) port=Integer.parseInt(args[0]);
		Personne bidule = new Personne("Franky_B18", 70, 0, 30, 0);
		Random r = new Random();
		new Console(bidule, r.nextInt(100), r.nextInt(100),port);
	}

}