package xuandien369.com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {
	
	@RequestMapping(value = "/apivippro",method = RequestMethod.GET)
	public String getString() {
	    return "Get some Foos";
	}
}
