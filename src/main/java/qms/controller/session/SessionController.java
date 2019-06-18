package qms.controller.session;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import qms.controller.BaseController;

@RestController
public class SessionController extends BaseController {

	final String PATH_MY_SESSION = "/my/session";
	final String PATH_MY_PRIVILEGE = "/my/privileges";

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	HttpServletRequest request;

	@Autowired
	CustomUserService userService;

	/****
	 * Get Login User Session
	 * 
	 * @return
	 * @throws JSONException
	 */

	@GetMapping(value = PATH_MY_SESSION, produces = MEDIA_TYPE)
	public String query() throws JSONException {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		logger.debug("requested sessionid={} query auth information", request.getRequestedSessionId());
		return objectResult(BaseController.ACTION_ACCESS, 200, getJSON(authentication));
	}

	/****
	 * Get Login User Privileges
	 * 
	 * @return
	 */
	@RequestMapping(path = PATH_MY_PRIVILEGE, method = RequestMethod.GET)
	public Iterable<ApplicationRefUserPrivilege> functions() {
		return userService.getLoginUserPrivileges();
	}

	public static JSONObject getJSON(Authentication authentication) {
		JSONObject object = new JSONObject();
		try {
			if (authentication != null) {
				String name = authentication.getName();
				boolean isLogged = authentication.isAuthenticated() && !"anonymousUser".equals(name);

				object.put("userName", name);
				object.put("isLogged", isLogged);
				if (isLogged) {
					//object.put("userPermissions", getUserPermissions(name));
					object.put("userPermissions", getUserPermissions(authentication));
					// object.put("userPermissions", toJsonArray(authentication.getAuthorities()));
				}
			} else {
				object.put("userName", "Guest");
				object.put("isLogged", false);
				object.put("userPermissions", new JSONArray());
				// return object;
			}

		} catch (JSONException e) {
			e.printStackTrace();

		}
		return object;

	}

	private static JSONArray getUserPermissions(Authentication authentication) {
		JSONArray userPermissions = new JSONArray();
		authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).forEach(userPermissions::put);
		return userPermissions;
	}
	
	@SuppressWarnings("unused")
	private  JSONArray getUserPermissions(String username) {
		JSONArray userPermissions = new JSONArray();
		userService.getAuthority(username).stream().map(GrantedAuthority::getAuthority).forEach(userPermissions::put);
		//authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).forEach(userPermissions::put);
		return userPermissions;
	}
}