package fenetres;

import javax.swing.JFrame;
import panneaux.Header;
import panneaux.InformationLivre;
import panneaux.RechercherUnLivre;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class FenetreClient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -629171735000729382L;

	// Constructeur de la fenetre
	public FenetreClient() {
		this.setMinimumSize(new Dimension(800, 600));
		this.setSize(1024, 768);
		this.setTitle("Bienvenu dans le logiciel de la bibliotheque");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.getContentPane().setLayout(new BorderLayout(5, 5));

		// Creation des modules composant cette Frame
		RechercherUnLivre moduleRechercheLivre = new RechercherUnLivre();
		moduleRechercheLivre.setMaximumSize(new Dimension(500, 2147483647));
		moduleRechercheLivre.setPreferredSize(new Dimension(250, 200));
		moduleRechercheLivre.setMinimumSize(new Dimension(150, 200));
		InformationLivre moduleInformationLivre = new InformationLivre();
		moduleInformationLivre.setInformationLivreClient(true);
		moduleInformationLivre.repaint();
		Header moduleHeader = new Header("Espace visiteurs");

		this.getContentPane().add(moduleHeader, BorderLayout.NORTH);
		this.getContentPane().add(moduleRechercheLivre, BorderLayout.WEST);
		this.getContentPane().add(moduleInformationLivre, BorderLayout.CENTER);

		/*
		 * TODO Mettre non editable avec les getter depuis le moduleRechercheLivre
		 * JTextField textFieldTitre; JTextField textFieldAuteur; JTextField
		 * textFieldTheme; JTextField textFieldEmplacement; JTextField textFieldISBN;
		 * JTextField textFieldISSN; JTextField textFieldNbExemplaireDispo; JTextField
		 * textFieldNbExemplaireDispoBiblio; JTextArea txtAreaComment;
		 */
	}

}
