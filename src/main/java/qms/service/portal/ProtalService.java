package qms.service.portal;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qms.exception.def.BadRequestException;
import qms.repository.portal.ApplicationPage;
import qms.repository.portal.ApplicationPageRepository;
import qms.service.context.ContextCheckService;

@Service
public class ProtalService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ApplicationPageRepository applicationPageRepository;

	@Autowired
	ContextCheckService context;

	public Iterable<ApplicationPage> getPages() {
		List<ApplicationPage> pages = null;

		try {
			List<String> permissions = context.userPrivileges();
			pages = applicationPageRepository.findByPrivileges(permissions);
			permissionCheck(pages, permissions);
			logger.info("user:{}, query pages information", context.getLoginUser().getUsername());
		} catch (Exception exception) {
			throw new BadRequestException("Internal Server Error");
		}
		return pages;
	}

	private List<ApplicationPage> permissionCheck(List<ApplicationPage> pages, List<String> persmissions) {

		for (Iterator<ApplicationPage> iterator = pages.iterator(); iterator.hasNext();) {
			ApplicationPage cur = iterator.next();

			// check child pages first
			permissionCheck(cur.getChilds(), persmissions);

			// if user doesn't have current page permission, remove the page from list
			if (cur.getPrivilege() != null && !persmissions.contains(cur.getPrivilege())) {
				iterator.remove();
			}
			// the user have current page permission, but no child pages, remove it from
			// list
			else if (cur.isHasChild() == true && cur.getChilds().size() == 0) {
				iterator.remove();
			}
		}
		return pages;
	}

	public ApplicationPage getPage(String pagename) {
		ApplicationPage page = null;
		try {
			List<String> privileges = context.userPrivileges();
			page = applicationPageRepository.findPage(pagename, privileges);
			if (page != null)
				page.getChilds().clear();
			logger.info("user:{}, query page {} information", context.getLoginUser().getUsername(), pagename);
		} catch (Exception exception) {
			throw new BadRequestException("Internal Server Error");
		}
		return page;
	}
}
