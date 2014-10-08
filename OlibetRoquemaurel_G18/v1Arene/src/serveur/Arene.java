package serveur;

import individu.Element;
import individu.combattant.Combattant;
import individu.equipement.Equipement;
import interaction.DuelBasic;
import interfaceGraphique.VueElement;

import java.awt.Point;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import controle.IConsole;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilitaires.UtilitaireConsole;


/**
 * Le serveur ou se connectent les Consoles en RMI.
 * En localhost pour l'instant
 *
 */
public class  Arene extends UnicastRemoteObject implements IArene, Runnable {

	private static final long serialVersionUID = 1L;
	private int port;                                      //port a utiliser pour la connexion
	private int compteur = 0 ;                             //nombre d'elements connectes au serveur
    private  Hashtable<Remote,VueElement> elements; //elements connectes au serveur
	
	/**
	 * Constructeur 
	 * @param port le port de connexion
	 * @throws Exception
	 */
	public Arene(int port) throws Exception {
		super();
        this.elements = null;
		this.port=port;
		Naming.rebind("rmi://localhost:"+port+"/Arene",this);
       	elements = new Hashtable<Remote,VueElement>();
		new Thread(this).start();
	}
	
	/**
	 *  Fournit une reference (entiere) pour un nouvel element dans l'arene, compte les elements
	 *  la synchro permet de garantir l'acces e un seul thread a la fois au compteur++  
	 * @return reference (entiere) utilisee pour rmi, compter les elements 
	 * @throws RemoteException */
    @Override
	public synchronized int allocateRef() throws RemoteException {
		compteur++;
		return compteur;
	}
	
	/**
	 * boucle principale du thread serveur
	 */
    @Override
	public void run() {
		TimeoutOp to;
		while (true) {
			try {
				synchronized(this) {	//on verouille le serveur durant un tour de jeu -> pas de connexion/deconnexion
					// a cet instant, pour chaque client connecte, on verifie s'il est en vie
					for(Enumeration<Remote> enu=elements.keys(); enu.hasMoreElements();) {
						//boucle de jeu
						Remote r=enu.nextElement();
						to=new TimeoutOp(r);
						to.join(1000);
						if (to.isAlive()) {
							to=null;
							System.out.println("Depassement du temps (client ne+"+elements.get(r).getRef()+") !");
							elements.remove(r);
							((IConsole) r).shutDown("Presence sur l'arene trop long. Degage !");
						}
						else{
							Element elem = ((IConsole) r).getElement();
							
							if(elem.getVie() <= 0){
								System.out.println(elem.getNom() + " est mort...");
								elements.remove(r);
								((IConsole) r).shutDown("Vous etes mort...");
							}
						}
					}
				}
				Thread.sleep(1000);	//dormir 'au plus' 1 seconde (difference temps execution est 1sec.) pour permettre connexion/deconnexion des qconsoles
			} catch (Exception e) {e.getMessage();}
		}
	}

	
	 /** Associe ("connecte") la representation d'un element en y associant un Remote, ajoute la representation d'un element dans l'arene 
		 * la synchro permet de garantir qu'on ne fait pas de nouvelle connection pdt un tour de jeu
		 * @param s vue (representation) de l'element 
		 * @throws RemoteException
		 */
    @Override
	public synchronized void connect(VueElement s) throws RemoteException {
		try {
			Remote r=Naming.lookup("rmi://localhost:"+port+"/Console"+s.getRef());
            System.out.println("coucou");
            if(((IConsole)r).getElement().estCorrect()) {
                elements.put(r, s);
                System.out.println("coucou2");
            } else {
                ((IConsole) r).shutDown("Comment tu as trichéééééééé!!! Pas bien du tout ça.");				
            }
		} catch (Exception e) {
			System.out.println("Erreur lors de la connexion d'un client (ref="+s.getRef()+") :");
			e.printStackTrace();
		}
		
	}

