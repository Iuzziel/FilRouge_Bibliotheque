package panneaux;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import dao.Adherent;
import dao.AdherentManager;
import dao.AmandeManager;
import dao.Emprunt;
import dao.EmpruntManager;
import dao.Exemplaire;
import dao.ExemplaireManager;
import dao.LigEmpruntManager;
import dao.Parametre;
import dao.ParametreManager;
import fenetres.CreerUnAdherent;
import fenetres.FenetreRegularisation;

public class RechercherUnAdherent extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3793794312904965025L;

	// Donnees membre
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldNumeroAdherent;
	private JTextField txtFieldInfoNom;
	private JTextField txtFieldInfoPrenom;
	private JTextField txtFieldInfoAdresse;
	private JTextField txtFieldInfoDateCoti;
	private JTextField txtFieldInfoCotiOk;
	private JButton btnRechercherAdherent = new JButton("Rechercher");
	private JButton btnRechercherAdherentReinit = new JButton("R\u00E9initialiser");
	private String [] colResultRechAdh = {"NUM_ADHERENT", "ADHERNOM", "ADHERPRENOM", "ADHERADRESSE", "ADHERDATENAISS", "ADHERDATECOTI"};
	private DefaultTableModel lisDatResultRechAdh = new DefaultTableModel(colResultRechAdh, 0);
	private JTable tabRenvoiResultatsAdherent = new JTable(lisDatResultRechAdh);
	private String [] colAdhLivEmp = {"NUM_EXEMP", "TITRE", "AUTEUR", "DATE_EMP", "RET_PREV", "AMANDE"};
	private DefaultTableModel lisDatAdhLivEmp = new DefaultTableModel(colAdhLivEmp, 0);
	private JTable tabAdherentLivreEmprunte = new JTable(lisDatAdhLivEmp);
	private JTextField txtFieldPenaliteEnCours;
	private JButton btnCreerAdherent = new JButton("Cr\u00E9er Adh\u00E9rent");
	private JButton btnRegulariser = new JButton("Regulariser ?");
	private JPanel panInfoAdherentPersoBtn = new JPanel();
	private JLabel lblRechercheStatus = new JLabel("");
	private Adherent tempAdher;
	private Vector<Adherent> vtempAdher = new Vector<Adherent>();
	private boolean cotisationOk = false;
	private boolean penaliteOk = false;
	private Vector<Emprunt> vEmprPenalite = new Vector<Emprunt>();
	private Vector<Long> vMontantPenalite = new Vector<Long>();

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	// Constructeur
	public RechercherUnAdherent() {
		setMinimumSize(new Dimension(200, 200));
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Rechercher un adh\u00E9rent",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		initControle();
	}

	// Listener
	class AppKeyListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			lblRechercheStatus.setText("");
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// Auto-generated method stub
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// Auto-generated method stub
		}
	}

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
			if(e.getSource() == btnRegulariser) {
				btnRegulariser_click();
			}
			if(e.getSource() == textFieldNumeroAdherent) {
				textFieldNom.setText("");
				textFieldPrenom.setText("");
				//textFieldNumeroAdherent_click();
				btnRechercherAdherent_click();
			}
			if(e.getSource() == textFieldNom) {
				textFieldNumeroAdherent.setText("");
				btnRechercherAdherent_click();
			}
			if(e.getSource() == textFieldPrenom) {
				textFieldNumeroAdherent.setText("");
				btnRechercherAdherent_click();
			}
			if(e.getSource() == btnRechercherAdherent) {
				btnRechercherAdherent_click();
			}
			if(e.getSource() == btnRechercherAdherentReinit) {
				btnRechercherAdherentReinit_Click();
			}
		}
	}

	//Méthodes
	private void btnRechercherAdherentReinit_Click() {
		textFieldNumeroAdherent.setText("");
		textFieldNom.setText("");
		textFieldPrenom.setText("");
		btnRechercherAdherentReinit_Click();
	}

	/**
	 * Rechercher un adherent en fonctions des champs renseignes.
	 */
	private void btnRechercherAdherent_click() {
		try {
			lisDatResultRechAdh.setRowCount(0);
			lisDatAdhLivEmp.setRowCount(0);
			if(!textFieldNumeroAdherent.getText().equals("")){
				tempAdher = AdherentManager.getAdherFromNum(new Adherent(Integer.valueOf(textFieldNumeroAdherent.getText())));
				if (tempAdher != null) {
					lisDatResultRechAdh.addRow(tempAdher.toVector());
					for (Integer temp : EmpruntManager.getEmpruntAdhe(tempAdher.getNum_adherent())) {
						lisDatAdhLivEmp.addRow(ExemplaireManager.getExemplaire(new Exemplaire(temp)).toAdherEmpVector());
					}
					affichageInfo();
				} else {
					lblRechercheStatus.setText("Pas d'adherent avec ce numero");
					System.out.println("Pkg:panneaux-Class:RechercherUnAdherent-\nPas d'adherent avec ce numero");
				}
			}else if(!textFieldNom.getText().equals("") || !textFieldPrenom.getText().equals("")){
				vtempAdher = AdherentManager.getAllAdherent(new Adherent(textFieldNom.getText().toUpperCase(), textFieldPrenom.getText().toLowerCase()));
				for (Adherent adherent : vtempAdher) {
					lisDatResultRechAdh.addRow(adherent.toVector());
				}
				if(lisDatResultRechAdh.getRowCount() > 0) {
					tempAdher = AdherentManager.getAdherFromNum(new Adherent(Integer.valueOf((String) lisDatResultRechAdh.getValueAt(0, 0))));
					for (Integer temp : EmpruntManager.getEmpruntAdhe(tempAdher.getNum_adherent())) {
						lisDatAdhLivEmp.addRow(ExemplaireManager.getExemplaire(new Exemplaire(temp)).toAdherEmpVector());
					}
					affichageInfo();
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Pkg:panneaux-Class:RechercherUnAdherent-Tag:1");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Pkg:panneaux-Class:RechercherUnAdherent-Tag:2");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			lblRechercheStatus.setText("Ce n'est pas un numero adherent valide");
			System.out.println("Pkg:panneaux-Class:RechercherUnAdherent-\nCe n'est pas un numero adherent valide");
		}		
	}

	//Remplissage de la partie info en fonction de la listData
	private void affichageInfo() {
		txtFieldInfoNom.setText("");
		txtFieldInfoPrenom.setText("");
		txtFieldInfoDateCoti.setText("");
		txtFieldInfoAdresse.setText("");

		txtFieldInfoNom.setText(String.valueOf(lisDatResultRechAdh.getValueAt(0, 1)));
		txtFieldInfoPrenom.setText(String.valueOf(lisDatResultRechAdh.getValueAt(0, 2)));
		txtFieldInfoAdresse.setText(String.valueOf(lisDatResultRechAdh.getValueAt(0, 3)));
		txtFieldInfoDateCoti.setText(String.valueOf(lisDatResultRechAdh.getValueAt(0, 5)));
		verifCoti();
		verifPenalite();
	}

	private void verifPenalite() {
		txtFieldPenaliteEnCours.setText("");
		txtFieldPenaliteEnCours.setBackground(Color.GRAY);
		vMontantPenalite.setSize(0);
		vEmprPenalite.setSize(0);
		long temp = 0l;
		try {
			Vector<Emprunt> vEmpr = EmpruntManager.getEmprAdher(tempAdher);
			for (Emprunt emprunt : vEmpr) {
				if (emprunt.getEmp_date_ret() == null) {
					Date today = new Date();                   
					Calendar c = Calendar.getInstance();
					c.setTime(emprunt.getEmp_date_emp());
					c.add(Calendar.DAY_OF_YEAR, ParametreManager.getParametre(new Parametre("nbjouremprunt")).getValeur());
					Date tempDate = c.getTime();
					if(today.compareTo(tempDate) <= 0){
						penaliteOk = true;
						txtFieldPenaliteEnCours.setText("Pas de retard");	
						txtFieldPenaliteEnCours.setBackground(Color.GREEN);
					}else if(!AmandeManager.getIfAmandEmpPayee(emprunt.getNum_emprunt())){
						temp = ((today.getTime() - c.getTimeInMillis()) / 1000 / 60 / 60 / 24) 
								* LigEmpruntManager.getNbExempInEmp(emprunt.getNum_emprunt()) 
								* ParametreManager.getParametre(new Parametre("pxamande")).getValeur();
						penaliteOk = false;
						vMontantPenalite.add(temp);
						vEmprPenalite.add(emprunt);
						txtFieldPenaliteEnCours.setText("Montant : " + (temp) + " Euros");	
						txtFieldPenaliteEnCours.setBackground(Color.RED);
					}else{
						txtFieldPenaliteEnCours.setText("Amande payee, procedez au retour de l'emprunt.");
						txtFieldPenaliteEnCours.setBackground(Color.CYAN);
					}
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Pkg:panneaux-Class:RechercherUnAdherent-Tag:7");
			e.printStackTrace();
		}
	}

	private void verifCoti() {
		txtFieldInfoCotiOk.setText("");
		txtFieldInfoCotiOk.setBackground(Color.GRAY);
		Date today = new Date();                   
		Date dateCoti = tempAdher.getAdherDateCoti();
		Calendar c = Calendar.getInstance();
		c.setTime(dateCoti);
		c.add(Calendar.YEAR, 1);
		Date tempDate = c.getTime();
		txtFieldInfoCotiOk.setText(sdf.format(tempDate));
		if(today.compareTo(tempDate) <= 0){
			cotisationOk = true;
			txtFieldInfoCotiOk.setBackground(Color.GREEN);
		}else{
			cotisationOk = false;
			txtFieldInfoCotiOk.setBackground(Color.RED);
		}
	}

	private void btnRegulariser_click() {
		@SuppressWarnings("unused")
		JFrame jframeRegul = new FenetreRegularisation();
	}



	private void initControle() {
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
		panRechercheBoutton.add(btnRechercherAdherent);

		panRechercheBoutton.add(btnRechercherAdherentReinit);

		//******************************************************Panel des resultats**************************************//
		JPanel panResultatAdherent = new JPanel();
		panRechercheResultats.add(panResultatAdherent);
		panResultatAdherent.setLayout(new BorderLayout(5, 5));

		// Creation label, de la Jtable, association a la JScrollPane, puis ajout dans le panel, ensuite boutons et leur panel
		JLabel lblRenvoiResultatsTitre = new JLabel("Adh\u00E9rent(s) correspondant(s) :");
		panResultatAdherent.add(lblRenvoiResultatsTitre, BorderLayout.NORTH);
		tabRenvoiResultatsAdherent.setPreferredSize(new Dimension(450, 400));

		JScrollPane srlTabRenvoiResultatsAdherent = new JScrollPane(tabRenvoiResultatsAdherent);
		panResultatAdherent.add(srlTabRenvoiResultatsAdherent, BorderLayout.CENTER);

		JPanel panResultatAdherentBtn = new JPanel();
		panResultatAdherent.add(panResultatAdherentBtn, BorderLayout.SOUTH);

		JButton btnResultatsValider = new JButton("Valider");
		panResultatAdherentBtn.add(btnResultatsValider);
		panResultatAdherentBtn.add(btnCreerAdherent);

		JPanel panRechercheStatus = new JPanel();
		panRechercheResultats.add(panRechercheStatus, BorderLayout.SOUTH);

		lblRechercheStatus.setForeground(Color.RED);
		panRechercheStatus.add(lblRechercheStatus);

		//*************************************Panel des infos adherent renvoye apres la recherche**********************************//
		// Renvoie de tout les label
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
		panInfoAdherentPersoNom.setBorder(new EmptyBorder(1, 1, 1, 1));
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
		panInfoAdherentPersoPrenom.setBorder(new EmptyBorder(1, 1, 1, 1));
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
		panInfoAdherentPersoAdresse.setBorder(new EmptyBorder(1, 1, 1, 1));
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
		panInfoAdherentPersoDerniereCoti.setBorder(new EmptyBorder(1, 1, 1, 1));
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
		panInfoAdherentPersoCotiOk.setBorder(new EmptyBorder(1, 1, 1, 1));
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
		panInfoAdherentPersoPenalite.setBorder(new EmptyBorder(1, 1, 1, 1));
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
		panInfoAdherentPersoBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
		panInfoAdherentPersoBtn.add(btnRegulariser);

		// Creation de la Jtable, association a la JScrollPane, puis ajout dans le panel
		JPanel panInfoEmpruntAdherent = new JPanel();
		panInfoAdherent.add(panInfoEmpruntAdherent, BorderLayout.CENTER);
		panInfoEmpruntAdherent.setLayout(new BorderLayout(5, 5));
		JLabel lblLivresEmprunts = new JLabel("Livre(s) emprunte(s) : ");
		panInfoEmpruntAdherent.add(lblLivresEmprunts, BorderLayout.NORTH);
		tabAdherentLivreEmprunte.setPreferredSize(new Dimension(450, 400));
		JScrollPane srlTabAdherentLivreEmprunte = new JScrollPane(tabAdherentLivreEmprunte);
		panInfoEmpruntAdherent.add(srlTabAdherentLivreEmprunte, BorderLayout.CENTER);

		//Abonnement aux Listeners
		btnCreerAdherent.addActionListener(new appActionListener());
		btnRegulariser.addActionListener(new appActionListener());
		textFieldNumeroAdherent.addKeyListener(new AppKeyListener());
		textFieldNumeroAdherent.addActionListener(new appActionListener());
		textFieldNom.addKeyListener(new AppKeyListener());
		textFieldNom.addActionListener(new appActionListener());
		textFieldPrenom.addKeyListener(new AppKeyListener());
		textFieldPrenom.addActionListener(new appActionListener());
		btnRechercherAdherent.addActionListener(new appActionListener());		
		btnRechercherAdherentReinit.addActionListener(new appActionListener());		
	}

	// Accesseurs
	public JTextField getTextFieldNom() {
		return textFieldNom;
	}
	public JTextField getTextFieldPrenom() {
		return textFieldPrenom;
	}
	public JTextField getTextFieldNumeroAdherent() {
		return textFieldNumeroAdherent;
	}
	public JTextField getTxtFieldInfoNom() {
		return txtFieldInfoNom;
	}
	public JTextField getTxtFieldInfoPrenom() {
		return txtFieldInfoPrenom;
	}
	public JTextField getTxtFieldInfoAdresse() {
		return txtFieldInfoAdresse;
	}
	public JTextField getTxtFieldInfoDateCote() {
		return txtFieldInfoDateCoti;
	}
	public JTextField getTxtFieldInfoCotiOk() {
		return txtFieldInfoCotiOk;
	}
	public JTable getTabRenvoiResultatsAdherent() {
		return tabRenvoiResultatsAdherent;
	}
	public JTable getTabAdherentLivreEmprunte() {
		return tabAdherentLivreEmprunte;
	}
	public Adherent getTempAdher() {
		return tempAdher;
	}
	public boolean isCotisationOk() {
		return cotisationOk;
	}
	public boolean isPenaliteOk() {
		return penaliteOk;
	}
	public Vector<Emprunt> getvEmprPenalite() {
		return vEmprPenalite;
	}
	public Vector<Long> getvMontantPenalite() {
		return vMontantPenalite;
	}
}
