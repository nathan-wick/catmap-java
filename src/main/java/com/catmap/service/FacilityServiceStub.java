package com.catmap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.catmap.dao.IFacilityDAO;
import com.catmap.dto.FacilityDTO;

@Component
public class FacilityServiceStub implements IFacilityService {
	
	@Autowired
	IFacilityDAO facilityDAO;

	@Override
	public List<FacilityDTO> fetch() throws Exception {

		return facilityDAO.fetch();
		
	}

	@Override
	public void save(List<FacilityDTO> facilities) {

		// TODO Auto-generated method stub

	}
	
}
