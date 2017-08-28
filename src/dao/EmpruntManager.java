package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class EmpruntManager {

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

		stm.setInt(1, emprunt.getNum_emprunt());

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
	 * Retourne un Vector<Emprunt> depuis un adherent.
	 * @param adherent
	 * @return Vector<Emprunt>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Vector<Emprunt> getEmprAdher(Adherent adherent) 
			throws ClassNotFoundException, SQLException{

		Vector<Emprunt> tmp = new Vector<Emprunt>();

		String sql = "SELECT * FROM emprunt WHERE num_adherent = ?";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, adherent.getNum_adherent());

		ResultSet rs = stm.executeQuery();

		if(rs.next())
		{
			tmp.add(new Emprunt(rs.getInt("num_emprunt"),
					rs.getInt("num_adherent"),
					rs.getDate("emp_date_emp"),
					rs.getDate("emp_date_ret")));
		}

		rs.close();
		stm.close();	
		return tmp;
	}

	/**
	 * Retourne un le nombre de livre emprunte par un adherent 
	 * a partir du numero d'adherent.
	 * @param 
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int getNbEmpruntAdhe(int num_adherent) 
			throws ClassNotFoundException, SQLException{

		String sql = "SELECT COUNT(num_exemplaire) FROM ligemprunt "
				+ "JOIN emprunt ON ligemprunt.num_emprunt = emprunt.num_emprunt "
				+ "WHERE emprunt.num_adherent = ? AND emprunt.emp_date_ret IS NULL";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, num_adherent);

		ResultSet rs = stm.executeQuery();

		int tmp = 0;

		if(rs.next())
		{
			tmp = rs.getInt("COUNT(num_exemplaire)");
		}

		rs.close();
		stm.close();	
		return tmp;
	}
	
	/**
	 * Recupere le numero de l'emprunt en cours a partir d'un exemplaire.
	 * @param exemplaire
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int getEmpFromExemp(Exemplaire exemplaire) 
			throws ClassNotFoundException, SQLException{

		String sql = "SELECT EMPRUNT.num_emprunt "
				+ "FROM EMPRUNT "
				+ "JOIN LIGEMPRUNT "
				+ "ON LIGEMPRUNT.NUM_EMPRUNT = EMPRUNT.NUM_EMPRUNT "
				+ "WHERE LIGEMPRUNT.NUM_EXEMPLAIRE = ? "
				+ "AND EMPRUNT.emp_date_ret IS NULL";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, exemplaire.getNum_exemplaire());

		ResultSet rs = stm.executeQuery();

		int tmp = 0;

		if(rs.next())
		{
			tmp = rs.getInt("num_emprunt");
		}

		rs.close();
		stm.close();	
		return tmp;
	}
	
	/**
	 * Retourne un le vector des num_exemplaire emprunte par un adherent 
	 * a partir du numero d'adherent.
	 * @param 
	 * @return Vector<Integer>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Vector<Integer> getEmpruntAdhe(int num_adherent) 
			throws ClassNotFoundException, SQLException{

		Vector<Integer> tmp = new Vector<Integer>();

		String sql = "SELECT num_exemplaire FROM ligemprunt "
				+ "JOIN emprunt "
				+ "ON emprunt.num_emprunt = ligemprunt.num_emprunt "
				+ "WHERE emprunt.num_adherent = ?";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, num_adherent);

		ResultSet rs = stm.executeQuery();

		while(rs.next())
		{
			tmp.add(rs.getInt("num_exemplaire"));
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
	 * Creer les ligne d'un emprunt a partir du numero d'emprunt,
	 * retourne le num_emprunt de l'insert effectué.
	 * @param 
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int setEmprunt(int num_emprunt, Exemplaire exemplaire)
			throws ClassNotFoundException, SQLException{

		String sql = "INSERT INTO LIGEMPRUNT (num_emprunt, num_exemplaire, num_etat) "
				+ "VALUES (?, ?, ?)";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, num_emprunt);
		stm.setInt(2, exemplaire.getNum_exemplaire());
		stm.setInt(3, exemplaire.getNum_etat());

		int nbmodif = stm.executeUpdate();

		// Récupération de la valeur demandée

		stm.close();	
		return nbmodif;
	}

	/**
	 * Sert pour enregistrer les retours. 
	 * "UPDATE emprunt SET emp_date_ret = SYSDATE WHERE num_emprunt = ?" .
	 * Retourne le nombre de lignes mises a jour.
	 * @param int
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int updateEmprunt(int num_emprunt) 
			throws ClassNotFoundException, SQLException{

		String sql = "UPDATE emprunt SET emp_date_ret = SYSDATE WHERE num_emprunt = ?";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, num_emprunt);

		int res = stm.executeUpdate();

		stm.close();	
		return res;
	}
}