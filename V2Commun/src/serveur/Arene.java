package serveur;

import individu.Element;
import individu.Personne;
import individu.Equipement;
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
import java.util.Random;

import controle.IConsole;

import utilitaires.UtilitaireConsole;


/**
 * Le serveur ou se connectent les Consoles en RMI.
 * En localhost pour l'instant
 *
 */
public class Arene extends UnicastRemoteObject implements IArene, Runnable {

	private static final long serialVersionUID = 1L;
	private int port;                                      //port a utiliser pour la connexion
	private String ipName; 								   //nom de la machine hebergeant l'arene
	private int compteur = 0 ;                             //nombre d'elements connectes au serveur
    private  Hashtable<Remote,VueElement> elements = null; //elements connectes au serveur
    private Hashtable<Integer,String> ipAddrConsoles = null; //repertoire des refRMI et leur adresses ip
    //Attributs pour gerer l'apparition des objets de facon "ordonnee" et aleatoire
    private int nbElemMax = 100;
    private int compteurEquipements = 0;
    private Hashtable<Remote,VueElement> equipements = null;
    private Hashtable<Integer,String> ipAddrConsolesEquipements = null;
    //Attributs pour gerer l'apparition des personnes de facon "ordonnee" et aleatoire
    private int compteurPersonnes = 0;
    private Hashtable<Remote,VueElement> personnes = null;
    private Hashtable<Integer,String> ipAddrConsolesPersonnes = null;
    
	/**
	 * Constructeur 
	 * @param port le port de connexion
	 * @param ipName nom de la machine qui heberge l'arene 
	 * @throws Exception
	 */
	public Arene(int port, String ipName, int maxElem) throws Exception {
		super();
		this.port=port;
		this.ipName = ipName;
		Naming.rebind("rmi://"+this.ipName+":"+this.port+"/Arene",this);
		nbElemMax = maxElem;
		elements = new Hashtable<Remote,VueElement>();
		equipements = new Hashtable<Remote,VueElement>();
		personnes = new Hashtable<Remote,VueElement>();
		ipAddrConsoles = new Hashtable<Integer,String>();
		ipAddrConsolesEquipements = new Hashtable<Integer,String>();
		ipAddrConsolesPersonnes = new Hashtable<Integer,String>();
		compteurEquipements = nbElemMax;
		compteurPersonnes = nbElemMax;
		new Thread(this).start();
	}
	
	/**
	 *  Fournit une reference (entiere) pour un nouvel element dans l'arene, compte les elements
	 *  la synchro permet de garantir l'acces e un seul thread a la fois au compteur++  
	 * @return reference (entiere) utilisee pour rmi, compter les elements 
	 * @throws RemoteException */
	public synchronized int allocateRef() throws RemoteException {
		compteur++;
		return compteur;
	}
	
	/**
	 * boucle principale du thread serveur
	 */
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
	
	/**
	 * Verifie que les capacites soient positives et inferieures au seuil
	 * @param r
	 * @return
	 * @throws RemoteException
	 */
	public boolean verif (Remote r) throws RemoteException {
		Element e = ((IConsole) r).getElement();
		            if (e instanceof Personne) {
		            	Personne p = (Personne) e;
		                if (p.getVie()>100)
		                    return false;
		                if (p.getAttaque()<0 || p.getDefense()<0 || p.getEsquive()<0 || p.getInventaire()<0)
		                    return false;
		                if (p.getAttaque()+p.getDefense()+p.getEsquive()+p.getInventaire()>100)
		                    return false;
		                if (p.getEsquive()>50)
		                    return false;
		            }
		            return true;
		        }

	
	 /** Associe ("connecte") la representation d'un element en y associant un Remote, ajoute la representation d'un element dans l'arene 
		 * 	 * la synchro permet de garantir qu'on ne fait pas de nouvelle connection pdt un tour de jeu
		 * @param s vue (representation) de l'element 
		 * @param ipConsole adresse ip de la console qui se connecte
		 * @throws RemoteException
		 */
	  public synchronized void connect(VueElement s, String ipConsole) throws RemoteException {
			try {
				//creation du lien serveur-console
			    int portConsole = port+s.getRef(); //on associe un port unique a chaque console
			    Remote r=Naming.lookup("rmi://"+ipConsole+":"+portConsole+"/Console"+s.getRef());
			    //calcul aleatoire position initiale
			    Random alea = new Random();
			    ((IConsole) r).posInit(new Point(alea.nextInt(100),alea.nextInt(100)));
			    
			    //Comportement selon Equipement/Personne
			    if(((IConsole) r).getElement() instanceof Equipement){
			    	//on met les equipements dans la pile
			    	equipements.put(r, s);
            		ipAddrConsolesEquipements.put(s.getRef(), ipConsole);
            		compteurEquipements--;
            		//si on n'a plus de place dans la pile, on les ajoute a l'arene
            		if(compteurEquipements == 0){
            			//on ajoute les equipements en attente sur l'arene
            			elements.putAll(equipements);
            			ipAddrConsoles.putAll(ipAddrConsolesEquipements);
            			//mise a nbMax pour le prochain largage
            			compteurEquipements = nbElemMax;
            			equipements = new Hashtable<Remote, VueElement>();
            			ipAddrConsolesEquipements = new Hashtable<Integer, String>();
            		}
            	}
			    else 
			    	if (verif(r)){
			    		//on met les personnes dans la pile
			    		personnes.put(r, s);
			    		ipAddrConsolesPersonnes.put(s.getRef(),ipConsole);
			    		compteurPersonnes--;
			    		//si on n'a plus de place dans la pile, on les ajoute a l'arene
			    		if(compteurPersonnes == 0){
			    		//on ajoute les equipements en attente sur l'arene
			    		elements.putAll(personnes);
			    		ipAddrConsoles.putAll(ipAddrConsolesPersonnes);
			    		//mise a nbMax pour le prochain largage
            			compteurPersonnes= nbElemMax;
            			personnes = new Hashtable<Remote, VueElement>();
            			ipAddrConsolesPersonnes = new Hashtable<Integer, String>();
			    		}
			    	}
			    	else
			    		((IConsole) r).shutDown("Tu as triche vilain garcon! Tu es vire !");				
			} catch (Exception e) {
				System.out.println("Erreur lors de la connexion d'un client (ref="+s.getRef()+") :");
				e.printStackTrace();
			}
			
		}

