package com.example.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StateDAO extends AbstractDAO implements IStateDAO {

	@Override
	public TYPE getStateByID(int stateID) throws ClipException {
		TYPE state;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getCon().prepareStatement("SELECT * FROM state WHERE state_id = ? ;");
			ps.setInt(1, stateID);
			rs = ps.executeQuery();
			rs.next();

			String stateName = rs.getString(2);
			state = TYPE.valueOf(stateName);
			return state;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ClipException("Can't find a state with ID : " + stateID, e);
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public int getStateByName(TYPE type) throws ClipException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getCon().prepareStatement("SELECT * FROM state WHERE name like ? ;");
			ps.setString(1, type.toString());
			rs = ps.executeQuery();
			rs.next();
			int id = rs.getInt(1);
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ClipException("Can't find a state with this name!");
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
