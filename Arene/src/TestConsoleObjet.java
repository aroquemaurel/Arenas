

import java.rmi.RemoteException;

import controle.Console;
import individu.Element;
import individu.Equipement;

public class TestConsoleObjet {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		int port=5099;	//par defaut, port de l'arene=5099
		if (args.length!=0) port=Integer.parseInt(args[0]);
		Element anduril = new Equipement("Bras Bionique", 10, 5, 0, 0, 0);

		//Random r = new Random();
		new Console(anduril, 40, 45, port);
	}

}
