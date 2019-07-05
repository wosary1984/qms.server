package qms.config.datasource;

import java.security.Principal;
import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/***
 * 用来返回CreatedBy和LastModifiedBy的值
 * 
 * @author i068981
 *
 */
@Component
public class AuditorAwareImp implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		if (ctx == null) {
			return null;
		}
		if (ctx.getAuthentication() == null) {
			return null;
		}
		if (ctx.getAuthentication().getPrincipal() == null) {
			return null;
		}
		Object principal = ctx.getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return  Optional.of(((UserDetails) principal).getUsername()); 
		}

		if (principal instanceof Principal) {

			return Optional.of(((Principal) principal).getName());
		}

		return Optional.of(String.valueOf(principal));
	}

}
