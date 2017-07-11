package panneaux;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;

public class InformationLivre extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3793794312904965025L;

	// Donnees membre
	private JTextField textFieldTitre;
	private JTextField textFieldAuteur;
	private JTextField textFieldTheme;
	private JTextField textFieldEmplacement;
	private JTextField textFieldISBN;
	private JTextField textFieldISSN;
	private JTextField textFieldNbExemplaireDispo;
	private JTextField textFieldNbExemplaireDispoBiblio;
	private JTextField textFieldExemplaire;
	private JTextArea txtAreaComment;
	private JComboBox<String> cboEtat = new JComboBox<String>();
	private JPanel panInfoNord = new JPanel();
	private JPanel panInfoNordExemplaire = new JPanel();
	private JPanel panInfoNordComboEtat = new JPanel();

	// Constructeur
	public InformationLivre() {
		setBorder(new TitledBorder(null, "Information sur le livre", TitledBorder.LEADING, TitledBorder.TOP));
		setLayout(new BorderLayout(0, 0));

		///////////////////// Regroupement des Infos + TextField/////////////////////
		panInfoNord.setBorder(new EmptyBorder(0, 2, 5, 2));
		add(panInfoNord, BorderLayout.NORTH);
		panInfoNord.setLayout(new GridLayout(0, 2, 5, 5));

		///////////////////// Creation des Infos & TextField/////////////////////
		// Titre
		JPanel panInfoNordTitre = new JPanel();
		panInfoNord.add(panInfoNordTitre);
		panInfoNordTitre.setLayout(new GridLayout(0, 2, 0, 0));
		JLabel lblTitre = new JLabel("Titre :");
		panInfoNordTitre.add(lblTitre);
		textFieldTitre = new JTextField();
		panInfoNordTitre.add(textFieldTitre);
		textFieldTitre.setColumns(10);

		// Nombre d'exemplaire dispo
		JPanel panInfoNordNbExemplaireDispo = new JPanel();
		panInfoNord.add(panInfoNordNbExemplaireDispo);
		panInfoNordNbExemplaireDispo.setLayout(new GridLayout(0, 2, 0, 0));
		JLabel lblNbExemplaireDispo = new JLabel("Nombre d'exemplaire disponible :");
		panInfoNordNbExemplaireDispo.add(lblNbExemplaireDispo);
		textFieldNbExemplaireDispo = new JTextField();
		panInfoNordNbExemplaireDispo.add(textFieldNbExemplaireDispo);
		textFieldNbExemplaireDispo.setColumns(5);

		// Auteur
		JPanel panInfoNordAuteur = new JPanel();
		panInfoNord.add(panInfoNordAuteur);
		panInfoNordAuteur.setLayout(new GridLayout(0, 2, 0, 0));
		JLabel lblAuteur = new JLabel("Auteur :");
		panInfoNordAuteur.add(lblAuteur);
		textFieldAuteur = new JTextField();
		panInfoNordAuteur.add(textFieldAuteur);
		textFieldAuteur.setColumns(10);

		// Nombre d'exemplaire dispo dans CETTE bibliotheque
		JPanel panInfoNordNbExemplaireDispoBiblio = new JPanel();
		panInfoNord.add(panInfoNordNbExemplaireDispoBiblio);
		panInfoNordNbExemplaireDispoBiblio.setLayout(new GridLayout(0, 2, 0, 0));
		JLabel lblNbExemplaireDispoBiblio = new JLabel("Nb d'exemplaire dispo dans cette biblioth\u00E8que :");
		panInfoNordNbExemplaireDispoBiblio.add(lblNbExemplaireDispoBiblio);
		textFieldNbExemplaireDispoBiblio = new JTextField();
		panInfoNordNbExemplaireDispoBiblio.add(textFieldNbExemplaireDispoBiblio);
		textFieldNbExemplaireDispoBiblio.setColumns(5);

		// Theme
		JPanel panInfoNordTheme = new JPanel();
		panInfoNord.add(panInfoNordTheme);
		panInfoNordTheme.setLayout(new GridLayout(0, 2, 0, 0));
		JLabel lblTheme = new JLabel("Th\u00E8me :");
		panInfoNordTheme.add(lblTheme);
		textFieldTheme = new JTextField();
		panInfoNordTheme.add(textFieldTheme);
		textFieldTheme.setColumns(10);

		// Emplacement
		JPanel panInfoNordEmplacement = new JPanel();
		panInfoNord.add(panInfoNordEmplacement);
		panInfoNordEmplacement.setLayout(new GridLayout(0, 2, 0, 0));
		JLabel lblEmplacement = new JLabel("Emplacement :");
		panInfoNordEmplacement.add(lblEmplacement);
		textFieldEmplacement = new JTextField();
		panInfoNordEmplacement.add(textFieldEmplacement);
		textFieldEmplacement.setColumns(10);

		// ISBN
		JPanel panInfoNordISBN = new JPanel();
		panInfoNord.add(panInfoNordISBN);
		panInfoNordISBN.setLayout(new GridLayout(0, 2, 0, 0));
		JLabel lblIsbn = new JLabel("ISBN :");
		panInfoNordISBN.add(lblIsbn);
		textFieldISBN = new JTextField();
		panInfoNordISBN.add(textFieldISBN);
		textFieldISBN.setColumns(10);

		// ISSN
		JPanel panInfoNordISSN = new JPanel();
		panInfoNord.add(panInfoNordISSN);
		panInfoNordISSN.setLayout(new GridLayout(0, 2, 0, 0));
		JLabel lblIssn = new JLabel("ISSN :");
		panInfoNordISSN.add(lblIssn);
		textFieldISSN = new JTextField();
		panInfoNordISSN.add(textFieldISSN);
		textFieldISSN.setColumns(10);

		// Code exemplaire
		panInfoNord.add(panInfoNordExemplaire);
		panInfoNordExemplaire.setLayout(new GridLayout(0, 2, 0, 0));
		JLabel lblExemplaire = new JLabel("Exemplaire :");
		panInfoNordExemplaire.add(lblExemplaire);
		textFieldExemplaire = new JTextField();
		panInfoNordExemplaire.add(textFieldExemplaire);
		textFieldExemplaire.setColumns(10);

		// Combo etat du livre
		panInfoNord.add(panInfoNordComboEtat);
		panInfoNordComboEtat.setLayout(new GridLayout(0, 2, 0, 0));
		JLabel lblEtat = new JLabel("Etat de l'exemplaire :");
		panInfoNordComboEtat.add(lblEtat);
		//TODO Changer ca dans la version finale
		cboEtat.addItem("Excellent");
		cboEtat.addItem("Tres bon");
		cboEtat.addItem("Bon");
		cboEtat.addItem("Mauvais");
		cboEtat.addItem("Perdu");
		panInfoNordComboEtat.add(cboEtat);

		///////////////////// Zone Centre, commentaire/////////////////////
		JPanel panInfoCentre = new JPanel();
		panInfoCentre
		.setBorder(new TitledBorder(null, "Commentaire :", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panInfoCentre, BorderLayout.CENTER);
		panInfoCentre.setLayout(new BoxLayout(panInfoCentre, BoxLayout.X_AXIS));

		txtAreaComment = new JTextArea();
		txtAreaComment.setPreferredSize(new Dimension(150, 150));
		txtAreaComment.setMinimumSize(new Dimension(150, 150));
		txtAreaComment.setEditable(false);
		txtAreaComment.setLineWrap(true);
		txtAreaComment.setWrapStyleWord(true);
		panInfoCentre.add(txtAreaComment);

		///////////////////// Zone Sud, bouton quitter/////////////////////
		JPanel panInfoSud = new JPanel();
		add(panInfoSud, BorderLayout.SOUTH);

		JButton btnQuitterLaRecherche = new JButton("Quitter la Recherche");
		panInfoSud.add(btnQuitterLaRecherche);

	}

	//*********************************************Methodes*****************************************//
	public void setCommentaireEditable(boolean bool) {
		this.txtAreaComment.setEditable(bool);
		return;
	}

	public void setInformationLivreClient(boolean bool) {
		if(bool) {
			this.panInfoNord.remove(panInfoNordExemplaire);
			this.panInfoNord.remove(panInfoNordComboEtat);
		}
		return;
	}

	/**
	 * Definie l'etat, donc l'affichage du statut de la combo.
	 * 0 - Excellent 
	 * 1 - Tres bon 
	 * 2 - Bon 
	 * 3 - Mauvais 
	 * 4 - Perdu
	 * 
	 * @param i
	 */
	public void setComboEtat(int i) {
		this.cboEtat.setSelectedIndex(i);
	}

	//************************************************* Accesseurs***************************************//
	public JTextField getTextFieldTitre() {
		return textFieldTitre;
	}

	public void setTextFieldTitre(JTextField textFieldTitre) {
		this.textFieldTitre = textFieldTitre;
	}

	public JTextField getTextFieldAuteur() {
		return textFieldAuteur;
	}

	public void setTextFieldAuteur(JTextField textFieldAuteur) {
		this.textFieldAuteur = textFieldAuteur;
	}

	public JTextField getTextFieldTheme() {
		return textFieldTheme;
	}

	public void setTextFieldTheme(JTextField textFieldTheme) {
		this.textFieldTheme = textFieldTheme;
	}

	public JTextField getTextFieldEmplacement() {
		return textFieldEmplacement;
	}

	public void setTextFieldEmplacement(JTextField textFieldEmplacement) {
		this.textFieldEmplacement = textFieldEmplacement;
	}

	public JTextField getTextFieldISBN() {
		return textFieldISBN;
	}

	public void setTextFieldISBN(JTextField textFieldISBN) {
		this.textFieldISBN = textFieldISBN;
	}

	public JTextField getTextFieldISSN() {
		return textFieldISSN;
	}

	public void setTextFieldISSN(JTextField textFieldISSN) {
		this.textFieldISSN = textFieldISSN;
	}

	public JTextField getTextFieldNbExemplaireDispo() {
		return textFieldNbExemplaireDispo;
	}

	public void setTextFieldNbExemplaireDispo(JTextField textFieldNbExemplaireDispo) {
		this.textFieldNbExemplaireDispo = textFieldNbExemplaireDispo;
	}

	public JTextField getTextFieldNbExemplaireDispoBiblio() {
		return textFieldNbExemplaireDispoBiblio;
	}

	public void setTextFieldNbExemplaireDispoBiblio(JTextField textFieldNbExemplaireDispoBiblio) {
		this.textFieldNbExemplaireDispoBiblio = textFieldNbExemplaireDispoBiblio;
	}

	public JTextArea getTxtrCommentaire() {
		return txtAreaComment;
	}

	public void setTxtrCommentaire(JTextArea txtrCommentaire) {
		this.txtAreaComment = txtrCommentaire;
	}
}
