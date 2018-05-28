package com.example.model;

import java.sql.Connection;

public abstract class AbstractDAO {
	private final Connection con = DBConnection.getInstance().getConnection();

	public Connection getCon() {
		return con;
	}

}
