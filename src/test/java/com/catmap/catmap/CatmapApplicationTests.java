package com.catmap.catmap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.catmap.dto.FacilityDTO;
import com.catmap.service.IFacilityService;

@SpringBootTest
class CatmapApplicationTests {

	@Autowired
	IFacilityService facilityService;
	List<FacilityDTO> facilities;
	List<FacilityDTO> test = null;
		
	@Test
	void contextLoads() {
	}
	//Unit test for fetch function
	@Test
	public void facilityFetchValidation() throws Exception {
		whenFetchIsMade();
		checkIfWorking();
	}


	//test fetch
	private void whenFetchIsMade() throws Exception {
		facilities = facilityService.fetch();
		
	}
	//Asserts fetch() does not return null
	private void checkIfWorking() {
		assertNotEquals(facilities, test);
		
	}

}
