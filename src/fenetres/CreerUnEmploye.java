package fenetres;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

public class CreerUnEmploye extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5600373141077273433L;

	//Donnees membre
	private JTextField textFieldIdentifiant = new JTextField();
	private JTextField textFieldNom = new JTextField();
	private JTextField textFieldPrenom = new JTextField();
	private JTextField textFieldAdresse = new JTextField();
	private JTextField textFieldDateDeNaissance = new JTextField();
	private JButton btnAnnuler = new JButton("Annuler");
	private JComboBox<String> cboRole = new JComboBox<String>();

	//Constructeur
	public CreerUnEmploye () {
		this.setMinimumSize(new Dimension(500, 300));
		this.setSize(500, 400);
		this.setTitle("Creation d'un nouvel employe");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

		//***************************************Panel creation d'un adherent***********************************//
		JPanel panCreerUnEmploye = new JPanel();
		panCreerUnEmploye.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Creer un employe",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		this.getContentPane().add(panCreerUnEmploye);
		panCreerUnEmploye.setLayout(new BorderLayout(5, 5));

		// panCreerUnEmployeCenter
		JPanel panCreerUnEmployeCenter = new JPanel();
		panCreerUnEmployeCenter.setLayout(new BoxLayout(panCreerUnEmployeCenter, BoxLayout.Y_AXIS));
		panCreerUnEmploye.add(panCreerUnEmployeCenter, BorderLayout.CENTER);

		// panIdentifiant
		JPanel panIdentifiant = new JPanel();
		panIdentifiant.setBorder(new EmptyBorder(2, 2, 2, 2));
		panIdentifiant.setMaximumSize(new Dimension(600, 35));
		panIdentifiant.setLayout(new BoxLayout(panIdentifiant, BoxLayout.X_AXIS));
		JLabel lblIdentifiant = new JLabel("Identifiant : ");
		panIdentifiant.add(lblIdentifiant);
		textFieldIdentifiant.setColumns(10);
		panIdentifiant.add(textFieldIdentifiant);
		panCreerUnEmployeCenter.add(panIdentifiant);

		// panRole
		JPanel panRole = new JPanel();
		panRole.setBorder(new EmptyBorder(2, 2, 2, 2));
		panRole.setMaximumSize(new Dimension(600, 35));
		panRole.setLayout(new BoxLayout(panRole, BoxLayout.X_AXIS));
		JLabel lblRole = new JLabel("Role : ");
		panRole.add(lblRole);
		// TODO Utiliser la combo avec les requetes
		cboRole.addItem("Bibliothecaire");
		cboRole.addItem("Gestionnaire de fond");
		cboRole.addItem("Responsable");
		cboRole.addItem("Administrateur");
		panRole.add(cboRole);
		panCreerUnEmployeCenter.add(panRole);

		// panNom
		JPanel panNom = new JPanel();
		panNom.setBorder(new EmptyBorder(2, 2, 2, 2));
		panNom.setMaximumSize(new Dimension(600, 35));
		panNom.setLayout(new BoxLayout(panNom, BoxLayout.X_AXIS));
		JLabel lblNom = new JLabel("Nom : ");
		panNom.add(lblNom);
		textFieldNom.setColumns(10);
		panNom.add(textFieldNom);
		panCreerUnEmployeCenter.add(panNom);

		// panPrenom
		JPanel panPrenom = new JPanel();
		panPrenom.setBorder(new EmptyBorder(2, 2, 2, 2));
		panPrenom.setMaximumSize(new Dimension(600, 35));
		panPrenom.setLayout(new BoxLayout(panPrenom, BoxLayout.X_AXIS));
		JLabel lblPrenom = new JLabel("Prenom : ");
		panPrenom.add(lblPrenom);
		textFieldPrenom.setColumns(10);
		panPrenom.add(textFieldPrenom);
		panCreerUnEmployeCenter.add(panPrenom);

		// panAdresse textFieldAdresse
		JPanel panAdresse = new JPanel();
		panAdresse.setBorder(new EmptyBorder(2, 2, 2, 2));
		panAdresse.setMaximumSize(new Dimension(600, 35));
		panAdresse.setLayout(new BoxLayout(panAdresse, BoxLayout.X_AXIS));
		JLabel lblAdresse = new JLabel("Adresse : ");
		panAdresse.add(lblAdresse);
		textFieldAdresse.setColumns(10);
		panAdresse.add(textFieldAdresse);
		panCreerUnEmployeCenter.add(panAdresse);

		// panDateDeNaissance textFieldDateDeNaissance
		JPanel panDateDeNaissance = new JPanel();
		panDateDeNaissance.setBorder(new EmptyBorder(2, 2, 2, 2));
		panDateDeNaissance.setMaximumSize(new Dimension(600, 35));
		panDateDeNaissance.setLayout(new BoxLayout(panDateDeNaissance, BoxLayout.X_AXIS));
		JLabel lblDateDeNaissance = new JLabel("Date de naissance : ");
		panDateDeNaissance.add(lblDateDeNaissance);
		textFieldDateDeNaissance.setColumns(10);
		panDateDeNaissance.add(textFieldDateDeNaissance);
		panCreerUnEmployeCenter.add(panDateDeNaissance);


		// panCreerUnEmployeSud
		JPanel panCreerUnEmployeSud = new JPanel();
		panCreerUnEmploye.add(panCreerUnEmployeSud, BorderLayout.SOUTH);

		// panCreerUnEmployeSud/pan btn Annuler
		JPanel panBtnCreerAnnuler = new JPanel();
		panCreerUnEmployeSud.add(panBtnCreerAnnuler);
		JButton btnCreerAdherent = new JButton("Cr\u00E9er employ\u00E9");
		panBtnCreerAnnuler.add(btnCreerAdherent);
		panBtnCreerAnnuler.add(btnAnnuler);

		//Abonnement aux Listeners
		btnAnnuler.addActionListener(new appActionListener());

	}


	// Listener
	public class appActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnAnnuler) {
				dispose();
			}

		}
	}
}
