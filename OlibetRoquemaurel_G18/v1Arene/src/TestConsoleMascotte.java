import individu.combattant.Mascotte;

import java.rmi.RemoteException;

import controle.Console;

/**
 * Test de la Console avec un Element qui s'ajoute a l'Arene (apres lancement Arene et IHM). A lancer en plusieurs exemplaires.
 */
public class TestConsoleMascotte {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		Mascotte choppeur = new Mascotte("choppeur");

		//Random r = new Random();
		new Console(choppeur, 20, 40);
	}

}