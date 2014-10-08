import individu.combattant.Capitaine;

import java.rmi.RemoteException;
import controle.Console;
import individu.combattant.Cyborg;

/**
 * Test de la Console avec un Element qui s'ajoute a l'Arene (apres lancement Arene et IHM). A lancer en plusieurs exemplaires.
 */
public class TestConsoleCyborg {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
        Cyborg franky = new Cyborg("franky");

		//Random r = new Random();
		new Console(franky, 40, 40);
	}

}