package fenetres;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

public class CreerUnAdherent extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5600373141077273433L;

	//Donnees membre
	private JTextField textFieldNumeroAdherent = new JTextField();
	private JTextField textFieldDateInscription = new JTextField();
	private JTextField textFieldNom = new JTextField();
	private JTextField textFieldPrenom = new JTextField();
	private JTextField textFieldAdresse = new JTextField();
	private JTextField textFieldDateDeNaissance = new JTextField();
	private JButton btnAnnuler = new JButton("Annuler");

	//Constructeur
	public CreerUnAdherent () {
		this.setMinimumSize(new Dimension(500, 300));
		this.setSize(550, 350);
		this.setTitle("Creation d'un nouvel adherent");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

		//***************************************Panel creation d'un adherent***********************************//
		JPanel panCreerUnAdherent = new JPanel();
		panCreerUnAdherent.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Creer un adh\u00E9rent",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		this.getContentPane().add(panCreerUnAdherent);
		panCreerUnAdherent.setLayout(new BorderLayout(5, 5));

		// panCreerUnAdherentCenter
		JPanel panCreerUnAdherentCenter = new JPanel();
		panCreerUnAdherentCenter.setLayout(new BoxLayout(panCreerUnAdherentCenter, BoxLayout.Y_AXIS));
		panCreerUnAdherent.add(panCreerUnAdherentCenter, BorderLayout.CENTER);

		// panCodeAdherent
		JPanel panCodeAdherent = new JPanel();
		panCodeAdherent.setBorder(new EmptyBorder(2, 2, 2, 2));
		panCodeAdherent.setMaximumSize(new Dimension(600, 35));
		panCodeAdherent.setLayout(new BoxLayout(panCodeAdherent, BoxLayout.X_AXIS));
		JLabel lblNumeroAdherent = new JLabel("Num\u00E9ro adh\u00E9rent : ");
		panCodeAdherent.add(lblNumeroAdherent);
		textFieldNumeroAdherent.setColumns(10);
		panCodeAdherent.add(textFieldNumeroAdherent);
		panCreerUnAdherentCenter.add(panCodeAdherent);

		// panDateInscription
		JPanel panDateInscription = new JPanel();
		panDateInscription.setBorder(new EmptyBorder(2, 2, 2, 2));
		panDateInscription.setMaximumSize(new Dimension(600, 35));
		panDateInscription.setLayout(new BoxLayout(panDateInscription, BoxLayout.X_AXIS));
		JLabel lblDateInscription = new JLabel("Date d'inscription : ");
		panDateInscription.add(lblDateInscription);
		textFieldDateInscription.setColumns(10);
		panDateInscription.add(textFieldDateInscription);
		panCreerUnAdherentCenter.add(panDateInscription);

		// panNom
		JPanel panNom = new JPanel();
		panNom.setBorder(new EmptyBorder(2, 2, 2, 2));
		panNom.setMaximumSize(new Dimension(600, 35));
		panNom.setLayout(new BoxLayout(panNom, BoxLayout.X_AXIS));
		JLabel lblNom = new JLabel("Nom : ");
		panNom.add(lblNom);
		textFieldNom.setColumns(10);
		panNom.add(textFieldNom);
		panCreerUnAdherentCenter.add(panNom);

		// panPrenom
		JPanel panPrenom = new JPanel();
		panPrenom.setBorder(new EmptyBorder(2, 2, 2, 2));
		panPrenom.setMaximumSize(new Dimension(600, 35));
		panPrenom.setLayout(new BoxLayout(panPrenom, BoxLayout.X_AXIS));
		JLabel lblPrenom = new JLabel("Prenom : ");
		panPrenom.add(lblPrenom);
		textFieldPrenom.setColumns(10);
		panPrenom.add(textFieldPrenom);
		panCreerUnAdherentCenter.add(panPrenom);

		// panAdresse textFieldAdresse
		JPanel panAdresse = new JPanel();
		panAdresse.setBorder(new EmptyBorder(2, 2, 2, 2));
		panAdresse.setMaximumSize(new Dimension(600, 35));
		panAdresse.setLayout(new BoxLayout(panAdresse, BoxLayout.X_AXIS));
		JLabel lblAdresse = new JLabel("Adresse : ");
		panAdresse.add(lblAdresse);
		textFieldAdresse.setColumns(10);
		panAdresse.add(textFieldAdresse);
		panCreerUnAdherentCenter.add(panAdresse);

		// panDateDeNaissance textFieldDateDeNaissance
		JPanel panDateDeNaissance = new JPanel();
		panDateDeNaissance.setBorder(new EmptyBorder(2, 2, 2, 2));
		panDateDeNaissance.setMaximumSize(new Dimension(600, 35));
		panDateDeNaissance.setLayout(new BoxLayout(panDateDeNaissance, BoxLayout.X_AXIS));
		JLabel lblDateDeNaissance = new JLabel("Date de naissance : ");
		panDateDeNaissance.add(lblDateDeNaissance);
		textFieldDateDeNaissance.setColumns(10);
		panDateDeNaissance.add(textFieldDateDeNaissance);
		panCreerUnAdherentCenter.add(panDateDeNaissance);


		// panCreerUnAdherentCenter
		JPanel panCreerUnAdherentSud = new JPanel();
		panCreerUnAdherent.add(panCreerUnAdherentSud, BorderLayout.SOUTH);

		// panBtnValiderPaiementCoti & creer adherent
		JPanel panBtnValiderPaiementCoti = new JPanel();
		panCreerUnAdherentSud.add(panBtnValiderPaiementCoti);
		JButton btnValiderPaiementCoti = new JButton("Valider le paiement de la cotisation");
		panBtnValiderPaiementCoti.add(btnValiderPaiementCoti);
		JButton btnCreerAdherent = new JButton("Cr\u00E9er Adh\u00E9rent");
		panBtnValiderPaiementCoti.add(btnCreerAdherent);

		// panCreerUnAdherent/pan btn Annuler
		JPanel panBtnAnnuler = new JPanel();
		panCreerUnAdherentSud.add(panBtnAnnuler);
		panBtnAnnuler.add(btnAnnuler);

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
