package qms.controller.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import qms.controller.BaseController;
import qms.repository.portal.ApplicationPage;
import qms.service.portal.ProtalService;

@RestController
public class PortalController extends BaseController {

	final String PATH_PORTAL = "/api/portal";

	@Autowired
	ProtalService protalService;

	/***
	 * query all persons
	 * 
	 * @return
	 */
	@RequestMapping(path = PATH_PORTAL, method = RequestMethod.GET)
	public Iterable<ApplicationPage> getPages() {
		return protalService.getPages();
	}

}
