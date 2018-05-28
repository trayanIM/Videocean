package com.videocean.tests;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import org.junit.Test;

import com.videocean.exception.ClipException;
import com.videocean.service.dao.StateDAO;
import com.videocean.model.TYPE;

public class TestStateDAO {
	StateDAO stateDao = new StateDAO();

	@Test
	public void testGetID() throws SQLException, ClipException {
		TYPE state = stateDao.getStateByID(1);
		assertNotNull(state);
	}

	@Test
	public void testGetName() throws SQLException, ClipException {
		TYPE type = TYPE.PUBLIC;
		int id = stateDao.getStateByName(type);
		assertNotNull(id);
	}

}
