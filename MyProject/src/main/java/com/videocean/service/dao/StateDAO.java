package com.videocean.service.dao;

import com.videocean.service.dbconnection.AbstractDAO;
import com.videocean.exception.ClipException;
import com.videocean.model.TYPE;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class StateDAO extends AbstractDAO implements IStateDAO {

	private Logger logger = Logger.getLogger(StateDAO.class.getName());

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
			String errorMessage = "Can't find a state with ID : " + stateID;
			logger.info(errorMessage);
			throw new ClipException(errorMessage, e);
		} finally {
			closeConnection(ps);
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
			String errorMessage = "Can't find a state with this name!";
			logger.info(errorMessage);
			throw new ClipException(errorMessage);
		} finally {
			closeConnection(ps);
		}

	}

}
