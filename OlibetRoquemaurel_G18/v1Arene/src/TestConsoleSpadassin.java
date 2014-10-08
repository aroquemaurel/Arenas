import individu.combattant.Spadassin;

import java.rmi.RemoteException;

import controle.Console;

/**
 * Test de la Console avec un Element qui s'ajoute a l'Arene (apres lancement Arene et IHM). A lancer en plusieurs exemplaires.
 */
public class TestConsoleSpadassin {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		Spadassin zoro = new Spadassin("zoro");

		//Random r = new Random();
		new Console(zoro, 0, 40);
	}

}