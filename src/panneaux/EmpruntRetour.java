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
	private Vector<Exemplaire> vectExemplaire = new Vector<Exemplaire>();
	private int nbEmpruntMax = 0;
	private int nbEmpruntMaxAdherent = 0;
	private JLabel lblEmprStatut = new JLabel("");

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
				lblEmprStatut.setText("");
				lblEmprStatut.setIcon(null);
				lblEmprStatut.setForeground(Color.RED);
				nbEmpruntMaxAdherent = getNbEmpruntMaxAdherent();
				if(vectExemplaire.size() < nbEmpruntMaxAdherent){
					btnEmprAjouter_Click();
				}else{
					System.out.println("Pkg:panneaux-Class:EmpruntRetour\nNb max d'emprunt atteint");
				}
			}
			if(e.getSource() == btnEmprSupprimer) {
				lblEmprStatut.setText("");
				lblEmprStatut.setIcon(null);
				lblEmprStatut.setForeground(Color.RED);
				empruntListData.removeRow(vectExemplaire.size()-1);//Affichage
				vectExemplaire.removeElement(vectExemplaire.lastElement());//Vecteur de l'emprunt en cours
				System.out.println("Pkg:panneaux-Class:EmpruntRetour\nvectExemplaire.size() = " + vectExemplaire.size());
			}
			if(e.getSource() == btnEmpruntEnregistrerFinal) {
				lblEmprStatut.setText("");
				lblEmprStatut.setIcon(null);
				try {
					int tmpNumEmp = EmpruntManager.creerEmprunt(new Adherent(FenetrePrincipale.partieEmploye.getRechercherUnAdherent().getTempAdher().getNum_adherent()));
					for (Exemplaire ex : vectExemplaire) {//Vecteur de l'emprunt en cours parcouru et ajouter dans LigEmprunt
						EmpruntManager.setEmprunt(tmpNumEmp, ex);
					}
					lblEmprStatut.setForeground(Color.GREEN);
					lblEmprStatut.setText("Enregistrement reussi.");
				} catch (ClassNotFoundException | SQLException e1) {
					System.out.println("Pkg:panneaux-Class:EmpruntRetour-Tag:2");
					e1.printStackTrace();
				}
			}
			if(e.getSource() == btnRetAjouter) {
			}
			if(e.getSource() == btnRetSupprimer) {
			}
			if(e.getSource() == btnValiderRetour) {
			}
		}
	}

	//Methodes
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
			System.out.println("Pkg:panneaux-Class:EmpruntRetour\nget Nb Emprunt Max Adherent : " + tmp);
			if(tmp == 0) {
				lblEmprStatut.setForeground(Color.RED);
				lblEmprStatut.setIcon(new ImageIcon(EmpruntRetour.class.getResource("/javax/swing/plaf/metal/icons/Warn.gif")));
				lblEmprStatut.setText("Nombre d'exemplaire emprunte maximum atteint");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Pkg:panneaux-Class:EmpruntRetour-Tag:6");
			e.printStackTrace();
		}
		return tmp;
	}
	/**
	 * Ajoute l'exemplaire selectionné au vecteur temporaire d'emprunt,
	 * apres un click sur le bouton ajouter du panEmprunt.
	 */
	private void btnEmprAjouter_Click() {
		boolean nouveau = true;//Booleen pour verifier si l'exemplaire n'a pas ete ajoute plusieurs fois dans le meme emprunt
		Exemplaire ex = FenetrePrincipale.partieEmploye.getRechercherUnLivre().getTempRecherExemp();
		for (Exemplaire exTemp : vectExemplaire) {
			if(exTemp.equals(ex)) nouveau = false;
		}
		try {
			if (nouveau && LigEmpruntManager.getDisp(ex).equals("Oui")) {
				vectExemplaire.addElement(ex);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Initialisation du panel
	private void initControle() {
		try {
			nbEmpruntMax = ParametreManager.getParametre(new Parametre("empruntmax")).getValeur();
			System.out.println("Pkg:panneaux-Class:EmpruntRetour\nNb emprunt max : " + nbEmpruntMax);
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
		
		lblEmprStatut.setForeground(Color.RED);
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
