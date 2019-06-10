package qms.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestContrller extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HttpServletRequest request;

	@RequestMapping(path = "/helloworld", method = RequestMethod.GET)
	public String hello() {

		if (request.getSession() != null) {
			logger.info("session id:{}", request.getSession().getId());
		}
		return successResult("helloworld", 200, "Hello World from contrller");
	}

}
