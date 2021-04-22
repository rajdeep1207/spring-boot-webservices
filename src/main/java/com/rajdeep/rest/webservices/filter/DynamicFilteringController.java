package com.rajdeep.rest.webservices.filter;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DynamicFilteringController {
	
	@GetMapping("/dfiltering")
	public SomeBean retriveSomeBean() {
		SomeBean someBean = new SomeBean("value1","value2","value3");
		return someBean;
	}
	
	@GetMapping("/dfiltering-list")
	public List<SomeBean> retriveListOfSomeBean() {
		return List.of(new SomeBean("value1","value2","value3"),
				new SomeBean("value11","value22","value33"));
	}
}
