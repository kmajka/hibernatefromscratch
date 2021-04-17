package myhibernate.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletePrepareStatementApp {

	static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	static final String URL = "jdbc:mysql://localhost:3306/bazatestowa2";
	static final String USERNAME = "newuser";
	static final String PASSWORD = "123";

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			String delete = "DELETE FROM car WHERE owner_id is null";
			pstmt = conn.prepareStatement(delete);

			int row = pstmt.executeUpdate();

            System.out.println("liczba wierszy skasowanych: " + row);

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
			try {
				if (pstmt != null) {
					pstmt.close();
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


