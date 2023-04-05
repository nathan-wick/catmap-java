package com.catmap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.catmap.dto.FacilityDTO;
import com.catmap.service.IFacilityService;

@Controller
public class CatmapController {

	@Autowired
	private IFacilityService facilityService;

	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		List<FacilityDTO> facilities = new ArrayList<FacilityDTO>();
		try {
			facilities = facilityService.fetch();
			modelAndView.setViewName("map");
			modelAndView.addObject("facilities", facilities);
		} catch (Exception error) {
			error.printStackTrace();
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}

}
