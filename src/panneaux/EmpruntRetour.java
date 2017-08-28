package panneaux;

import dao.*;
import fenetres.FenetrePrincipale;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;

public class EmpruntRetour extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2734805873664823251L;

	// Donnees membres
	private String [] empruntCols = {"NUM_EXEMPLAIRE", "TITRE", "AUTEUR", "BIBLIOTHEQUE", "DISP"};
	private DefaultTableModel empruntListData = new DefaultTableModel(empruntCols, 0);
	private JTable jTabLivreEmpruntScan = new JTable(empruntListData);
	private JScrollPane srlLivreEmpruntScanne;
	private String [] retourCols = {"NUM_EXEMPLAIRE", "TITRE", "AUTEUR", "BIBLIOTHEQUE", "DISP"};
	private DefaultTableModel retourListData = new DefaultTableModel(retourCols, 0);
	private JTable jTabLivreRetourScan = new JTable(retourListData);
	private JScrollPane srlLivreRetourScanne;
	private JButton btnEmprAjouter = new JButton("Ajouter");
	private JButton btnEmprSupprimer = new JButton("Supprimer");
	private JButton btnEmpruntEnregistrerFinal = new JButton("Valider Emprunt");
	private JButton btnRetAjouter = new JButton("Ajouter");
	private JButton btnRetSupprimer = new JButton("Supprimer");
	private JButton btnValiderRetour = new JButton("Valider Retour");
	private Vector<Exemplaire> vectExempEmpr = new Vector<Exemplaire>();
	private Vector<Exemplaire> vectExempRet = new Vector<Exemplaire>();
	private int nbEmpruntMax = 0;
	private int nbEmpruntMaxAdherent = 0;
	private JLabel lblEmprStatut = new JLabel("");
	private JLabel lblRetStatut = new JLabel("");
	private int numEmpRetour;

	// Constructeur
	public EmpruntRetour() {
		setMinimumSize(new Dimension(200, 200));
		setPreferredSize(new Dimension(375, 250));
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Selection Emprunt/Retour", TitledBorder.LEADING, TitledBorder.TOP));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		initControle();
	}

	//Actions listeners
	private class appActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnEmprAjouter) {
				if(FenetrePrincipale.getPartieEmploye().getRechercherUnAdherent().isCotisationOk() 
						&& FenetrePrincipale.getPartieEmploye().getRechercherUnAdherent().isPenaliteOk()){
					btnEmprAjouter_Click();
				}else if(FenetrePrincipale.getPartieEmploye().getRechercherUnAdherent().isCotisationOk()){
					lblEmprStatut.setText("Payez d'abord la cotisation!");
				}else if (FenetrePrincipale.getPartieEmploye().getRechercherUnAdherent().isPenaliteOk()){
					lblEmprStatut.setText("Reglez d'abord la penalité!");
				}
			}
			if(e.getSource() == btnEmprSupprimer) {
				btnEmprSupprimer_Click();
			}
			if(e.getSource() == btnEmpruntEnregistrerFinal) {
				btnEmpruntEnregistrerFinal_Click();
			}
			if(e.getSource() == btnRetAjouter) {
				btnRetAjouter_Click();
			}
			if(e.getSource() == btnRetSupprimer) {
				btnRetSupprimer_Click();
			}
			if(e.getSource() == btnValiderRetour) {
				btnValiderRetour_Click();
			}
		}
	}

	//Methodes
	//Emprunt
	/**
	 * Retourne le nombre d'exemplaire max qu'un adherent peut emprunter, 
	 * en fonction du nombre qu'il a deja emprunte et pas encore rendu, 
	 * et du nombre max d'exemplaire emprunte defini dans la table parametre.
	 * @return int
	 */
	private int getNbEmpruntMaxAdherent(){
		int tmp = 0;
		try {
			tmp = nbEmpruntMax - EmpruntManager.getNbEmpruntAdhe(FenetrePrincipale.partieEmploye.getRechercherUnAdherent().getTempAdher().getNum_adherent());
			if(tmp == 0) {
				lblEmprStatut.setForeground(Color.RED);
				lblEmprStatut.setIcon(new ImageIcon(EmpruntRetour.class.getResource("/javax/swing/plaf/metal/icons/Warn.gif")));
				lblEmprStatut.setText("Nombre d'exemplaire emprunte maximum atteint");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Pkg:panneaux-Class:EmpruntRetour-Tag:3");
			e.printStackTrace();
		}
		return tmp;
	}

	/**
	 * Initialise le label du statut du panel emprunt.
	 * Vide, pas d'icone, couleur par defaut du text Rouge.
	 */
	private void initLblEmprStatut(){
		lblEmprStatut.setText("");
		lblEmprStatut.setIcon(null);
		lblEmprStatut.setForeground(Color.RED);
	}

	/**
	 * Ajoute l'exemplaire selectionne au vecteur temporaire d'emprunt,
	 * apres un click sur le bouton ajouter du panEmprunt.
	 */
	private void btnEmprAjouter_Click() {
		initLblEmprStatut();
		nbEmpruntMaxAdherent = getNbEmpruntMaxAdherent();
		if(vectExempEmpr.size() < nbEmpruntMaxAdherent){
			boolean nouveau = true;//Booleen pour verifier si l'exemplaire n'a pas ete ajoute plusieurs fois dans le meme emprunt
			Exemplaire ex = FenetrePrincipale.partieEmploye.getRechercherUnLivre().getTempRecherExemp();
			for (Exemplaire exTemp : vectExempEmpr) {
				if(exTemp.equals(ex)) nouveau = false;
			}
			try {
				if (nouveau && LigEmpruntManager.getDisp(ex).equals("Oui")) {
					vectExempEmpr.addElement(ex);
					empruntListData.addRow(ex.toEmpRetVector());
				}else if(!nouveau){
					lblEmprStatut.setIcon(new ImageIcon(EmpruntRetour.class.getResource("/javax/swing/plaf/metal/icons/Warn.gif")));
					lblEmprStatut.setText("Livre deja scanne!");
					System.out.println("Pkg:panneaux-Class:EmpruntRetour-Tag:1");
				}else{
					lblEmprStatut.setIcon(new ImageIcon(EmpruntRetour.class.getResource("/javax/swing/plaf/metal/icons/Warn.gif")));
					lblEmprStatut.setText("Livre indisponible!");
				}
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Pkg:panneaux-Class:EmpruntRetour-Tag:4");
				e.printStackTrace();
			}
		}else{
			lblEmprStatut.setForeground(Color.RED);
			lblEmprStatut.setIcon(new ImageIcon(EmpruntRetour.class.getResource("/javax/swing/plaf/metal/icons/Warn.gif")));
			lblEmprStatut.setText("Nombre d'exemplaire maximum atteint");
			System.out.println("Pkg:panneaux-Class:EmpruntRetour\nNb max d'emprunt atteint");
		}
	}

	/**
	 * Supprime l'exemplaire selectionne du vecteur temporaire d'emprunt,
	 * apres un click sur le bouton supprimer du panEmprunt.
	 */
	private void btnEmprSupprimer_Click() {
		initLblEmprStatut();
		empruntListData.removeRow(vectExempEmpr.size()-1);//Affichage
		vectExempEmpr.removeElement(vectExempEmpr.lastElement());//Vecteur de l'emprunt en cours
	}

	/**
	 * Enregistre un nouvel emprunt, 
	 * et y associe les livre du vecteur temp d'exemplaire dans LigEmprunt.
	 */
	private void btnEmpruntEnregistrerFinal_Click() {
		initLblEmprStatut();
		try {
			int tmpNumEmp = EmpruntManager.creerEmprunt(new Adherent(FenetrePrincipale.partieEmploye.getRechercherUnAdherent().getTempAdher().getNum_adherent()));
			for (Exemplaire ex : vectExempEmpr) {//Vecteur de l'emprunt en cours parcouru et ajouter dans LigEmprunt
				EmpruntManager.setEmprunt(tmpNumEmp, ex);
			}
			lblEmprStatut.setForeground(Color.GREEN);
			lblEmprStatut.setText("Enregistrement reussi.");
			empruntListData.setRowCount(0);//Affichage
			vectExempEmpr.removeAllElements();//Vecteur de l'emprunt en cours
		} catch (ClassNotFoundException | SQLException e1) {
			lblEmprStatut.setForeground(Color.RED);
			lblEmprStatut.setIcon(new ImageIcon(EmpruntRetour.class.getResource("/javax/swing/plaf/metal/icons/Warn.gif")));
			lblEmprStatut.setText("Erreur dans l'enregistrement de l'emprunt!");
			System.out.println("Pkg:panneaux-Class:EmpruntRetour-Tag:2");
			e1.printStackTrace();
		}			
	}

	//Methodes
	//Retour
	/**
	 * Initialise le label du statut du panel emprunt.
	 * Vide, pas d'icone, couleur par defaut du text Rouge.
	 */
	private void initLblRetStatut(){
		lblRetStatut.setText("");
		lblRetStatut.setIcon(null);
		lblRetStatut.setForeground(Color.RED);
	}

	private void btnRetAjouter_Click() {
		initLblRetStatut();
		Adherent tmpAdh = FenetrePrincipale.partieEmploye.getRechercherUnAdherent().getTempAdher();
		Exemplaire ex = FenetrePrincipale.partieEmploye.getRechercherUnLivre().getTempRecherExemp();
		if (tmpAdh != null){
			try {
				Vector<Integer> vTmp = EmpruntManager.getEmpruntAdhe(tmpAdh.getNum_adherent());
				boolean nouveau = true;
				boolean memeEmprunt = true;
				if(vectExempRet.isEmpty()){
					numEmpRetour = EmpruntManager.getEmpFromExemp(ex);//Si premier scan recup le num_emprunt associe
				}else{
					if((numEmpRetour == EmpruntManager.getEmpFromExemp(ex))?(memeEmprunt = true):(memeEmprunt = false));//verif si l'exemplaire vient du meme emprunt que les autres
				}
				for (Exemplaire exTmp : vectExempRet) {
					if(exTmp.getNum_exemplaire() == ex.getNum_exemplaire()) nouveau = false;//verif si l'exemplaire est deja scanne
				}
				for (Integer tmpInt : vTmp) {//ajout dans le retour si condition ok
					if(ex.getNum_exemplaire() == tmpInt && nouveau && memeEmprunt){
						vectExempRet.addElement(ex);
						retourListData.addRow(ex.toEmpRetVector());
					}else if(!nouveau && memeEmprunt){
						lblRetStatut.setText("Exemplaire deja ajoute.");
					}else if(!nouveau && !memeEmprunt){
						lblRetStatut.setText("Exemplaire n'appartenant pas au même emprunt.");
					}
				}
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Pkg:panneaux-Class:EmpruntRetour\nbtnRetAjouter_Click()");
				e.printStackTrace();
			}
		}else{
			lblRetStatut.setText("Selectionnez d'abord un Adherent valide.");
		}

	}
	private void btnRetSupprimer_Click() {
		initLblRetStatut();		
		retourListData.removeRow(vectExempRet.size()-1);//Affichage
		vectExempRet.removeElement(vectExempRet.lastElement());//Vecteur de l'emprunt en cours
		System.out.println("Pkg:panneaux-Class:EmpruntRetour\nvectExempRet.size() = " + vectExempEmpr.size());	
	}

	private void btnValiderRetour_Click() {
		initLblRetStatut();
		try {
			if(vectExempRet.size() == LigEmpruntManager.getNbExempInEmp(numEmpRetour)){
				try {
					int nbligneup = EmpruntManager.updateEmprunt(EmpruntManager.getEmpFromExemp(vectExempRet.elementAt(1)));
					if(nbligneup > 0) {
						lblRetStatut.setForeground(Color.GREEN);
						lblRetStatut.setText("Retour validé");
						retourListData.setRowCount(0);//Affichage
						vectExempRet.removeAllElements();//Vecteur de l'emprunt en cours
					}else{
						lblRetStatut.setText("Erreur, pas de retour mis a jour.");
					}
				} catch (ClassNotFoundException | SQLException e) {
					System.out.println("Pkg:panneaux-Class:EmpruntRetour-Tag:4-1\nbtnValiderRetour_Click()");	
					e.printStackTrace();
				}
			}else{
				lblRetStatut.setText("Tout les exemplaires de ce retour ne sont pas present!");//Pas de retour partiel, et un seul emprunt retourne par validation.
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Pkg:panneaux-Class:EmpruntRetour-Tag:4-2\nbtnValiderRetour_Click()");	
			e.printStackTrace();
		}

	}

	//Initialisation du panel
	private void initControle() {
		try {
			nbEmpruntMax = ParametreManager.getParametre(new Parametre("empruntmax")).getValeur();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Pkg:panneaux-Class:EmpruntRetour\nEchec chargement du parametre nb emprunt max");
			e.printStackTrace();
		}
		// tabEmpruntRetour
		JTabbedPane tabEmpruntRetour = new JTabbedPane(JTabbedPane.TOP);
		add(tabEmpruntRetour);

		// tabEmpruntRetour/panEmprunt
		JPanel panEmprunt = new JPanel();
		tabEmpruntRetour.addTab("Emprunt", panEmprunt);
		panEmprunt.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panEmprunt.setLayout(new BorderLayout(0, 0));

		JPanel panEmprCentre = new JPanel();
		panEmprunt.add(panEmprCentre);
		panEmprCentre.setLayout(new BoxLayout(panEmprCentre, BoxLayout.Y_AXIS));

		// tabEmpruntRetour/panEmprunt/Centre
		JLabel lblEmpruntTitre = new JLabel("Livre selectionne(s) : ");
		panEmprCentre.add(lblEmpruntTitre);
		srlLivreEmpruntScanne = new JScrollPane(jTabLivreEmpruntScan);
		panEmprCentre.add(srlLivreEmpruntScanne);
		srlLivreEmpruntScanne.setViewportBorder(new BevelBorder(BevelBorder.LOWERED));

		initLblEmprStatut();
		lblEmprStatut.setHorizontalAlignment(SwingConstants.CENTER);
		panEmprCentre.add(lblEmprStatut);

		// tabEmpruntRetour/panEmprunt/Sud
		JPanel panEmprSud = new JPanel();
		panEmprunt.add(panEmprSud, BorderLayout.SOUTH);
		panEmprSud.add(btnEmprAjouter);
		panEmprSud.add(btnEmprSupprimer);
		panEmprSud.add(btnEmpruntEnregistrerFinal);

		// tabEmpruntRetour/panRetour
		JPanel panRetour = new JPanel();
		tabEmpruntRetour.addTab("Retour", panRetour);
		panRetour.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panRetour.setLayout(new BorderLayout(0, 0));

		JPanel panRetCentre = new JPanel();
		panRetour.add(panRetCentre, BorderLayout.CENTER);
		panRetCentre.setLayout(new BoxLayout(panRetCentre, BoxLayout.Y_AXIS));

		// tabEmpruntRetour/panRetour/Centre
		JLabel lblRetourTitre = new JLabel("Livre selectionne(s) : ");
		panRetCentre.add(lblRetourTitre);
		srlLivreRetourScanne = new JScrollPane(jTabLivreRetourScan);
		panRetCentre.add(srlLivreRetourScanne);
		srlLivreRetourScanne.setViewportBorder(new BevelBorder(BevelBorder.LOWERED));

		initLblRetStatut();
		lblRetStatut.setHorizontalAlignment(SwingConstants.CENTER);
		panRetCentre.add(lblRetStatut);

		// tabEmpruntRetour/panRetour/Sud
		JPanel panRetSud = new JPanel();
		panRetour.add(panRetSud, BorderLayout.SOUTH);
		panRetSud.add(btnRetAjouter);
		panRetSud.add(btnRetSupprimer);
		panRetSud.add(btnValiderRetour);

		//Abonnement aux listeners
		btnEmprAjouter.addActionListener(new appActionListener());
		btnEmprSupprimer.addActionListener(new appActionListener());
		btnEmpruntEnregistrerFinal.addActionListener(new appActionListener());
		btnRetAjouter.addActionListener(new appActionListener());
		btnRetSupprimer.addActionListener(new appActionListener());
		btnValiderRetour.addActionListener(new appActionListener());
	}
}
