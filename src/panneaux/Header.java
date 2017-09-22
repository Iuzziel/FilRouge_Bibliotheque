package panneaux;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import fenetres.FenetrePrincipale;
import fenetres.FenetreConnexion;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;

public class Header extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6494265312863046861L;

	// ***********************************************Donnees membre*****************************************//
	private JLabel lblHeaderSeConnecter = new JLabel("Se connecter");
	private JButton btnQuitter = new JButton("Quitter");
	private static FenetreConnexion fenetreConnexion = new FenetreConnexion();

	// ***********************************************Constructeur*****************************************//
	/**
	 * Creation du module Header. Commun a toutes les fenetres du logiciel.
	 * Recoit le titre de la fenetre en string en argument.
	 * 
	 * @param titre
	 */
	public Header(String titre) {
		setLayout(new BorderLayout(5, 5));
		JPanel panHeaderTitre = new JPanel();
		add(panHeaderTitre, BorderLayout.CENTER);

		// Titre de la page consulte
		JLabel lblHeaderTitrePage = new JLabel(titre);
		lblHeaderTitrePage.setFont(new Font("Tahoma", Font.BOLD, 16));
		panHeaderTitre.add(lblHeaderTitrePage);

		// Panneau de connexion
		JPanel panHeaderConnexion = new JPanel();
		panHeaderConnexion.setBorder(new TitledBorder(null, "Fonction avanc\u00E9e", TitledBorder.LEADING, TitledBorder.TOP));
		add(panHeaderConnexion, BorderLayout.EAST);

		//Initialisation de la fenetre de connexion
		fenetreConnexion.setVisible(false);

		// Panneau de connexion/*
		JLabel lblIconeConnexion = new JLabel("");
		lblIconeConnexion.setIcon(new ImageIcon(Header.class.getResource("/com/sun/java/swing/plaf/windows/icons/UpFolder.gif")));
		panHeaderConnexion.add(lblIconeConnexion);

		lblHeaderSeConnecter.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHeaderSeConnecter.setForeground(Color.BLUE);
		lblHeaderSeConnecter.addMouseListener(new AppMouseListener());
		panHeaderConnexion.add(lblHeaderSeConnecter);

		// Panneau quitter /*
		// bouton quitter pour quitter l'application
		JPanel panQuitter = new JPanel();
		panQuitter.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panQuitter, BorderLayout.WEST);
		panQuitter.setLayout(new BoxLayout(panQuitter, BoxLayout.X_AXIS));
		btnQuitter.addActionListener(new AppActionListener());
		panQuitter.add(btnQuitter);
	}

	// ***********************************************Listerners*****************************************//
	class AppActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Auto-generated method stub
			if (e.getSource() == btnQuitter) {
				System.exit(0);
			}
		}
	}

	class AppMouseListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			//Appel de la fenetre de connexion en fonction de l'etat de la session
			if (e.getSource() == lblHeaderSeConnecter && lblHeaderSeConnecter.getText().equals("Se connecter")) {
				fenetreConnexion = new FenetreConnexion();
				fenetreConnexion.setVisible(true);
				fenetreConnexion.setLocationRelativeTo(null);
				fenetreConnexion.setAlwaysOnTop(true);
			}
			if (e.getSource() == lblHeaderSeConnecter && lblHeaderSeConnecter.getText().equals("Se deconnecter")) {
				FenetreConnexion.setEstConnecte(false);
				FenetrePrincipale.partieEmploye.setEmployeeConnecte(null);
				FenetrePrincipale.changerPartieClient();
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// Auto-generated method stub
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// Auto-generated method stub
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// Auto-generated method stub
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// Auto-generated method stub
		}
	}

	// ***********************************************Accesseurs*****************************************//
	public JLabel getLblHeaderSeConnecter() {
		return lblHeaderSeConnecter;
	}

	public void setLblHeaderSeConnecter(JLabel lblHeaderSeConnecter) {
		this.lblHeaderSeConnecter = lblHeaderSeConnecter;
	}
	public FenetreConnexion getFenetreConnexion() {
		return fenetreConnexion;
	}
}
