package panneaux;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;

public class GestionEmployesEdition extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2902920964663284927L;

	// Donnees membres
	private JTextField txtFieldNom;
	private JTextField txtFieldPrenom;
	private JTextField txtFieldAdresse;
	private JTextField txtFieldTelephone;
	private JTextField txtFieldService;
	private JTextField txtFieldRole;
	private JTextField txtFieldBibliotheque;

	// Constructeur
	public GestionEmployesEdition() {
		this.setPreferredSize(new Dimension(350, 200));
		this.setBorder(new TitledBorder(null, "Affichage des informations detaillees de l'employe selectionne", TitledBorder.LEADING, TitledBorder.TOP));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// Creation d'un panel pour les infos
		JPanel panInfo = new JPanel();
		add(panInfo);
		panInfo.setLayout(new GridLayout(0, 1, 2, 2));

		// Creation d'un panel pour le Nom + textFieldNom
		JPanel panInfoNom = new JPanel();
		panInfo.add(panInfoNom);
		panInfoNom.setLayout(new BoxLayout(panInfoNom, BoxLayout.X_AXIS));
		JLabel lblNom = new JLabel("Nom : ");
		panInfoNom.add(lblNom);
		txtFieldNom = new JTextField();
		txtFieldNom.setMaximumSize(new Dimension(2147483647, 20));
		panInfoNom.add(txtFieldNom);
		txtFieldNom.setColumns(10);

		// Creation d'un panel pour le prenom + textFieldPrenom
		JPanel panInfoPrenom = new JPanel();
		panInfo.add(panInfoPrenom);
		panInfoPrenom.setLayout(new BoxLayout(panInfoPrenom, BoxLayout.X_AXIS));
		JLabel lblPrenom = new JLabel("Prenom : ");
		panInfoPrenom.add(lblPrenom);
		txtFieldPrenom = new JTextField();
		txtFieldPrenom.setMaximumSize(new Dimension(2147483647, 20));
		panInfoPrenom.add(txtFieldPrenom);
		txtFieldPrenom.setColumns(10);

		// Creation d'un panel pour l'Adresse + textFieldAdresse
		JPanel panInfoAdresse = new JPanel();
		panInfo.add(panInfoAdresse);
		panInfoAdresse.setLayout(new BoxLayout(panInfoAdresse, BoxLayout.X_AXIS));
		JLabel lblAdresse = new JLabel("Adresse : ");
		panInfoAdresse.add(lblAdresse);
		txtFieldAdresse = new JTextField();
		txtFieldAdresse.setMaximumSize(new Dimension(2147483647, 20));
		panInfoAdresse.add(txtFieldAdresse);
		txtFieldAdresse.setColumns(10);

		// Creation d'un panel pour le numeroTel + textFieldTelephone
		JPanel panInfoTel = new JPanel();
		panInfo.add(panInfoTel);
		panInfoTel.setLayout(new BoxLayout(panInfoTel, BoxLayout.X_AXIS));
		JLabel lblTelephone = new JLabel("Telephone : ");
		panInfoTel.add(lblTelephone);
		txtFieldTelephone = new JTextField();
		txtFieldTelephone.setMaximumSize(new Dimension(2147483647, 20));
		panInfoTel.add(txtFieldTelephone);
		txtFieldTelephone.setColumns(10);

		// Creation d'un panel pour le Service + textFieldService
		JPanel panInfoService = new JPanel();
		panInfo.add(panInfoService);
		panInfoService.setLayout(new BoxLayout(panInfoService, BoxLayout.X_AXIS));
		JLabel lblService = new JLabel("Service : ");
		panInfoService.add(lblService);
		txtFieldService = new JTextField();
		txtFieldService.setMaximumSize(new Dimension(2147483647, 20));
		panInfoService.add(txtFieldService);
		txtFieldService.setColumns(10);

		// Creation d'un panel pour le Role + textFieldRole
		JPanel panInfoRole = new JPanel();
		panInfo.add(panInfoRole);
		panInfoRole.setLayout(new BoxLayout(panInfoRole, BoxLayout.X_AXIS));
		JLabel lblRoleOccupe = new JLabel("Role occupe : ");
		panInfoRole.add(lblRoleOccupe);
		txtFieldRole = new JTextField();
		txtFieldRole.setMaximumSize(new Dimension(2147483647, 20));
		panInfoRole.add(txtFieldRole);
		txtFieldRole.setColumns(10);

		// Creation d'un panel pour la bibliotheque auquel il est attribue
		JPanel panInfobibliotheque = new JPanel();
		panInfo.add(panInfobibliotheque);
		panInfobibliotheque.setLayout(new BoxLayout(panInfobibliotheque, BoxLayout.X_AXIS));
		JLabel lblRoleBibliotheque = new JLabel("Bibliotheque attribue : ");
		panInfobibliotheque.add(lblRoleBibliotheque);
		txtFieldBibliotheque = new JTextField();
		txtFieldBibliotheque.setMaximumSize(new Dimension(2147483647, 20));
		panInfobibliotheque.add(txtFieldBibliotheque);
		txtFieldBibliotheque.setColumns(10);

		// Ajout des boutons
		JPanel panBtn = new JPanel();
		JButton btnEditer = new JButton("Editer cet employe");
		JButton btnAnnuler = new JButton("Annuler");
		JButton btnSupprimer = new JButton("Supprimer cet employe");
		panBtn.setLayout(new BoxLayout(panBtn, BoxLayout.X_AXIS));
		panBtn.add(btnEditer);
		panBtn.add(btnAnnuler);
		panBtn.add(btnSupprimer);
		this.add(panBtn);
	}

	// Accesseurs
	public JTextField getTxtFieldNom() {
		return txtFieldNom;
	}

	public void setTxtFieldNom(JTextField txtFieldNom) {
		this.txtFieldNom = txtFieldNom;
	}

	public JTextField getTxtFieldPrenom() {
		return txtFieldPrenom;
	}

	public void setTxtFieldPrenom(JTextField txtFieldPrenom) {
		this.txtFieldPrenom = txtFieldPrenom;
	}

	public JTextField getTxtFieldAdresse() {
		return txtFieldAdresse;
	}

	public void setTxtFieldAdresse(JTextField txtFieldAdresse) {
		this.txtFieldAdresse = txtFieldAdresse;
	}

	public JTextField getTxtFieldTelephone() {
		return txtFieldTelephone;
	}

	public void setTxtFieldTelephone(JTextField txtFieldTelephone) {
		this.txtFieldTelephone = txtFieldTelephone;
	}

	public JTextField getTxtFieldService() {
		return txtFieldService;
	}

	public void setTxtFieldService(JTextField txtFieldService) {
		this.txtFieldService = txtFieldService;
	}

	public JTextField getTxtFieldRole() {
		return txtFieldRole;
	}

	public void setTxtFieldRole(JTextField txtFieldRole) {
		this.txtFieldRole = txtFieldRole;
	}

}
