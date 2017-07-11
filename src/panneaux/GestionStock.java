package panneaux;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToolBar.Separator;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Dimension;

public class GestionStock extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3278275461451702696L;

	// Constructeur
	public GestionStock() {
		setMinimumSize(new Dimension(200, 400));
		setPreferredSize(new Dimension(250, 500));
		setLayout(new BorderLayout(5, 5));

		// Creation du panel principal
		JPanel panGestionListe = new JPanel();
		panGestionListe.setBorder(new TitledBorder(null, "Gestion des Listes", TitledBorder.LEADING, TitledBorder.TOP));
		panGestionListe.setLayout(new BoxLayout(panGestionListe, BoxLayout.Y_AXIS));
		add(panGestionListe);

		// Creation des blocs de conditions de recherche

		// panGestListeLivre
		// panGestListeLivre/OptionLivre
		JPanel panGestListeLivre = new JPanel();
		panGestionListe.add(panGestListeLivre);
		panGestListeLivre.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JRadioButton optLivre = new JRadioButton("Livre");
		panGestListeLivre.add(optLivre);
		// panGestListeLivre/CheckBox complementaire
		JCheckBox chkLivreStock = new JCheckBox("Dans le stock");
		panGestListeLivre.add(chkLivreStock);
		JCheckBox chkLivreSorti = new JCheckBox("Sorti");
		panGestListeLivre.add(chkLivreSorti);

		JLabel lblOptionnel = new JLabel("Optionnel : ");
		panGestListeLivre.add(lblOptionnel);
		JCheckBox chkSortiRegle = new JCheckBox("En regle");
		panGestListeLivre.add(chkSortiRegle);
		JCheckBox chkSortiRetard = new JCheckBox("En retard");
		panGestListeLivre.add(chkSortiRetard);

		panGestionListe.add(new Separator());

		// panGestListeAdherent
		JPanel panGestListeAdherent = new JPanel();
		panGestionListe.add(panGestListeAdherent);
		panGestListeAdherent.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JRadioButton optAdherent = new JRadioButton("Adherent");
		panGestListeAdherent.add(optAdherent);
		// panGestListeAdherent/Checkbox
		JCheckBox chkAdherentCotiOk = new JCheckBox("Cotisation en regle");
		panGestListeAdherent.add(chkAdherentCotiOk);
		JCheckBox chkAdherentCotiNok = new JCheckBox("Cotisation en retard");
		panGestListeAdherent.add(chkAdherentCotiNok);
		JCheckBox chkAdherentEnCoursEmprunt = new JCheckBox("Livre en cours d'emprunt");
		panGestListeAdherent.add(chkAdherentEnCoursEmprunt);
		JCheckBox chkAdherentEnCoursEmpruntOk = new JCheckBox("En regle");
		panGestListeAdherent.add(chkAdherentEnCoursEmpruntOk);
		JCheckBox chkAdherentEnCoursEmpruntNok = new JCheckBox("En retard");
		panGestListeAdherent.add(chkAdherentEnCoursEmpruntNok);
		JCheckBox chkAdherentPasEmprunt = new JCheckBox("Aucun prêt en cours");
		panGestListeAdherent.add(chkAdherentPasEmprunt);

		panGestionListe.add(new Separator());

		// panGestListePeriode
		JPanel panGestListePeriode = new JPanel();
		panGestionListe.add(panGestListePeriode);
		// TODO Remplir les ComboBox de date
		// panGestListePeriode/Date de reference
		JLabel lblDateDeReference = new JLabel("Date de R\u00E9f\u00E9rence : ");
		panGestListePeriode.add(lblDateDeReference);
		JComboBox<String> cboDateDeRef = new JComboBox<String>();
		panGestListePeriode.add(cboDateDeRef);
		// panGestListePeriode/Sur les...
		JLabel lblDateSurLes = new JLabel("Sur les : ");
		panGestListePeriode.add(lblDateSurLes);
		JComboBox<String> cboDateSurLes = new JComboBox<String>();
		panGestListePeriode.add(cboDateSurLes);

		panGestionListe.add(new Separator());

		// panGestListeTri
		// TODO Remplir les comboBox en fonction des conditions
		JPanel panGestListeTri = new JPanel();
		panGestionListe.add(panGestListeTri);
		JLabel lblConditionUtilisePour = new JLabel("Condition utilis\u00E9e pour le tri  : ");
		panGestListeTri.add(lblConditionUtilisePour);
		JComboBox<String> cboConditionUtilisePour = new JComboBox<String>();
		panGestListeTri.add(cboConditionUtilisePour);
		JLabel lblOrdreDuTri = new JLabel("Ordre du tri : ");
		panGestListeTri.add(lblOrdreDuTri);
		JComboBox<String> cboOrdreDuTri = new JComboBox<String>();
		panGestListeTri.add(cboOrdreDuTri);

		panGestionListe.add(new Separator());

		// panGestListeBtn
		JPanel panGestListeBtn = new JPanel();
		panGestionListe.add(panGestListeBtn);
		panGestListeBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JButton btnGestListeBtnCreer = new JButton("Cr\u00E9er la liste");
		panGestListeBtn.add(btnGestListeBtnCreer);
		JButton btnGestListeBtnImprimer = new JButton("Imprimer");
		panGestListeBtn.add(btnGestListeBtnImprimer);

		// Creation des groupes et btns Radio
		// Duo Livre / Adherent
		ButtonGroup grpLivreAdherent = new ButtonGroup();
		grpLivreAdherent.add(optAdherent);
		grpLivreAdherent.add(optLivre);

		/*
		 * Sur la maquette, +4 radio btn, mais remplace par des checkbox ButtonGroup
		 * grpSorti = new ButtonGroup(); JRadioButton optSortiEnRegle = new
		 * JRadioButton("En regle"); JRadioButton optSortiEnRetard = new
		 * JRadioButton("En retard"); ButtonGroup grpEmprunte = new ButtonGroup();
		 * JRadioButton optEmprunteEnRegle = new JRadioButton("En regle"); JRadioButton
		 * optEmprunteEnRetard = new JRadioButton("En retard");
		 */
	}
}
