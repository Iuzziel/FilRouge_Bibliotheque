package fenetres;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private boolean estConnecte = false;

	// Constructeur de la fenetre
	public FenetreConnexion() {
		setPreferredSize(new Dimension(275, 400));
		setMinimumSize(new Dimension(275, 200));
		setMaximumSize(new Dimension(400, 400));
		getContentPane().setLayout(new BorderLayout(5, 5));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);

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
		btnConnexionValider.addActionListener(new appActionListener());
		panConnexionBtn.add(btnConnexionAnnuler);
		btnConnexionAnnuler.addActionListener(new appActionListener());

	}

	//Methodes
	private boolean connexion(String login, String password) {
		if(login.equals("root") && password.equals("toor")) {
			this.setVisible(false);
			estConnecte = true;
		}else{
			estConnecte = false;
			JOptionPane.showMessageDialog(this, "Mauvais identifiants");
		}
		return estConnecte;
	}

	//Actions listeners
	private class appActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnConnexionValider) {
				connexion(getTextFieldConnexionIdentifiant().getText(), String.valueOf(getPwdFieldConnexion().getPassword()));
			}
			if(e.getSource() == btnConnexionAnnuler) {
				dispose();
			}
		}

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

	public boolean isEstConnecte() {
		return estConnecte;
	}
	public void setEstConnecte(boolean estConnecte) {
		this.estConnecte = estConnecte;
	}

}
