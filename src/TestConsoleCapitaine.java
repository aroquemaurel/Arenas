import individu.combattant.Capitaine;

import java.rmi.RemoteException;
import controle.Console;

/**
 * Test de la Console avec un Element qui s'ajoute a l'Arene (apres lancement Arene et IHM). A lancer en plusieurs exemplaires.
 */
public class TestConsoleCapitaine {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		Capitaine luffy = new Capitaine("luffy");

		//Random r = new Random();
		new Console(luffy, 40, 40);
	}

}
