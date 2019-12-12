package com.amwater.mysource.resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class CustomRestController {
	
	@RequestMapping(value="/api/customrest", method=RequestMethod.GET,produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Object customrest() {
		return "Test";
	}

}
