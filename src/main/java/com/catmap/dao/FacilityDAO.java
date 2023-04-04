package com.catmap.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.catmap.dto.FacilityDTO;

@Component
public class FacilityDAO implements IFacilityDAO {

	@Autowired
	NetworkDAO networkDAO;
	
	@Override
	public List<FacilityDTO> fetch() throws Exception {
		
		List<FacilityDTO> facilities = new ArrayList<FacilityDTO>();
		String jsonFacilitiesRaw = networkDAO.request("https://cso.uc.edu:3000/occupancy");
		JSONArray jsonFacilities = new JSONArray(jsonFacilitiesRaw);
		
		for(int i = 0; i < jsonFacilities.length(); i++) {
			
			JSONObject jsonFacility = jsonFacilities.getJSONObject(i);
			FacilityDTO facility = new FacilityDTO();
			int facilityId = jsonFacility.getInt("FacilityID");
			String name = jsonFacility.getString("Description");
			facility.setFacilityId(facilityId);
			facility.setName(name);
			
			JSONArray jsonFacilityOccupancies = jsonFacility.getJSONArray("Occupancy");
			JSONObject jsonFacilityOccupancy = jsonFacilityOccupancies.getJSONObject(0);
			int capacity = jsonFacilityOccupancy.getInt("Capacity");
			int available = jsonFacilityOccupancy.getInt("Available");
			
			if(available < 0) {
				
				available = 0;
				
			}
			
			if(available > capacity) {
				
				available = capacity;
				
			}
			
			facility.setCapacity(capacity);
			facility.setAvailable(available);
			
			facilities.add(facility);
			
		}
		
		return facilities;
		
	}
	
}
