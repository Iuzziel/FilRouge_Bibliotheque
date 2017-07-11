package fenetres;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

public class CreerUneBibliotheque extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5600373141077273433L;

	//Donnees membre
	private JTextField textFieldIdentifiantBibliotheque = new JTextField();
	private JTextField textFieldNom = new JTextField();
	private JTextField textFieldAdresse = new JTextField();
	private JButton btnAnnuler = new JButton("Annuler");

	//Constructeur
	public CreerUneBibliotheque () {
		this.setMinimumSize(new Dimension(500, 300));
		this.setSize(500, 400);
		this.setTitle("Creation d'une nouvelle bibliotheque");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

		//***************************************Panel creation d'un adherent***********************************//
		JPanel panCreerUneBibliotheque = new JPanel();
		panCreerUneBibliotheque.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Creer une bibliotheque",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		this.getContentPane().add(panCreerUneBibliotheque);
		panCreerUneBibliotheque.setLayout(new BorderLayout(5, 5));

		// panCreerUneBiblioCenter
		JPanel panCreerUneBiblioCenter = new JPanel();
		panCreerUneBiblioCenter.setLayout(new BoxLayout(panCreerUneBiblioCenter, BoxLayout.Y_AXIS));
		panCreerUneBibliotheque.add(panCreerUneBiblioCenter, BorderLayout.CENTER);

		// panIdentifiantBibliotheque
		JPanel panIdentifiantBibliotheque = new JPanel();
		panIdentifiantBibliotheque.setBorder(new EmptyBorder(2, 2, 2, 2));
		panIdentifiantBibliotheque.setMaximumSize(new Dimension(600, 35));
		panIdentifiantBibliotheque.setLayout(new BoxLayout(panIdentifiantBibliotheque, BoxLayout.X_AXIS));
		JLabel lblIdentifiantBiblio = new JLabel("Idnetifiant bibliotheque : ");
		panIdentifiantBibliotheque.add(lblIdentifiantBiblio);
		textFieldIdentifiantBibliotheque.setColumns(10);
		panIdentifiantBibliotheque.add(textFieldIdentifiantBibliotheque);
		panCreerUneBiblioCenter.add(panIdentifiantBibliotheque);

		// panNom
		JPanel panNom = new JPanel();
		panNom.setBorder(new EmptyBorder(2, 2, 2, 2));
		panNom.setMaximumSize(new Dimension(600, 35));
		panNom.setLayout(new BoxLayout(panNom, BoxLayout.X_AXIS));
		JLabel lblNom = new JLabel("Nom : ");
		panNom.add(lblNom);
		textFieldNom.setColumns(10);
		panNom.add(textFieldNom);
		panCreerUneBiblioCenter.add(panNom);

		// panAdresse textFieldAdresse
		JPanel panAdresse = new JPanel();
		panAdresse.setBorder(new EmptyBorder(2, 2, 2, 2));
		panAdresse.setMaximumSize(new Dimension(600, 35));
		panAdresse.setLayout(new BoxLayout(panAdresse, BoxLayout.X_AXIS));
		JLabel lblAdresse = new JLabel("Adresse : ");
		panAdresse.add(lblAdresse);
		textFieldAdresse.setColumns(10);
		panAdresse.add(textFieldAdresse);
		panCreerUneBiblioCenter.add(panAdresse);

		// panCreerUnAdherentCenter
		JPanel panCreerUneBiblioSud = new JPanel();
		panCreerUneBibliotheque.add(panCreerUneBiblioSud, BorderLayout.SOUTH);

		// panCreerUnAdherent/pan btn Annuler
		JPanel panBtnCreerAnnuler = new JPanel();
		panCreerUneBiblioSud.add(panBtnCreerAnnuler);
		JButton btnCreerBiblio = new JButton("Cr\u00E9er bibliotheque");
		panBtnCreerAnnuler.add(btnCreerBiblio);
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
