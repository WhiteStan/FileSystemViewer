package com.epam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Defines methods which processing simple HTTP requests
 */
@Controller
@RequestMapping(value = "/")
public class ApplicationController {

	/**
	 * Endpoint to retrieve main page
	 * 
	 * @return String with name specifying view to retrieve
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getIndexPage() {
		return "FileSystemViewer";
	}
}
