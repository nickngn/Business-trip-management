package com.hung.project1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hung.project1.entity.Notification;
import com.hung.project1.repository.NotificationRepository;

@Controller
public class NotificationController {
	
	@Autowired
	NotificationRepository notificationRepo;

	@GetMapping("/notifications")
	public String viewNotifications(
			@RequestParam(name="page" ,required=false, defaultValue="1") int page, 
			@RequestParam(name="size", required=false, defaultValue="10") int size, 
			ModelMap map) {

		Pageable pageRequest = new PageRequest(page-1, size);
		Page<Notification> notificationPage = notificationRepo.findAllByIdDesc(pageRequest);
		
		map.addAttribute("page", page);
		map.addAttribute("size", size);
		//
		map.addAttribute("notificationPage", notificationPage);
		map.addAttribute("title", "Các thông báo");
		map.addAttribute("basePath", "/notifications");
		
		return "notification";
	}
	
	@PutMapping("/notifications/add")
	@ResponseBody
	public ResponseEntity<Object> addNotification(@Valid @RequestBody Notification notification, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getFieldErrors(),
					HttpStatus.BAD_REQUEST);
		} 
		
		
		notificationRepo.save(notification);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
