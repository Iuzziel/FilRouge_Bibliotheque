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
	private InformationLivre moduleInformationLivre = new InformationLivre();
	private RechercherUnLivre moduleRechercheLivre = new RechercherUnLivre();

	//Constructeur
	public PartieVisiteur() {
		this.setMinimumSize(new Dimension(800, 600));
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.setLayout(new BorderLayout(5, 5));
		this.setVisible(true);

		// Creation des modules composants ce Panel
		moduleRechercheLivre.setMaximumSize(new Dimension(500, 2147483647));
		moduleRechercheLivre.setPreferredSize(new Dimension(250, 200));
		moduleRechercheLivre.setMinimumSize(new Dimension(150, 200));
		moduleInformationLivre.setInformationLivreClient(true);
		moduleInformationLivre.repaint();
		moduleHeader = new Header("Espace visiteurs");
		this.add(moduleHeader, BorderLayout.NORTH);
		this.add(moduleRechercheLivre, BorderLayout.WEST);
		this.add(moduleInformationLivre, BorderLayout.CENTER);
	}

	public Header getModuleHeader() {
		return moduleHeader;
	}

	public InformationLivre getModuleInformationLivre() {
		return moduleInformationLivre;
	}

	public RechercherUnLivre getModuleRechercheLivre() {
		return moduleRechercheLivre;
	}

}
