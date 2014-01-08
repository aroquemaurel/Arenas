package controle;

import individu.Element;
import individu.Personne;
import individu.Equipement;
import interfaceGraphique.VueElement;

import java.awt.Point;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;


import serveur.IArene;
import utilitaires.UtilitaireConsole;
import controle.Strategie;

/**
 * 
 * Se connecte au serveur avec un Element et sa VueElement.
 * "run" permet a l'Element/VueElement de se deplacer
 *
 */
public class Console extends UnicastRemoteObject implements IConsole {
	
	private static final long serialVersionUID = 1L;
	private int port=5099;	              //port par defaut pour communiquer avec l'arene
	private IArene serveur = null;                    //le serveur avec lequel le controleur communique
	private VueElement ve = null;                     //la vue de l'element (pour l'interface graphique)
	private Element elem = null;                      //l'element pour lequel le controleur est cree
	private Hashtable<Integer,VueElement> voisins;    //les vues des voisins sur l'interface graphique
	private Point pointErrance;                       //le point ou aller errer
	private int refRMI;                               //la reference (entiere) attribuee par le serveur a la connexion
	
	
	/**
	 * Constructeur
	 * @param elem l'element pour lequel le controleur est cree
	 * @param dx la position initiale de l'element sur l'ordonnee (interface graphique)
	 * @param dy la position initiale de l'element sur l'abscisse (interface graphique)
	 * @param port numero de port de l'arene 5099 par defaut 
	 * @throws RemoteException
	 */
	public Console(Element elem, int dx, int dy, int port) throws RemoteException {
		 //appel au constructeur de la super-classe -> il peut etre implicite
		super();
		this.port = port;
		try{
			//initialisation de l'element pour lequel le controleur est cree
			this.elem=elem;
			
			//position initiale aleatoire
			//Random r=new Random();
			//Point pos = new Point(r.nextInt(100),r.nextInt(100));
			
			//Creation de la position initiale de la vue de l'element sur l'interface graphique
			Point pos = new Point(dx,dy);
			
			//initialisation des voisins (vide avant la connexion)
			voisins = new Hashtable<Integer,VueElement>();
			
			//preparation connexion au serveur
			//handshake/enregistrement RMI
			//serveur=(IArene) java.rmi.Naming.lookup("rmi://ouvea.edu.ups-tlse.fr:"+this.port+"/Arene");
			serveur=(IArene) java.rmi.Naming.lookup("rmi://localhost:"+this.port+"/Arene");
			serveur.toString();
			
			//initialisation de la reference du controleur sur le serveur
			//La console devient "serveur" pour les methodes de IConsole 
			//lancer l'annuaire rmi en tant que serveur. A faire une seule fois par serveur de console pour un port donne
			this.refRMI=((IArene) serveur).allocateRef();
			int portServeur= this.port+refRMI;
			java.rmi.registry.LocateRegistry.createRegistry(portServeur);
			Naming.rebind("rmi://localhost:"+(portServeur)+"/Console"+refRMI,this);
		
			
			//initialisation de la vue sur l'element
			ve=new VueElement(refRMI, pos, this, "Atterrissage...");
						
			//connexion a l'arene pour lui permettre d'utiliser les methodes de IConsole
			String ipConsole =InetAddress.getLocalHost().getCanonicalHostName();
			
			serveur.connect(ve,ipConsole);
			
			//affiche message si succes
			System.out.println("Console connectee ["+refRMI+"]");
 		} catch (Exception e) {
  			System.out.println("Erreur : la console n'a pa pu etre creee !\nCause : "+e.getCause());
  			e.printStackTrace();
 		}
	}


	/**
	 * Permet au serveur de faire "jouer" un tour a l'element.
	 * Calcule ses voisins (donnes par le serveur), cherche le plus proche, s'il est a proximite, lance l'interaction sinon se dirige vers lui (s'il existe un plus proche)
	 * Cette methode est execute chaque seconde  
	 */
	public void run() throws RemoteException {
		//si l'element auquel le controleur est associe est une personne
		if(elem instanceof Personne) {
			//decremente sa duree de vie
			ve.decrTTL(); 
			
			//mets a jour ses voisins 
			voisins = ((IArene) serveur).voisins(ve.getPoint(),refRMI);	
			
		
			// Recherche du plus proche, sinon errer
			
			HashMap<Integer, HashMap<Integer,VueElement>> resultat = Strategie.chercherElementProche(ve, voisins);
			int distPlusProche = resultat.keySet().iterator().next();
			int refPlusProche =  resultat.get(distPlusProche).keySet().iterator().next();
			VueElement cible = resultat.get(distPlusProche).get(refPlusProche);
			
			
			//i le plus proche est a proximite
			if (distPlusProche<=1) { //si la cible est un equipement
				if(cible.getControleur().getElement() instanceof Equipement) {
					//ramassage
					parler("Je tente de ramasser un objet");
					((IArene) serveur).ramasser(refRMI, refPlusProche);
				}
				else {
					//si la cible est une personne
					if(cible.getControleur().getElement() instanceof Personne) {
						//jeu
						parler("Je joue avec "+refPlusProche);
						((IArene) serveur).interaction(refRMI, refPlusProche);
					}
				}
			}
			//sinon
			else { 
				if (refPlusProche!=0) {
					parler("Je me dirige vers "+refPlusProche);
				}
				else parler("J'erre..."); 
			
				//l'element courant se deplace vers le plus proche (s'il existe) sinon il erre
				seDirigerVers(refPlusProche);	
			}
		}
	}
	
