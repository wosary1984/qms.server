package qms.service.context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import qms.repository.authorization.privilege.ApplicationPrivilege;
import qms.repository.user.ApplicationUser;
import qms.repository.user.ApplicationUserRepository;

@Service
public class ContextCheckService {

	@Autowired
	ApplicationUserRepository applicationUserRepository;

	/***
	 * get current login user
	 * 
	 * @param userRepository
	 * @return
	 */
	public ApplicationUser getLoginUser() {
		ApplicationUser loginUser = null;
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		if (authentication.getName() != null) {
			loginUser = applicationUserRepository.findByUsername(authentication.getName());
		}
		return loginUser;
	}

	public List<String> userPrivileges() {
		List<String> privileges = new ArrayList<String>();
		if (getLoginUser() != null) {
			Iterator<ApplicationPrivilege> iterator = getLoginUser().getPrivileges().iterator();
			while (iterator.hasNext()) {
				ApplicationPrivilege a = iterator.next();
				privileges.add(a.getPrivilege());
			}
		}
		return privileges;
	}

}
