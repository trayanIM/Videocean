package com.videocean.service.dao;

import com.videocean.exception.ClipException;
import com.videocean.model.TYPE;

public interface IStateDAO {

	TYPE getStateByID(int stateID) throws ClipException;


}