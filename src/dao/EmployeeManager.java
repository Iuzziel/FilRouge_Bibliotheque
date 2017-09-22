package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeManager {
	public static Employee getEmployee(Employee employee) 
			throws ClassNotFoundException, SQLException{

		Employee tmp = null;

		String sql = "SELECT * FROM employee WHERE empnom = UPPER(?)";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setString(1, employee.getEmpNom());

		ResultSet rs = stm.executeQuery();

		if(rs.next())
		{
			tmp = new Employee(rs.getInt("num_employee"),
					rs.getString("empNom"),
					rs.getString("empPrenom"),
					rs.getString("empAdresse"),
					rs.getDate("empDateNaiss"),
					rs.getInt("num_biblio"),
					rs.getString("roles"),
					rs.getString("password"));
		}

		rs.close();
		stm.close();	
		return tmp;
	}
}
