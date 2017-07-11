package panneaux;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class GestionStockResultAffich extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 629014741202551996L;

	// Constructeur
	public GestionStockResultAffich() {
		this.setLayout(new BorderLayout(5, 5));
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		// Creation du panel principal, JTable des resultats de la recherche
		// TODO Remplir la JList
		JList<String> lstGestionListeResultAffich = new JList<String>();
		JScrollPane panGestionListeResultAffich = new JScrollPane(lstGestionListeResultAffich);
		this.add(panGestionListeResultAffich, BorderLayout.CENTER);

		// Panel Statistiques
		JPanel panGestListStats = new JPanel();
		panGestListStats.setPreferredSize(new Dimension(250, 150));
		panGestListStats.setBorder(new TitledBorder(null, "Affichage des statistiques de la liste", TitledBorder.LEADING, TitledBorder.TOP));
		this.add(panGestListStats, BorderLayout.SOUTH);
	}

}
