package com.dr.assignment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dr.assignment.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	private AdminService adminService;

	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@GetMapping(value = "/flushCache")
	public @ResponseBody ResponseEntity<String> search() throws Exception {
		log.info("Request received to flush the cache");

		adminService.clearCache();

		return ResponseEntity.ok("Success");
	}
}
