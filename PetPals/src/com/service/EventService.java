package com.service;

import java.sql.SQLException;

import com.dao.PetPalsDao;
import com.daoImpl.PetPalsDaoImpl;
import com.model.AdoptionEvent;


public class EventService {
	PetPalsDao dao = new PetPalsDaoImpl();

	public int hostEvent(AdoptionEvent event)throws SQLException {
		// TODO Auto-generated method stub
		return dao.hostEvent(event);
	}

	

}
