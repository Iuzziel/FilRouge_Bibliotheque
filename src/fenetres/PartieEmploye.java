package fenetres;

import panneaux.*;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import dao.Employee;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

public class PartieEmploye extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6154275497867212254L;
	
	private RechercherUnLivre rechercherUnLivre;
	private InformationLivre gestEmpruntInformationLivre;
	private RechercherUnAdherent rechercherUnAdherent;
	private Employee employeeConnecte = new Employee("");

	// Constructeur de la fenetre
	public PartieEmploye() {
		this.setMinimumSize(new Dimension(800, 600));
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.setLayout(new BorderLayout(5, 5));
		this.setVisible(true);
		initControle();
	}
	
	//Methodes
	private void initControle() {
		// Creation des modules composants ce Panel
		// Header
		Header headerEmploye = new Header("Espace employe de la bibliotheque");
		headerEmploye.getLblHeaderSeConnecter().setText("Se deconnecter");
		this.add(headerEmploye, BorderLayout.NORTH);
		
		// Creation du tabbedPane
		JTabbedPane tabEspaceEmploye = new JTabbedPane(JTabbedPane.TOP);
		tabEspaceEmploye.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		this.add(tabEspaceEmploye, BorderLayout.CENTER);
		
		// Creation des onglets
		// Creation des onglets/tabGestEmprunt
		JPanel tabGestEmprunt = new JPanel();
		tabGestEmprunt.setLayout(new GridLayout(0, 1, 5, 5));
		tabEspaceEmploye.addTab("Gestion des emprunts", null, tabGestEmprunt, null);
		
		// Creation des onglets/tabGestFond
		JPanel tabGestFond = new JPanel();
		tabGestFond.setLayout(new GridLayout(1, 2, 5, 5));
		tabEspaceEmploye.addTab("Gestion du fond", null, tabGestFond, null);
		
		// Creation des onglets/tabGestStock
		JPanel tabGestStock = new JPanel();
		tabEspaceEmploye.addTab("Gestion du stock", null, tabGestStock, null);
		
		// Creation des onglets/tabAdmin
		JPanel tabAdmin = new JPanel();
		tabAdmin.setLayout(new GridLayout(2, 2, 5, 5));
		tabEspaceEmploye.addTab("Gestion des comptes employ\u00E9s", null, tabAdmin, null);
		
		// *********************************************Tab gestion des	emprunts*********************************//
		// Creation Panel du haut
		JPanel panTabGestEmpruntHaut = new JPanel();
		tabGestEmprunt.add(panTabGestEmpruntHaut);
		panTabGestEmpruntHaut.setLayout(new BoxLayout(panTabGestEmpruntHaut, BoxLayout.X_AXIS));
		// Panel du haut Ajout du module recherche adherent
		rechercherUnAdherent = new RechercherUnAdherent();
		panTabGestEmpruntHaut.add(rechercherUnAdherent);
		// Panel du haut Ajout du module Emprunt/Retour
		EmpruntRetour empruntRetour = new EmpruntRetour();
		panTabGestEmpruntHaut.add(empruntRetour);
		
		// Creation Panel du bas
		// PanBas Ajout du module recherche livre
		JPanel panTabGestEmpruntBas = new JPanel();
		tabGestEmprunt.add(panTabGestEmpruntBas);
		panTabGestEmpruntBas.setLayout(new BorderLayout(0, 0));
		rechercherUnLivre = new RechercherUnLivre();
		panTabGestEmpruntBas.add(rechercherUnLivre, BorderLayout.WEST);
		// PanBas Ajout du module Information Livre
		gestEmpruntInformationLivre = new InformationLivre();
		gestEmpruntInformationLivre.setPreferredSize(new Dimension(300, 300));
		gestEmpruntInformationLivre.setMinimumSize(new Dimension(300, 300));
		panTabGestEmpruntBas.add(gestEmpruntInformationLivre, BorderLayout.CENTER);
		gestEmpruntInformationLivre.setCommentaireEditable(true);
		
		// **********************************Tab gestion du fond***********************************//
		// Creation Panel
		RechercherUnLivre gestFondRechercherUnLivre = new RechercherUnLivre();
		gestFondRechercherUnLivre.addBoutonCreerLivre();
		tabGestFond.add(gestFondRechercherUnLivre);
		InformationLivre gestFondInformationLivre = new InformationLivre();
		gestFondInformationLivre.setCommentaireEditable(true);
		tabGestFond.add(gestFondInformationLivre);
		
		// *********************************Tab gestion du stock******************************//
		// Creation Panel
		tabGestStock.setLayout(new BorderLayout(5, 5));
		tabGestStock.add(new GestionStock(), BorderLayout.WEST);
		tabGestStock.add(new GestionStockResultAffich(), BorderLayout.CENTER);
		
		// **********************************Tab Admin*************************************//
		// Creation Panel
		tabAdmin.setLayout(new BorderLayout(5, 5));
		tabAdmin.add(new GestionEmployes(), BorderLayout.WEST);
		tabAdmin.add(new GestionEmployesResultAffich(), BorderLayout.CENTER);
	}

	//Accesseurs
	public RechercherUnLivre getRechercherUnLivre() {
		return rechercherUnLivre;
	}
	public RechercherUnAdherent getRechercherUnAdherent() {
		return rechercherUnAdherent;
	}
	public InformationLivre getGestEmpruntInformationLivre() {
		return gestEmpruntInformationLivre;
	}
	public Employee getEmployeeConnecte() {
		return employeeConnecte;
	}
	public void setEmployeeConnecte(Employee employeeConnecte) {
		this.employeeConnecte = employeeConnecte;
	}
}
