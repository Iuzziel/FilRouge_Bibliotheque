package panneaux;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import dao.*;
import fenetres.FenetreConnexion;
import fenetres.FenetrePrincipale;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class RechercherUnLivre extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3793794312904965025L;

	// Donnees membre
	private JTextField textFieldTitre;
	private JTextField textFieldAuteur;
	private JTextField textFieldTheme;
	private JTextField textFieldExemplaire;
	private String [] cols = {"NUM_EXEMPLAIRE", "TITRE", "AUTEUR", "BIBLIOTHEQUE", "ETAT"};
	private DefaultTableModel listData = new DefaultTableModel(cols, 0);
	private JTable tabRenvoiResultatsLivre = new JTable(listData);
	private JScrollPane srlTabRenvoiResultatsLivre;
	private JPanel panRechercheBoutton = new JPanel();
	private JLabel lblRechercheStatus = new JLabel("");
	private Exemplaire tempRecherExemp;

	// Constructeur
	public RechercherUnLivre() {
		setBorder(new TitledBorder(null, "Rechercher un ouvrage", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		initControle();
	}

	// Listener
	class AppKeyListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getSource() == textFieldExemplaire){
				textFieldTitre.setText("");
				textFieldAuteur.setText("");
				textFieldTheme.setText("");	
				lblRechercheStatus.setText("");
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// Auto-generated method stub
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// Auto-generated method stub
		}
	}

	public class appActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == textFieldExemplaire){
				textFieldExemplaire_click();
			}
		}
	}

	// **********************************Methodes**********************************//
	public void addBoutonCreerLivre() {
		JButton ajouterLivre = new JButton("Ajouter un livre ");
		panRechercheBoutton.add(ajouterLivre);
	}
	//Recherche par numero d'exemplaire
	public void textFieldExemplaire_click() {
		try {
			listData.setRowCount(0);
			tempRecherExemp = ExemplaireManager.getExemplaire(new Exemplaire(Integer.valueOf(textFieldExemplaire.getText())));
			if (tempRecherExemp != null) {
				listData.addRow(tempRecherExemp.toInfoVector());
				if(FenetreConnexion.isEstConnecte()){
					affichageInfo(tempRecherExemp);
					FenetrePrincipale.partieEmploye.getGestEmpruntInformationLivre().refreshInfoLivre();
				}else{
					FenetrePrincipale.partieVisiteur.getModuleInformationLivre().refreshInfoLivre();
				}
			} else {
				lblRechercheStatus.setText("Pas d'exemplaire avec ce numero");
				System.out.println("Pkg:panneaux-Class:RechercherUnLivre-\nPas d'exemplaire avec ce numero");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Pkg:panneaux-Class:RechercherUnLivre-Tag:1");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Pkg:panneaux-Class:RechercherUnLivre-Tag:2");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			lblRechercheStatus.setText("Ce n'est pas un numero d'exemplaire valide");
			System.out.println("Pkg:panneaux-Class:RechercherUnLivre-\nCe n'est pas un numero d'exemplaire valide");
		}
	}

	/**
	 * Remplissage de la partie info en fonction de la listData
	 * @param temp
	 */
	private void affichageInfo(Exemplaire temp) {
		Livre liv;
		Auteur aut;
		Theme thm;
		try {
			liv = LivreManager.getLivreInfo(temp);
			textFieldTitre.setText(liv.getTitre());
			aut = AuteurManager.getAuteur(new Auteur(liv.getNum_auteur()));
			textFieldAuteur.setText(aut.toString());
			thm = ThemeManager.getTheme(new Theme(liv.getNum_theme()));
			textFieldTheme.setText(thm.getTheme());
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Pkg:panneaux-Class:RechercherUnLivre-\nMethode affichageInfo()");
			e.printStackTrace();
		}
	}
	
	private void initControle() {
		JPanel panRechercheResultats = new JPanel();
		add(panRechercheResultats);
		panRechercheResultats.setLayout(new BoxLayout(panRechercheResultats, BoxLayout.Y_AXIS));

		//********************************************Panel de la recherche de livre********************************//
		JPanel panRecherche = new JPanel();
		panRechercheResultats.add(panRecherche);
		panRecherche.setLayout(new BoxLayout(panRecherche, BoxLayout.Y_AXIS));

		// Numero exemplaire
		JPanel panRechercheExemplaire = new JPanel();
		panRechercheExemplaire.setBorder(new EmptyBorder(2, 2, 2, 2));
		panRechercheExemplaire.setMaximumSize(new Dimension(600, 35));
		panRecherche.add(panRechercheExemplaire);
		panRechercheExemplaire.setLayout(new BoxLayout(panRechercheExemplaire, BoxLayout.X_AXIS));
		JLabel lblExemplaire = new JLabel("Code exemplaire : ");
		panRechercheExemplaire.add(lblExemplaire);
		textFieldExemplaire = new JTextField();
		panRechercheExemplaire.add(textFieldExemplaire);
		textFieldExemplaire.setColumns(10);

		// panRecherche range par label associe
		// Titre
		JPanel panRechercheTitre = new JPanel();
		panRechercheTitre.setBorder(new EmptyBorder(2, 2, 2, 2));
		panRechercheTitre.setMaximumSize(new Dimension(600, 35));
		panRecherche.add(panRechercheTitre);
		panRechercheTitre.setLayout(new BoxLayout(panRechercheTitre, BoxLayout.X_AXIS));
		JLabel lblTitre = new JLabel("Titre : ");
		panRechercheTitre.add(lblTitre);
		textFieldTitre = new JTextField();
		panRechercheTitre.add(textFieldTitre);
		textFieldTitre.setColumns(10);

		// Auteur
		JPanel panRechercheAuteur = new JPanel();
		panRechercheAuteur.setBorder(new EmptyBorder(2, 2, 2, 2));
		panRechercheAuteur.setMaximumSize(new Dimension(600, 35));
		panRecherche.add(panRechercheAuteur);
		panRechercheAuteur.setLayout(new BoxLayout(panRechercheAuteur, BoxLayout.X_AXIS));
		JLabel lblAuteur = new JLabel("Auteur : ");
		panRechercheAuteur.add(lblAuteur);
		textFieldAuteur = new JTextField();
		panRechercheAuteur.add(textFieldAuteur);
		textFieldAuteur.setColumns(10);

		// Theme
		JPanel panRechercheTheme = new JPanel();
		panRechercheTheme.setBorder(new EmptyBorder(2, 2, 2, 2));
		panRechercheTheme.setMaximumSize(new Dimension(600, 35));
		panRecherche.add(panRechercheTheme);
		panRechercheTheme.setLayout(new BoxLayout(panRechercheTheme, BoxLayout.X_AXIS));
		JLabel lblTheme = new JLabel("Th\u00E8me : ");
		panRechercheTheme.add(lblTheme);
		textFieldTheme = new JTextField();
		panRechercheTheme.add(textFieldTheme);
		textFieldTheme.setColumns(10);

		// Bouton Rechercher
		panRechercheBoutton.setBorder(new EmptyBorder(2, 0, 2, 0));
		panRecherche.add(panRechercheBoutton);
		panRechercheBoutton.setLayout(new BoxLayout(panRechercheBoutton, BoxLayout.X_AXIS));
		JButton btnRechercherLivre = new JButton("Rechercher");
		panRechercheBoutton.add(btnRechercherLivre);

		//******************************************Panel des resultats*******************************************//
		JPanel panResultats = new JPanel();
		panRechercheResultats.add(panResultats);
		panResultats.setLayout(new BorderLayout(5, 5));

		JLabel lblRenvoiResultatsTitre = new JLabel("Livre(s) correspondant(s) :");
		panResultats.add(lblRenvoiResultatsTitre, BorderLayout.NORTH);

		srlTabRenvoiResultatsLivre = new JScrollPane(tabRenvoiResultatsLivre);
		srlTabRenvoiResultatsLivre.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panResultats.add(srlTabRenvoiResultatsLivre, BorderLayout.CENTER);

		// Bouton +d'info et son panel
		JPanel panResultatsBtnPlusInfos = new JPanel();
		panResultats.add(panResultatsBtnPlusInfos, BorderLayout.SOUTH);
		lblRechercheStatus.setForeground(Color.RED);
		panResultatsBtnPlusInfos.add(lblRechercheStatus);
		JButton btnResultatsPlusInfos = new JButton("+ d'infos");
		panResultatsBtnPlusInfos.add(btnResultatsPlusInfos);

		//Abonnement aux Listeners
		textFieldExemplaire.addActionListener(new appActionListener());
		textFieldExemplaire.addKeyListener(new AppKeyListener());		
	}
	
	// **********************************Accesseurs**********************************//
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

	public JTextField getTextFieldExemplaire() {
		return textFieldExemplaire;
	}

	public void setTextFieldExemplaire(JTextField textFieldExemplaire) {
		this.textFieldExemplaire = textFieldExemplaire;
	}

	public JTable getTabRenvoiResultatsLivre() {
		return tabRenvoiResultatsLivre;
	}

	public void setTabRenvoiResultatsLivre(JTable tabRenvoiResultatsLivre) {
		this.tabRenvoiResultatsLivre = tabRenvoiResultatsLivre;
	}

	public JPanel getPanRechercheBoutton() {
		return panRechercheBoutton;
	}

	public void setPanRechercheBoutton(JPanel panRechercheBoutton) {
		this.panRechercheBoutton = panRechercheBoutton;
	}
	
	public Exemplaire getTempRecherExemp() {
		return tempRecherExemp;
	}
}
