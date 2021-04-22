package com.rajdeep.rest.webservices.filter;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public SomeBean retriveSomeBean() {
		return new SomeBean("value1","value2","value3");
	}
	
	@GetMapping("/filtering-list")
	public List<SomeBean> retriveListOfSomeBean() {
		return List.of(new SomeBean("value1","value2","value3"),
				new SomeBean("value11","value22","value33"));
	}
}
