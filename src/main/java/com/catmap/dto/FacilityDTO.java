package com.catmap.dto;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;

public class FacilityDTO {

	private int facilityId;
	private String name;
	private int capacity;
	private int available;
	private Date date;
	private double lat;
	private double lng;

	// Latitude and longitude values for each facility ID
	private static final Map<String, Double[]> FACILITY_LOCATIONS = new HashMap<>() {

		private static final long serialVersionUID = 1L; {

		    put("2045", new Double[]{39.129894, -84.516852});
		    put("2046", new Double[]{39.128439, -84.516616});
		    put("2047", new Double[]{39.135716, -84.515223});
		    put("2049", new Double[]{39.134303, -84.517271});
		    put("2043", new Double[]{39.129001, -84.512904});
		    put("2050", new Double[]{39.137669, -84.505159});
		    put("2052", new Double[]{39.136808, -84.507685});
		    put("2051", new Double[]{39.130841, -84.521377});
		    put("2061", new Double[]{39.1287534,-84.5159829});
		    put("2042", new Double[]{39.134615, -84.510986});
		    put("2060", new Double[]{39.134505, -84.510986});
		    put("2044", new Double[]{39.130166, -84.515964});
		    put("2048", new Double[]{39.135025, -84.515180});

		}

	};

	public FacilityDTO(int facilityId) {
		
	    this.facilityId = facilityId;
	    Double[] location = FACILITY_LOCATIONS.get(String.valueOf(facilityId));
	    if (location != null && location.length == 2) {

	        this.lat = location[0];
	        this.lng = location[1];

	    }

	}

	public int getFacilityId() {

		return facilityId;

	}

	public void setFacilityId(int facilityId) {

		this.facilityId = facilityId;

	}

	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

	public int getCapacity() {

		return capacity;

	}

	public void setCapacity(int capacity) {

		this.capacity = capacity;

	}

	public int getAvailable() {

		return available;

	}

	public void setAvailable(int available) {

		this.available = available;

	}

	public Date getDate() {

		return date;

	}

	public void setDate(Date date) {

		this.date = date;

	}

	public double getLat() {

		return lat;

	}

	public void setLat(double lat) {

		this.lat = lat;

	}

	public double getLng() {

		return lng;

	}

	public void setLng(double lng) {

		this.lng = lng;

	}

}
