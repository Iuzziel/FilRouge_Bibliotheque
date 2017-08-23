package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LivreManager {

	/**
	 * Retourne un emprunt a partir de son numero
	 * @param 
	 * @return Emprunt
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Emprunt getEmprunt(Emprunt emprunt) 
			throws ClassNotFoundException, SQLException{

		Emprunt tmp = null;

		String sql = "SELECT * FROM emprunt WHERE num_emprunt = ?";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, emprunt.num_emprunt);

		ResultSet rs = stm.executeQuery();

		if(rs.next())
		{
			tmp = new Emprunt(rs.getInt("num_emprunt"),
					rs.getInt("num_adherent"),
					rs.getDate("emp_date_emp"));
		}

		rs.close();
		stm.close();	
		return tmp;
	}

	/**
	 * Insert un emprunt a partir du numero d'un adherent,
	 * retourne le num_emprunt de l'insert effectué.
	 * @param 
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int creerEmprunt(Adherent adherent) 
			throws ClassNotFoundException, SQLException{

		String sql = "INSERT INTO emprunt (num_emprunt, num_adherent, emp_date_emp) "
				+ "VALUES (SEQ_EMPRUNT_num_emprunt.NEXTVAL, ?, SYSDATE)";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql, new String[] {"num_emprunt"});

		stm.setInt(1, adherent.getNum_adherent());
		@SuppressWarnings("unused")
		int nbmodif = stm.executeUpdate();
		
		// Récupération de la valeur demandée
		int temp_num_emprunt = 0;
		ResultSet seqInsert = stm.getGeneratedKeys();
		if (seqInsert.next())
		 temp_num_emprunt = seqInsert.getInt(1); // N° de la colonne du tab de String

		stm.close();	
		return temp_num_emprunt;
	}

	/**
	 * Insert un emprunt a partir du numero d'un adherent,
	 * retourne le num_emprunt de l'insert effectué.
	 * @param 
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int setLivre(Livre livre)
			throws ClassNotFoundException, SQLException{

		String sql = "INSERT INTO emprunt (num_emprunt, num_adherent, emp_date_emp) "
				+ "VALUES (SEQ_EMPRUNT_num_emprunt.NEXTVAL, ?, SYSDATE)";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql, new String[] {"num_emprunt"});

		stm.setInt(1, livre.getNum_livre());
		@SuppressWarnings("unused")
		int nbmodif = stm.executeUpdate();
		
		// Récupération de la valeur demandée
		int temp_num_emprunt = 0;
		ResultSet seqInsert = stm.getGeneratedKeys();
		if (seqInsert.next())
		 temp_num_emprunt = seqInsert.getInt(1); // N° de la colonne du tab de String

		stm.close();	
		return temp_num_emprunt;
	}
}