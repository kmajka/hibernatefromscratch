package myhibernate.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertPrepareStatementApp {

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
			
			String insert = "INSERT INTO car(car_id, brand) VALUES(14, ?)";
			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, "Mercedes");
			pstmt.execute();

			pstmt.close();

			System.out.println("end");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException ex) {
			}
			
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