	/**
	 * appele par l'IHM pour afficher une representation de l'arene
	 * via RMI, on envoie une copie (serialisee) du monde 
	 */
	public ArrayList<VueElement> getWorld() throws RemoteException {
		ArrayList<VueElement> aux=new ArrayList<VueElement>();
		for(VueElement s:elements.values()) {aux.add(s);}
		return aux; 
	}

	
	/**
	 * Liste des reference des voisins et leurs coordonnees a partir d'une position
	 */
	public Hashtable<Integer, VueElement> voisins(Point pos,int ref)
			throws RemoteException {
		//Hashtable<Integer, Point> aux=new Hashtable<Integer, Point>();
		
		Hashtable<Integer,VueElement> aux = new Hashtable<Integer, VueElement>();
		
		for(VueElement s:elements.values()) {
			if (((UtilitaireConsole.distanceChebyshev(s.getPoint(), pos))<10) & (s.getRef()!=ref)) {
				//aux.put(s.getRef(),new Point(s.getPoint().x,s.getPoint().y)); //on cree un nouveau point
				aux.put(s.getRef(), s);
			}
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
				((IConsole) r).run(); //on lance une execution
				elements.put(r,((IConsole) r).update()); //maj du serveur ac les infos du client, pourquoi clonage ??
				if (elements.get(r).getTTL()==0) {
					elements.remove(r);
					((IConsole) r).shutDown("Vous etes reste trop longtemps dans l'arene, vous etes elimine !");
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
	 */
	public int countClients() {
		return elements.size();
	}
	
	/**
	 * Appele par le run de la console. Permet a l'attaquant (ref1) d'attaquer (executer une frappe) le defenseur (ref2).
	 * Les regles de combat s'appliquent (en fonction de la force, de la defense et de l'esquive).
	 * Les caracteristiques du defenseur sont mises a jour (pas d'impact sur attaquant)
	 * Les deux protagonistes ajoutent leur adversaire dans les elements deja vus. 
	 */
	public void interaction(int ref1, int ref2) throws RemoteException {
		
		 try {
			 //recupere l'attaquant et le defenseur
			 int port1 = port+ref1;
		     int port2 = port+ref2;
		     String ip1 = ipAddrConsoles.get(ref1);
		     String ip2 = ipAddrConsoles.get(ref2);
		     Remote attaquant = Naming.lookup("rmi://"+ip1+":"+port1+"/Console"+ref1);
		     Remote defenseur = Naming.lookup("rmi://"+ip2+":"+port2+"/Console"+ref2);
		     
		     //cree le duel
			 DuelBasic duel = new DuelBasic(this,(IConsole) attaquant, (IConsole) defenseur);
			
			 //realise combat
			 duel.realiserCombat();
			
			 //ajout les elements avec lesquels on a joue dans la liste des elements connus
			 ((IConsole) attaquant).ajouterConnu(ref2);
			 ((IConsole) defenseur).ajouterConnu(ref1);		
		 } 
		 catch (MalformedURLException e) {
			 e.printStackTrace();
		 } 
		 catch (NotBoundException e) {
			 e.printStackTrace();
		 }
	}	
	
	/**
	 * Appele par le run de la console. Permet a l'element ref1 (Personne) de ramasser l'element ref2 (Equipement).
	 * Si l'equipement est trop lourd, il n'est pas ramasse, mais ajoute aux elements deja vus.
	 * Si l'equimement est ramasse, il modifie les caracteristiques de la personne.
	 */
	public void ramasser(int ref1, int ref2) throws RemoteException {
		try {
			//recupere le combattant et l'objet
			 int port1 = port+ref1;
		     int port2 = port+ref2;
		     String ip1 = ipAddrConsoles.get(ref1);
		     String ip2 = ipAddrConsoles.get(ref2);
		     Remote combattant = Naming.lookup("rmi://"+ip1+":"+port1+"/Console"+ref1);
		     Remote objet = Naming.lookup("rmi://"+ip2+":"+port2+"/Console"+ref2);
			
		     //tester l'objet avant de le ramasser...
		     Element equip = ((IConsole)objet).getElement();
		     Element personne = ((IConsole) combattant).getElement();
		     if (((Equipement)equip).totalEffetInventaire()<=((Personne)personne).getInventaire()) {
		    	 //ramasse l'objet
		    	 ((IConsole) combattant).ramasserObjet((IConsole)objet);
			
		    	 //mets a jour l'etat de l'objet comme ramasse
		    	 ((IConsole) objet).perdreVie(1);
			
		     }
		   //ajoute l'objet avec lesquels on a joue dans la liste des elements connus
			 ((IConsole) combattant).ajouterConnu(ref2);
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
