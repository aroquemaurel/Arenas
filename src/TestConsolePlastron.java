import individu.equipement.Equipement;

import java.rmi.RemoteException;

import controle.Console;

/**
 * Test de la Console avec un Element qui s'ajoute a l'Arene (apres lancement Arene et IHM). A lancer en plusieurs exemplaires.
 */
public class TestConsolePlastron {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		Equipement armure = new Equipement("armure");

		//Random r = new Random();
		new Console(armure,40, 40);
	}

}
