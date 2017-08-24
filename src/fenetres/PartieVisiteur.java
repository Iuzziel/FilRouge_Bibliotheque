package fenetres;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import panneaux.*;

public class PartieVisiteur extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9123660548389513004L;

	//Donnees membre
	private Header moduleHeader;

	//Constructeur
	public PartieVisiteur() {
		this.setMinimumSize(new Dimension(800, 600));
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.setLayout(new BorderLayout(5, 5));
		this.setVisible(true);

		// Creation des modules composants ce Panel
		RechercherUnLivre moduleRechercheLivre = new RechercherUnLivre();
		moduleRechercheLivre.setMaximumSize(new Dimension(500, 2147483647));
		moduleRechercheLivre.setPreferredSize(new Dimension(250, 200));
		moduleRechercheLivre.setMinimumSize(new Dimension(150, 200));
		InformationLivre moduleInformationLivre = new InformationLivre();
		moduleInformationLivre.setInformationLivreClient(true);
		moduleInformationLivre.repaint();
		moduleHeader = new Header("Espace visiteurs");
		this.add(moduleHeader, BorderLayout.NORTH);
		this.add(moduleRechercheLivre, BorderLayout.WEST);
		this.add(moduleInformationLivre, BorderLayout.CENTER);

		/*
		 * TODO Mettre non editable avec les getter depuis le moduleRechercheLivre
		 * JTextField textFieldTitre; JTextField textFieldAuteur; JTextField
		 * textFieldTheme; JTextField textFieldEmplacement; JTextField textFieldISBN;
		 * JTextField textFieldISSN; JTextField textFieldNbExemplaireDispo; JTextField
		 * textFieldNbExemplaireDispoBiblio; JTextArea txtAreaComment;
		 */
	}

	public Header getModuleHeader() {
		return moduleHeader;
	}

}
