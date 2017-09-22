package fenetres;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import dao.Employee;
import dao.EmployeeManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Dimension;
import java.awt.Font;

public class FenetreConnexion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6196409418829667588L;

	// Donnees membre
	private JTextField textFieldConnexionIdentifiant;
	private JPasswordField pwdFieldConnexion;
	private JButton btnConnexionValider = new JButton("Valider");
	private	JButton btnConnexionAnnuler = new JButton("Annuler");
	private static boolean estConnecte = false;

	// Constructeur de la fenetre
	public FenetreConnexion() {
		setPreferredSize(new Dimension(275, 400));
		setMinimumSize(new Dimension(275, 200));
		setMaximumSize(new Dimension(400, 400));
		getContentPane().setLayout(new BorderLayout(5, 5));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initControle();

	}

	//Actions listeners
	private class appActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == pwdFieldConnexion) {
				connexion(getTextFieldConnexionIdentifiant().getText(), String.valueOf(getPwdFieldConnexion().getPassword()));
			}
			if(e.getSource() == btnConnexionValider) {
				connexion(getTextFieldConnexionIdentifiant().getText(), String.valueOf(getPwdFieldConnexion().getPassword()));
			}
			if(e.getSource() == btnConnexionAnnuler) {
				dispose();
			}
		}

	}

	//Methodes
	private void connexion(String login, String password) {//TODO Acceder a la Bdd pour traiter la connexion
		Employee tEmp = new Employee(login);
		try {
			tEmp = EmployeeManager.getEmployee(new Employee(login));
			if(password.equals(tEmp.getPassword())) {
				this.setVisible(false);
				setEstConnecte(true);
				FenetrePrincipale.changerPartieClient();
				FenetrePrincipale.partieEmploye.setEmployeeConnecte(tEmp);
			}else{
				setEstConnecte(false);
				JOptionPane.showMessageDialog(this, "Mauvais identifiants");
			}
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(this, "Connexion a la BDD impossible");
			System.out.println("Pkg:fenetres-Class:FenetreConnexion-Tag:1");
			e.printStackTrace();
		}
		return;
	}

	private void initControle() {
		// Titre
		JLabel lblConnexionAuxComptes = new JLabel("Connexion aux comptes Employ\u00E9s :");
		lblConnexionAuxComptes.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConnexionAuxComptes.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblConnexionAuxComptes, BorderLayout.NORTH);

		// Panel IdMdp + Boutton
		JPanel panConnexion = new JPanel();
		panConnexion.setBorder(new TitledBorder(null, "Entrez vos identifiants", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panConnexion, BorderLayout.CENTER);
		panConnexion.setLayout(new BoxLayout(panConnexion, BoxLayout.Y_AXIS));

		// Panel Id + MdP
		JPanel panConnexionIdPass = new JPanel();
		panConnexion.add(panConnexionIdPass);
		panConnexionIdPass.setLayout(new GridLayout(0, 1, 5, 5));

		// Panel Id
		JPanel panConnexionId = new JPanel();
		panConnexionIdPass.add(panConnexionId);

		JLabel lblConnexionIdentifiant = new JLabel("Identifiant :");
		panConnexionId.add(lblConnexionIdentifiant);
		textFieldConnexionIdentifiant = new JTextField();
		textFieldConnexionIdentifiant.setText("afpa");
		panConnexionId.add(textFieldConnexionIdentifiant);
		textFieldConnexionIdentifiant.setColumns(10);

		// Panel MdP
		JPanel panConnexionPass = new JPanel();
		panConnexionIdPass.add(panConnexionPass);

		JLabel lblConnexionPassword = new JLabel("Mot de Passe :");
		panConnexionPass.add(lblConnexionPassword);
		pwdFieldConnexion = new JPasswordField();
		panConnexionPass.add(pwdFieldConnexion);
		pwdFieldConnexion.setColumns(10);

		// Panel des boutton
		JPanel panConnexionBtn = new JPanel();
		panConnexionIdPass.add(panConnexionBtn);
		panConnexionBtn.add(btnConnexionValider);
		panConnexionBtn.add(btnConnexionAnnuler);

		//Abonnement aux listeners
		btnConnexionValider.addActionListener(new appActionListener());
		btnConnexionAnnuler.addActionListener(new appActionListener());	
		pwdFieldConnexion.addActionListener(new appActionListener());	

		this.setVisible(true);
	}

	// Accesseurs
	public JTextField getTextFieldConnexionIdentifiant() {
		return textFieldConnexionIdentifiant;
	}

	public void setTextFieldConnexionIdentifiant(JTextField textFieldConnexionIdentifiant) {
		this.textFieldConnexionIdentifiant = textFieldConnexionIdentifiant;
	}

	public JPasswordField getPwdFieldConnexion() {
		return pwdFieldConnexion;
	}

	public void setPwdFieldConnexion(JPasswordField pwdFieldConnexion) {
		this.pwdFieldConnexion = pwdFieldConnexion;
	}

	public static boolean isEstConnecte() {
		return estConnecte;
	}
	public static void setEstConnecte(boolean estConnecte) {
		FenetreConnexion.estConnecte = estConnecte;
	}

}
