import individu.equipement.Equipement;

import java.rmi.RemoteException;

import controle.Console;

/**
 * Test de la Console avec un Element qui s'ajoute a l'Arene (apres lancement Arene et IHM). A lancer en plusieurs exemplaires.
 */
public class TestConsoleBottes {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		Equipement bottesEnCuir = new Equipement("bottesEnCuir");

		//Random r = new Random();
		new Console(bottesEnCuir,40, 40);
	}

}
