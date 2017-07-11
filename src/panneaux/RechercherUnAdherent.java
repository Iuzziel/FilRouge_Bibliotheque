package panneaux;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import fenetres.CreerUnAdherent;

public class RechercherUnAdherent extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3793794312904965025L;

	// Donnees membre
	// TODO Penser a  changer avec getter et setter dans le code final
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldNumeroAdherent;
	private JTextField txtFieldInfoNom;
	private JTextField txtFieldInfoPrenom;
	private JTextField txtFieldInfoAdresse;
	private JTextField txtFieldInfoDateCoti;
	private JTextField txtFieldInfoCotiOk;
	private JTable tabRenvoiResultatsAdherent;
	private JTable tabAdherentLivreEmprunte;
	private JTextField txtFieldPenaliteEnCours;
	private JButton btnCreerAdherent = new JButton("Cr\u00E9er Adh\u00E9rent");
	private JButton btnEditerCetAdhrent = new JButton("Editer cet Adh\u00E9rent ?");
	private JButton btnAnnulerEdition = new JButton("Annuler");
	private JPanel panInfoAdherentPersoBtn = new JPanel();


	// Constructeur
	public RechercherUnAdherent() {
		setMinimumSize(new Dimension(200, 200));
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Rechercher un adh\u00E9rent",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JPanel panRechercheResultats = new JPanel();
		add(panRechercheResultats);
		panRechercheResultats.setLayout(new BorderLayout(5, 5));

		//***************************************Panel de la recherche d'un adherent***********************************//
		JPanel panRechercheAdherent = new JPanel();
		panRechercheResultats.add(panRechercheAdherent, BorderLayout.NORTH);
		panRechercheAdherent.setLayout(new BoxLayout(panRechercheAdherent, BoxLayout.Y_AXIS));

		// panRechercheCodeAdherent
		JPanel panRechercheNumeroAdherent = new JPanel();
		panRechercheNumeroAdherent.setBorder(new EmptyBorder(2, 2, 2, 2));
		panRechercheNumeroAdherent.setMaximumSize(new Dimension(600, 35));
		panRechercheAdherent.add(panRechercheNumeroAdherent);
		panRechercheNumeroAdherent.setLayout(new BoxLayout(panRechercheNumeroAdherent, BoxLayout.X_AXIS));
		JLabel lblNumeroAdherent = new JLabel("Num\u00E9ro adh\u00E9rent : ");
		panRechercheNumeroAdherent.add(lblNumeroAdherent);
		textFieldNumeroAdherent = new JTextField();
		textFieldNumeroAdherent.setColumns(10);
		panRechercheNumeroAdherent.add(textFieldNumeroAdherent);

		// panRechercheNom
		JPanel panRechercheNom = new JPanel();
		panRechercheNom.setBorder(new EmptyBorder(2, 2, 2, 2));
		panRechercheNom.setMaximumSize(new Dimension(600, 35));
		panRechercheAdherent.add(panRechercheNom);
		panRechercheNom.setLayout(new BoxLayout(panRechercheNom, BoxLayout.X_AXIS));
		JLabel lblNom = new JLabel("Nom : ");
		panRechercheNom.add(lblNom);
		textFieldNom = new JTextField();
		textFieldNom.setColumns(10);
		panRechercheNom.add(textFieldNom);

		// panRecherchePrenom
		JPanel panRecherchePrenom = new JPanel();
		panRecherchePrenom.setBorder(new EmptyBorder(2, 2, 2, 2));
		panRecherchePrenom.setMaximumSize(new Dimension(600, 35));
		panRechercheAdherent.add(panRecherchePrenom);
		panRecherchePrenom.setLayout(new BoxLayout(panRecherchePrenom, BoxLayout.X_AXIS));
		JLabel lblPrenom = new JLabel("Pr\u00E9nom : ");
		panRecherchePrenom.add(lblPrenom);
		textFieldPrenom = new JTextField();
		textFieldPrenom.setColumns(10);
		panRecherchePrenom.add(textFieldPrenom);

		// panRechercheBoutton
		JPanel panRechercheBoutton = new JPanel();
		panRechercheAdherent.add(panRechercheBoutton);
		JButton btnRechercherAdherent = new JButton("Rechercher");
		panRechercheBoutton.add(btnRechercherAdherent);

		JButton btnRechercherAdherentReinit = new JButton("R\u00E9initialiser");
		panRechercheBoutton.add(btnRechercherAdherentReinit);

		//******************************************************Panel des resultats**************************************//
		JPanel panResultatAdherent = new JPanel();
		panRechercheResultats.add(panResultatAdherent);
		panResultatAdherent.setLayout(new BorderLayout(5, 5));

		// Creation label, de la Jtable, association a  la JScrollPane, puis ajout dans le panel, ensuite boutons et leur panel
		// TODO Remplir la JTable
		JLabel lblRenvoiResultatsTitre = new JLabel("Adh\u00E9rent(s) correspondant(s) :");
		panResultatAdherent.add(lblRenvoiResultatsTitre, BorderLayout.NORTH);
		tabRenvoiResultatsAdherent = new JTable();
		tabRenvoiResultatsAdherent.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null},
					{null, null, null, null, null},
					{null, null, null, null, null},
					{null, null, null, null, null},
				},
				new String[] {
						"N°", "Nom", "Prenom", "Adresse", "Date de naissance"
				}
				));
		tabRenvoiResultatsAdherent.setPreferredSize(new Dimension(60, 100));

		JScrollPane srlTabRenvoiResultatsAdherent = new JScrollPane(tabRenvoiResultatsAdherent);
		panResultatAdherent.add(srlTabRenvoiResultatsAdherent, BorderLayout.CENTER);

		JPanel panResultatAdherentBtn = new JPanel();
		panResultatAdherent.add(panResultatAdherentBtn, BorderLayout.SOUTH);

		JButton btnResultatsValider = new JButton("Valider");
		panResultatAdherentBtn.add(btnResultatsValider);
		panResultatAdherentBtn.add(btnCreerAdherent);

		//*************************************Panel des infos adherent renvoye apres la recherche**********************************//
		// Renvoie de tout les label TODO Ajouter les textfields lies aux infos
		JPanel panInfoAdherent = new JPanel();
		panInfoAdherent.setMinimumSize(new Dimension(150, 100));
		panInfoAdherent.setBorder(new TitledBorder(null, "Information sur l'adh\u00E9rent", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		add(panInfoAdherent);
		panInfoAdherent.setLayout(new BorderLayout(5, 5));
		JPanel panInfoAdherentPerso = new JPanel();
		panInfoAdherent.add(panInfoAdherentPerso, BorderLayout.NORTH);
		panInfoAdherentPerso.setLayout(new BoxLayout(panInfoAdherentPerso, BoxLayout.Y_AXIS));

		// panInfoAdherentPersoNom contenant Label et txtField
		JPanel panInfoAdherentPersoNom = new JPanel();
		panInfoAdherentPerso.add(panInfoAdherentPersoNom);
		panInfoAdherentPersoNom.setLayout(new BoxLayout(panInfoAdherentPersoNom, BoxLayout.X_AXIS));
		JLabel lblRetourNom = new JLabel("Nom : ");
		panInfoAdherentPersoNom.add(lblRetourNom);
		txtFieldInfoNom = new JTextField();
		txtFieldInfoNom.setMaximumSize(new Dimension(2147483647, 20));
		txtFieldInfoNom.setColumns(10);
		panInfoAdherentPersoNom.add(txtFieldInfoNom);

		// panInfoAdherentPersoPrenom contenant Label et txtField
		JPanel panInfoAdherentPersoPrenom = new JPanel();
		panInfoAdherentPerso.add(panInfoAdherentPersoPrenom);
		panInfoAdherentPersoPrenom.setLayout(new BoxLayout(panInfoAdherentPersoPrenom, BoxLayout.X_AXIS));
		JLabel lblRetourPrenom = new JLabel("Pr\u00E9nom : ");
		panInfoAdherentPersoPrenom.add(lblRetourPrenom);
		txtFieldInfoPrenom = new JTextField();
		txtFieldInfoPrenom.setMaximumSize(new Dimension(2147483647, 20));
		txtFieldInfoPrenom.setColumns(10);
		panInfoAdherentPersoPrenom.add(txtFieldInfoPrenom);

		// panInfoAdherentPersoAdresse contenant Label et txtField
		JPanel panInfoAdherentPersoAdresse = new JPanel();
		panInfoAdherentPerso.add(panInfoAdherentPersoAdresse);
		panInfoAdherentPersoAdresse.setLayout(new BoxLayout(panInfoAdherentPersoAdresse, BoxLayout.X_AXIS));
		JLabel lblRetourAdresse = new JLabel("Adresse : ");
		panInfoAdherentPersoAdresse.add(lblRetourAdresse);
		txtFieldInfoAdresse = new JTextField();
		txtFieldInfoAdresse.setMaximumSize(new Dimension(2147483647, 20));
		txtFieldInfoAdresse.setColumns(10);
		panInfoAdherentPersoAdresse.add(txtFieldInfoAdresse);

		// panInfoAdherentPersoDerniereCoti contenant Label et txtField
		JPanel panInfoAdherentPersoDerniereCoti = new JPanel();
		panInfoAdherentPersoDerniereCoti.setMaximumSize(new Dimension(32767, 20));
		panInfoAdherentPerso.add(panInfoAdherentPersoDerniereCoti);
		panInfoAdherentPersoDerniereCoti.setLayout(new GridLayout(0, 2, 0, 0));
		JLabel lblRetourDateCotisation = new JLabel("Date de la derni\u00E8re cotisation : ");
		panInfoAdherentPersoDerniereCoti.add(lblRetourDateCotisation);
		txtFieldInfoDateCoti = new JTextField();
		txtFieldInfoDateCoti.setMaximumSize(new Dimension(2147483647, 20));
		txtFieldInfoDateCoti.setColumns(10);
		panInfoAdherentPersoDerniereCoti.add(txtFieldInfoDateCoti);

		// panInfoAdherentPersoCotiOk contenant Label et txtField
		JPanel panInfoAdherentPersoCotiOk = new JPanel();
		panInfoAdherentPersoCotiOk.setMaximumSize(new Dimension(32767, 20));
		panInfoAdherentPerso.add(panInfoAdherentPersoCotiOk);
		panInfoAdherentPersoCotiOk.setLayout(new GridLayout(0, 2, 0, 0));
		JLabel lblRetourCotisationOk = new JLabel("A jour dans sa cotisation ? : ");
		panInfoAdherentPersoCotiOk.add(lblRetourCotisationOk);
		txtFieldInfoCotiOk = new JTextField();
		txtFieldInfoCotiOk.setMaximumSize(new Dimension(2147483647, 20));
		txtFieldInfoCotiOk.setColumns(10);
		panInfoAdherentPersoCotiOk.add(txtFieldInfoCotiOk);

		// panInfoAdherentPersoPenalite contenant Label et txtField
		JPanel panInfoAdherentPersoPenalite = new JPanel();
		panInfoAdherentPersoPenalite.setMaximumSize(new Dimension(32767, 20));
		panInfoAdherentPerso.add(panInfoAdherentPersoPenalite);
		panInfoAdherentPersoPenalite.setLayout(new GridLayout(0, 2, 0, 0));
		JLabel lblPenaliteEnCours = new JLabel("Penalite en cours ? : ");
		panInfoAdherentPersoPenalite.add(lblPenaliteEnCours);
		txtFieldPenaliteEnCours = new JTextField();
		txtFieldPenaliteEnCours.setMaximumSize(new Dimension(2147483647, 20));
		txtFieldPenaliteEnCours.setColumns(10);
		panInfoAdherentPersoPenalite.add(txtFieldPenaliteEnCours);

		// panInfoAdherentPersoBtn contenant le bouton Editer
		panInfoAdherentPerso.add(panInfoAdherentPersoBtn);
		panInfoAdherentPersoBtn.setLayout(new BoxLayout(panInfoAdherentPersoBtn, BoxLayout.X_AXIS));
		panInfoAdherentPersoBtn.add(btnEditerCetAdhrent);

		// Creation de la Jtable, association a la JScrollPane, puis ajout dans le panel
		// TODO Remplir la JTable
		JPanel panInfoEmpruntAdherent = new JPanel();
		panInfoAdherent.add(panInfoEmpruntAdherent, BorderLayout.CENTER);
		panInfoEmpruntAdherent.setLayout(new BorderLayout(5, 5));
		JLabel lblLivresEmprunts = new JLabel("Livre(s) emprunt\u00E9(s) : ");
		panInfoEmpruntAdherent.add(lblLivresEmprunts, BorderLayout.NORTH);
		tabAdherentLivreEmprunte = new JTable();
		tabAdherentLivreEmprunte.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
				},
				new String[] {
						"N°", "Titre", "Auteur", "Date emprunt", "Retour prevu", "Retour"
				}
				));
		tabAdherentLivreEmprunte.setPreferredSize(new Dimension(50, 50));
		JScrollPane srlTabAdherentLivreEmprunte = new JScrollPane(tabAdherentLivreEmprunte);
		panInfoEmpruntAdherent.add(srlTabAdherentLivreEmprunte, BorderLayout.CENTER);
		
		//Abonnement aux Listeners
		btnCreerAdherent.addActionListener(new appActionListener());
		btnEditerCetAdhrent.addActionListener(new appActionListener());
		btnAnnulerEdition.addActionListener(new appActionListener());
	}

	// Listener
	public class appActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnCreerAdherent) {
				CreerUnAdherent fenetreCreerAdherent = new CreerUnAdherent();
				fenetreCreerAdherent.setLocationRelativeTo(null);
				fenetreCreerAdherent.setVisible(true);
				fenetreCreerAdherent.setAlwaysOnTop(true);;
				panInfoAdherentPersoBtn.repaint();
			}
			if(e.getSource() == btnEditerCetAdhrent) {
				panInfoAdherentPersoBtn.removeAll();
				panInfoAdherentPersoBtn.add(new JButton("Valider"));
				panInfoAdherentPersoBtn.add(btnAnnulerEdition);
				panInfoAdherentPersoBtn.repaint();
			}
			if(e.getSource() == btnAnnulerEdition) {
				panInfoAdherentPersoBtn.removeAll();
				panInfoAdherentPersoBtn.add(btnEditerCetAdhrent);
				panInfoAdherentPersoBtn.repaint();
			}
		}
	}



	// Accesseurs
	public JTextField getTextFieldNom() {
		return textFieldNom;
	}

	public void setTextFieldNom(JTextField textFieldNom) {
		this.textFieldNom = textFieldNom;
	}

	public JTextField getTextFieldPrenom() {
		return textFieldPrenom;
	}

	public void setTextFieldPrenom(JTextField textFieldPrenom) {
		this.textFieldPrenom = textFieldPrenom;
	}

	public JTextField getTextFieldNumeroAdherent() {
		return textFieldNumeroAdherent;
	}

	public void setTextFieldNumeroAdherent(JTextField textFieldNumeroAdherent) {
		this.textFieldNumeroAdherent = textFieldNumeroAdherent;
	}

	public JTextField getTxtFieldInfoNom() {
		return txtFieldInfoNom;
	}

	public void setTxtFieldInfoNom(JTextField txtFieldInfoNom) {
		this.txtFieldInfoNom = txtFieldInfoNom;
	}

	public JTextField getTxtFieldInfoPrenom() {
		return txtFieldInfoPrenom;
	}

	public void setTxtFieldInfoPrenom(JTextField txtFieldInfoPrenom) {
		this.txtFieldInfoPrenom = txtFieldInfoPrenom;
	}

	public JTextField getTxtFieldInfoAdresse() {
		return txtFieldInfoAdresse;
	}

	public void setTxtFieldInfoAdresse(JTextField txtFieldInfoAdresse) {
		this.txtFieldInfoAdresse = txtFieldInfoAdresse;
	}

	public JTextField getTxtFieldInfoDateCote() {
		return txtFieldInfoDateCoti;
	}

	public void setTxtFieldInfoDateCote(JTextField txtFieldInfoDateCote) {
		this.txtFieldInfoDateCoti = txtFieldInfoDateCote;
	}

	public JTextField getTxtFieldInfoCotiOk() {
		return txtFieldInfoCotiOk;
	}

	public void setTxtFieldInfoCotiOk(JTextField txtFieldInfoCotiOk) {
		this.txtFieldInfoCotiOk = txtFieldInfoCotiOk;
	}

	public JTable getTabRenvoiResultatsAdherent() {
		return tabRenvoiResultatsAdherent;
	}

	public void setTabRenvoiResultatsAdherent(JTable tabRenvoiResultatsAdherent) {
		this.tabRenvoiResultatsAdherent = tabRenvoiResultatsAdherent;
	}

	public JTable getTabAdherentLivreEmprunte() {
		return tabAdherentLivreEmprunte;
	}

	public void setTabAdherentLivreEmprunte(JTable tabAdherentLivreEmprunte) {
		this.tabAdherentLivreEmprunte = tabAdherentLivreEmprunte;
	}

}
