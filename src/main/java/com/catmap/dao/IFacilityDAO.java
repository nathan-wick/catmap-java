package com.catmap.dao;

import java.util.List;

import com.catmap.dto.FacilityDTO;

public interface IFacilityDAO {

	List<FacilityDTO> fetch() throws Exception;
	
}