	/**
	 * appele par l'IHM pour afficher une representation de l'arene
	 * via RMI, on envoie une copie (serialisee) du monde 
     * @throws java.rmi.RemoteException
	 */
    @Override
	public ArrayList<VueElement> getWorld() throws RemoteException {
		ArrayList<VueElement> aux=new ArrayList<VueElement>();
		for(VueElement s:elements.values()) {aux.add(s);}
		return aux; 
	}

	
	/**
	 * Liste des reference des voisins et leurs coordonnees a partir d'une position
     * @param pos
     * @param ref
     * @return 
     * @throws java.rmi.RemoteException 
	 */
    @Override
	public Hashtable<Integer, VueElement> voisins(Point pos,int ref)
			throws RemoteException {
        
            //Hashtable<Integer, Point> aux=new Hashtable<Integer, Point>();
            
            Hashtable<Integer,VueElement> aux = new Hashtable<Integer, VueElement>();
            try {
                Combattant combattantCourant = (Combattant)(UtilitaireConsole.intToConsole(ref)).getElement();
                Element e;
                for(VueElement s:elements.values()) {
                    if (((UtilitaireConsole.distanceChebyshev(s.getPoint(), pos))<10) & (s.getRef()!=ref)) {
                        // Si c'est un objet, on vérifie que son inventaire n'est pas plein
                        e = (UtilitaireConsole.intToConsole(s.getRef())).getElement();
                        if((e instanceof Equipement && !combattantCourant.getListeEquipement().nbMaxAtteind()) || 
                                e instanceof Combattant) {
                            aux.put(s.getRef(), s);
                        }
                    }
                }
        } catch (NotBoundException | MalformedURLException ex) {
            Logger.getLogger(Arene.class.getName()).log(Level.SEVERE, null, ex);
        }
            return aux;
	}

	/**
	 * Classe permettant de lancer une execution du client (run)
	 * dans un thread separe, pour pouvoir limiter son temps d'execution
	 * via un join(timeout)
	 *
	 */
	public class TimeoutOp extends Thread {
		private Remote r;
		TimeoutOp(Remote r) {this.r=r; start();}
		public void run() {
			try {
				if (((IConsole) r).getElement() instanceof Equipement) {
				} else {
					((IConsole) r).run(); //on lance une execution
					elements.put(r,((IConsole) r).update()); //maj du serveur ac les infos du client, pourquoi clonage ??
					if (elements.get(r).getTTL()==0) {
						elements.remove(r);
						((IConsole) r).shutDown("Vous etes reste trop longtemps dans l'arene, vous etes elimine !");
					}
				}
				
			} catch (Exception e) {
				//les exceptions sont inhibees ici, que ce soit une deconnection du client ou autre
				//en cas d'erreur, le client ne sera plus jamais execute
				//e.printStackTrace();
				elements.remove(r);
			} 
		}
	}
	

	/**
	 * Renvoie le nombre de clients connectes
     * @return 
	 */
	public int countClients() {
		return elements.size();
	}
	
    @Override
	public void interaction(int ref, int ref2) throws RemoteException {
		 try {
			 //recupere l'attaquant et le defenseur
			 IConsole attaquant = UtilitaireConsole.intToConsole(ref);
			 IConsole defenseur = UtilitaireConsole.intToConsole(ref2);
			 
			 if (defenseur.getElement() instanceof Equipement) {
				 ramasser(ref, ref2);
			 } else {
				 //cree le duel
				 DuelBasic duel = new DuelBasic(this,attaquant, defenseur);
				
				 //realise combat
				 duel.realiserCombat();
			 }
			 //ajout les elements avec lesquels on a joue dans la liste des elements connus
			 attaquant.ajouterConnu(ref2);
			 defenseur.ajouterConnu(ref);		
		 } 
		 catch (MalformedURLException e) {
			 e.printStackTrace();
		 } 
		 catch (NotBoundException e) {
			 e.printStackTrace();
		 }
	}	
	
    @Override
	public void ramasser(int ref, int ref2) throws RemoteException {
		 try {
			 //recupere l'element et l'objet
			 IConsole combattant = UtilitaireConsole.intToConsole(ref);
			 IConsole equipement = UtilitaireConsole.intToConsole(ref2);
             
			 Element buff = combattant.getElement(); 
			 if(!((Combattant)buff).getListeEquipement().nbMaxAtteind()) {
				combattant.ramasserObjet(equipement);
			 	supprimerElement(equipement);
			 }
		 } 
		 catch (MalformedURLException e) {
			 e.printStackTrace();
		 } 
		 catch (NotBoundException e) {
			 e.printStackTrace();
		 }
	}
	
	/**
	 * Supprime un element de la liste des elements connectes au serveur
	 * @param elem l'element a enlever
	 */
	public void supprimerElement(Remote elem) {
		this.elements.remove(elem);
	}


}
