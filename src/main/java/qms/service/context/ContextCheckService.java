package qms.service.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

}
