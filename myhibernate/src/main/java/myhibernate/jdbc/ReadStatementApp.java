package myhibernate.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadStatementApp {

	static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	static final String URL = "jdbc:mysql://localhost:3306/bazatestowa2";
	static final String USERNAME = "newuser";
	static final String PASSWORD = "123";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {

			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.createStatement();
			String sql = "SELECT car_id, brand, owner_id FROM car";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {

				int carId = rs.getInt("car_id");
				String brand = rs.getString("brand");
				int ownerId = rs.getInt("owner_id");

				System.out.println("carId: " + carId + " brand: " + brand + " ownerId: " + ownerId);
			}

			rs.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException ex) {	}
			
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

	}

}
