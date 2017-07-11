package panneaux;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GestionEmployesResultAffich extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 629014741202551996L;

	// Donnees membres
	private JList<String> lstGestionEmployesResultAffich;

	// Constructeur
	public GestionEmployesResultAffich() {
		this.setLayout(new BorderLayout(5, 5));
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		// Creation du panel principal, JTable des resultats de la recherche
		// TODO Remplir la JList
		lstGestionEmployesResultAffich = new JList<String>();
		JScrollPane panGestionEmployesResultAffich = new JScrollPane(lstGestionEmployesResultAffich);
		this.add(panGestionEmployesResultAffich, BorderLayout.CENTER);

		// Panel Statistiques
		GestionEmployesEdition panGestionEmployesEdition = new GestionEmployesEdition();
		this.add(panGestionEmployesEdition, BorderLayout.SOUTH);
	}

	// Accesseurs
	public JList<String> getLstGestionEmployesResultAffich() {
		return lstGestionEmployesResultAffich;
	}

	public void setLstGestionEmployesResultAffich(JList<String> lstGestionEmployesResultAffich) {
		this.lstGestionEmployesResultAffich = lstGestionEmployesResultAffich;
	}

}
