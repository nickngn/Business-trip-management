package com.projects.business_trip_management.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.projects.business_trip_management.entity.Threshold;
import com.projects.business_trip_management.repository.ThresholdRepository;

@Controller
public class ThresholdController {

	private final Logger logger = LoggerFactory.getLogger(ProposeController.class);
	private final ThresholdRepository thresholdRepository;
	
	public ThresholdController(ThresholdRepository repository) {
		this.thresholdRepository = repository;
	}
	

	@GetMapping("/thresholds")
	public ModelAndView viewThreshold(ModelMap map) {
		logger.info(" - viewThreshold() :");

		List<Threshold> thresholds = thresholdRepository.findAll();
		ModelAndView mav = new ModelAndView("thresholds");
		mav.addObject("threshold", new Threshold());
		mav.addObject("thresholds", thresholds);
		mav.addObject("basePath", "/thresholds");
		mav.addObject("title", "Hạn mức chi phí");
		return mav;
	}


	@PostMapping(value="/thresholds", 
			consumes = {MediaType.ALL_VALUE} 
	       )
	public String createOrUpdateThreshold(@Valid Threshold thresholds) {
		logger.info(" - createOrUpdateThreshold() :");

		if (thresholds != null) {
			this.thresholdRepository.save(thresholds);
		}
		
		return "redirect:/thresholds";
	}
}
