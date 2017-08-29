package fenetres;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import dao.AdherentManager;
import dao.Amande;
import dao.AmandeManager;
import dao.ConnectionManager;

public class FenetreRegularisation extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4516016132539082799L;

	// Donnees membre
	private JButton btnValiderCoti = new JButton("Valider le paiment");
	private JButton btnValiderPenal = new JButton("Valider le paiment");
	private JButton btnAnnulLaValid = new JButton("Annuler la Validation");
	private JButton btnValider = new JButton("Valider");	
	private JButton btnQuitter = new JButton("Quitter");
	private final JPanel panCentralCondi = new JPanel();
	private JLabel lblInfoRegul = new JLabel("");
	private JLabel lblInfoCoti = new JLabel("");

	// Constructeur de la fenetre
	public FenetreRegularisation() {
		setPreferredSize(new Dimension(275, 400));
		setMinimumSize(new Dimension(275, 200));
		setMaximumSize(new Dimension(400, 400));
		getContentPane().setLayout(new BorderLayout(5, 5));

		initControle();
	}

	//Actions listeners
	private class appActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnValiderCoti) {
				try {
					ConnectionManager.getConnection().setAutoCommit(false);
					AdherentManager.updateAdheCoti(FenetrePrincipale.partieEmploye.getRechercherUnAdherent().getTempAdher().getNum_adherent());
					lblInfoCoti.setText("Cotisation enregistrée.");
				} catch (ClassNotFoundException | SQLException e1) {
					lblInfoCoti.setText("Erreur enregistrement.");
					System.out.println("Pkg:fenetres-Class:FenetreRegularisation-Tag:1");
					e1.printStackTrace();
				}
			}
			if(e.getSource() == btnValiderPenal) {//TODO Finir ca
				try {
					ConnectionManager.getConnection().setAutoCommit(false);
					for (int i = 0; i < FenetrePrincipale.partieEmploye.getRechercherUnAdherent().getvEmprPenalite().size(); i++) {
						int tmp = AmandeManager.insertAmande(
								new Amande(FenetrePrincipale.partieEmploye.getRechercherUnAdherent().getvEmprPenalite().elementAt(i).getNum_emprunt(), 
										Integer.valueOf(String.valueOf(FenetrePrincipale.partieEmploye.getRechercherUnAdherent().getvMontantPenalite().elementAt(i)))));
						if(tmp == 1){
							lblInfoRegul.setText("Amande archivée.");
						}else{
							lblInfoRegul.setText("Erreur");
						}
					}
				} catch (ClassNotFoundException | SQLException e1) {
					System.out.println("Pkg:fenetres-Class:FenetreRegularisation-Tag:2");
					e1.printStackTrace();
				}
			}			
			if(e.getSource() == btnAnnulLaValid) {
				try {
					ConnectionManager.getConnection().rollback();
				} catch (ClassNotFoundException | SQLException e1) {
					System.out.println("Pkg:fenetres-Class:FenetreRegularisation-Tag:3");
					e1.printStackTrace();
				}
			}			
			if(e.getSource() == btnValider) {
				try {
					ConnectionManager.getConnection().commit();
					ConnectionManager.getConnection().setAutoCommit(true);
					FenetrePrincipale.partieEmploye.getRechercherUnAdherent().revalidate();
					FenetrePrincipale.partieEmploye.getRechercherUnAdherent().repaint();				
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					System.out.println("Pkg:fenetres-Class:FenetreRegularisation-Tag:4");
					e1.printStackTrace();
				}
			}
			if(e.getSource() == btnQuitter) {
				try {
					ConnectionManager.getConnection().rollback();
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					System.out.println("Pkg:fenetres-Class:FenetreRegularisation-Tag:3");
					e1.printStackTrace();
				}
			}
		}
	}

	//Methodes	
	private void initControle() {
		JPanel panCentral = new JPanel();
		getContentPane().add(panCentral);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		panCentral.setLayout(new BorderLayout(0, 0));

		panCentral.add(panCentralCondi, BorderLayout.CENTER);
		panCentralCondi.setLayout(new GridLayout(1, 2, 2, 2));
		panCentral.add(btnAnnulLaValid, BorderLayout.SOUTH);
		JPanel panValidation = new JPanel();
		getContentPane().add(panValidation, BorderLayout.SOUTH);

		if(!FenetrePrincipale.partieEmploye.getRechercherUnAdherent().isCotisationOk()){
			JPanel panCoti = new JPanel();
			panCentralCondi.add(panCoti);
			JLabel lblCoti = new JLabel("Reglement Cotisation");
			panCoti.add(lblCoti);
			panCoti.add(btnValiderCoti);
			panCoti.add(lblInfoCoti);
			btnValiderCoti.addActionListener(new appActionListener());
		}

		if(!FenetrePrincipale.partieEmploye.getRechercherUnAdherent().isPenaliteOk()){
			JPanel panPenal = new JPanel();
			panCentralCondi.add(panPenal);
			JLabel lblPenal = new JLabel("Reglement Penalite");
			panPenal.add(lblPenal);
			panPenal.add(btnValiderPenal);
			panPenal.add(lblInfoRegul);
			btnValiderPenal.addActionListener(new appActionListener());
		}

		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.toFront();

		panValidation.add(btnValider);
		panValidation.add(btnQuitter);
		btnAnnulLaValid.addActionListener(new appActionListener());
		btnValider.addActionListener(new appActionListener());
		btnQuitter.addActionListener(new appActionListener());	
	}
}
