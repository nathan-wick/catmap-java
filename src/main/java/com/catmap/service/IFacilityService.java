package com.catmap.service;

import java.util.List;

import com.catmap.dto.FacilityDTO;

public interface IFacilityService {

	List<FacilityDTO> fetch() throws Exception;
	
	void save(List<FacilityDTO> facilities);

}
