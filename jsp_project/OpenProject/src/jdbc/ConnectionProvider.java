package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

	public static Connection getConnection() {

		Connection conn=null;

		//2. DB ���� : Connection ��ü�� ���´�.
		String jdbcUrl = "jdbc:mysql://localhost:3306/open?serverTimezone=UTC";
		String user = "aia";
		String password = "aia";

		try {
			conn = DriverManager.getConnection(jdbcUrl, user, password);
		} catch (SQLException e) {
			System.out.println("Connection ��ü ���� ����!");
			e.printStackTrace();
		}

		return conn;
	}

}