import individu.equipement.Equipement;

import java.rmi.RemoteException;

import controle.Console;

/**
 * Test de la Console avec un Element qui s'ajoute a l'Arene (apres lancement Arene et IHM). A lancer en plusieurs exemplaires.
 */
public class TestConsoleSabre {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		Equipement katana = new Equipement("katana");

		//Random r = new Random();
		new Console(katana, 40, 40);
	}

}
