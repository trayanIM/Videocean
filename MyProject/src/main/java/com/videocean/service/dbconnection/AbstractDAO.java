package com.videocean.service.dbconnection;

import java.sql.*;

public abstract class AbstractDAO {
	private final Connection con = DBConnection.getInstance().getConnection();

	public Connection getCon() {
		return con;
	}

	public static void closeConnection(Statement statement, ResultSet rs) {
		try {
			if (statement != null)
				statement.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeConnection(PreparedStatement ps) {
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
