

import java.rmi.RemoteException;

import controle.Console;
import individu.Element;
import individu.Equipement;
import java.util.Random;

public class TestConsoleObjet {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		int port=5099;	//par defaut, port de l'arene=5099
		if (args.length!=0) port=Integer.parseInt(args[0]);
		Element anduril = new Equipement("Cadeau surprise :-)", 0, 0, -100, 0, 0);

		Random r = new Random();
		new Console(anduril, r.nextInt(100), r.nextInt(100), port);
        //new Console(anduril, 90, (90), port);
	}

}