	/**
	 * Deplace ce sujet d'une case en direction du sujet dont la reference est donnee en parametre
	 * @param ref la reference de l'element cible
	 */
	public void seDirigerVers(int ref) {
		Point pvers;
		
		//si la cible est l'element meme, il reste sur place
		if (ref==ve.getRef()) return;

		//s'il n'y a pas de reference la plus proche
		if (ref==0) {
			//si le point ou l'element se trouve est le point d'errance precedemment defini
			if ((pointErrance!=null) && (pointErrance.equals(ve.getPoint()))) { 
				//le point d'errance est reinitialise
				pointErrance=null;
			}
			if (pointErrance==null) {
				//initialisation aleatoire
				Random r=new Random();
				pointErrance=new Point(r.nextInt(100), r.nextInt(100));
			}
			//la cible devient le nouveau point d'errance
			pvers=pointErrance;
		} 
		//sinon la cible devient le point sur lequel se trouve l'element le plus proche
		else {
			pvers=voisins.get(ref).getPoint();
		}
		
		//si l'element n'existe plus (cas posible: deconnexion du serveur), le point reste sur place
		if (pvers==null) return;
		
		//calcule la direction pour atteindre la ref (+1/-1 par rapport a la position courante)
		int dx=(int) (pvers.getX()-ve.getPoint().x);
		if (dx!=0)	
			dx=dx/Math.abs(dx);
		int dy=(int) (pvers.getY()-ve.getPoint().y);
		if (dy!=0)  
			dy=dy/Math.abs(dy);
		
		//instancie le point destination
		Point dest = new Point(ve.getPoint().x+dx,ve.getPoint().y+dy);
		
		//si le point destination est libre
		if (UtilitaireConsole.caseVide(dest,voisins)) {		
			//l'element courant se deplace
			ve.setPoint(dest);
		} 
		else {
			//cherche la case libre la plus proche dans la direction de la cible
			Point top = UtilitaireConsole.meilleurPoint(ve.getPoint(),dest,voisins);
			//deplace l'element courant sur celle-la
			ve.setPoint(top);
		}
	}

	/**
	 * Appelle par le serveur pour faire la MAJ du sujet 
	 */
	public VueElement update() throws RemoteException {
		VueElement aux=(VueElement) ve.clone();
		aux.setPhrase(ve.getPhrase()); 
		return aux;
	}

	/**
	 * Deconnexion du controleur du serveur
	 * @param cause le message a afficher comme cause de la deconnexion
	 */
	public void shutDown(String cause) throws RemoteException {
		System.out.println("Console "+refRMI+" deconnectee : "+cause);
		System.exit(1);
	}

	public Element getElement() throws RemoteException {
		return elem;
	}
	
	public VueElement getVueElement() throws RemoteException {
		return ve;
	}
	
	public void parler(String s) throws RemoteException {
		ve.setPhrase(s);	
	}
	

	public void perdreVie(int viePerdue) throws RemoteException {
		this.elem.setVie(this.elem.getVie()-viePerdue);
		System.out.println("Ouch, j'ai perdu " + viePerdue + " points de vie. Il me reste " + this.elem.getVie() + " points de vie.");		
	}
	
	/** Appliquer l'effet d'avoir ramasse un objet */
	public void ramasserObjet(IConsole objet) throws RemoteException {
		int bonusForce,bonusDefense,bonusVie,bonusEsquive,bonusInventaire;
		//calcul des bonus/capacites
		bonusForce=((Equipement)(objet.getElement())).getBonusForce();
		bonusDefense=((Equipement)(objet.getElement())).getBonusDefense();
		bonusVie=((Equipement)(objet.getElement())).getBonusVie();
		bonusEsquive=((Equipement)(objet.getElement())).getBonusEsquive();
		bonusInventaire=((Equipement)(objet.getElement())).getBonusInventaire();
		int force = ((Personne)this.elem).getForce();
		int defense = ((Personne)this.elem).getDefense();
		int vie = ((Personne)this.elem).getVie();
		int esquive = ((Personne)this.elem).getEsquive();
		int inventaire = ((Personne)this.elem).getInventaire();
		//calcul du seuil
		if (force+bonusForce>100) force=100; else force= force+bonusForce;
		if (defense+bonusDefense>100) defense=100; else defense=defense+bonusDefense;
		if (vie+bonusVie>100) vie=100; else vie=vie+bonusVie;
		if (esquive+bonusEsquive>50) esquive=50; else esquive=esquive+bonusEsquive;
		if (inventaire+bonusInventaire>100) inventaire=100; else inventaire=inventaire+bonusInventaire;
		//application du bonus
		((Personne)this.elem).setForce(force);
		((Personne)this.elem).setDefense(defense);
		((Personne)this.elem).setVie(vie);
		((Personne)this.elem).setEsquive(esquive);
		((Personne)this.elem).setInventaire(inventaire);
		//mise a jour de l'inventaire
		((Personne)this.elem).setInventaire(inventaire - ((Equipement)(objet.getElement())).totalEffetInventaire());
		//mise a zero Equipement pour empecher double emploi
		((Equipement)(objet.getElement())).setBonusForce(0);
		((Equipement)(objet.getElement())).setBonusDefense(0);
		((Equipement)(objet.getElement())).setBonusVie(0);
		((Equipement)(objet.getElement())).setBonusEsquive(0);
		((Equipement)(objet.getElement())).setBonusInventaire(0);
	}
	
	public String afficher() throws RemoteException{
		return this.elem.toString();
	}

	/**
	 * Ajout l'element dans la liste des elements connus (combattants et equipements)
	 * @param ref l'element a ajouter
	 */
	public void ajouterConnu(int ref) throws RemoteException {
		elem.ajouterConnu(ref);
	}
		
}
