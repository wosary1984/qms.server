package qms.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public abstract class BaseController {
	protected static final String MEDIA_TYPE = MediaType.APPLICATION_JSON_UTF8_VALUE;
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected static String ACTION_LOGIN = "LOGIN";
	protected static String ACTION_LOGOUT = "LOGOUT";
	protected static String ACTION_ACCESS = "ACCESS";

	public BaseController() {
	}

	public enum ResultStatus {
		// 成员变量
		Error("E"), Success("S");
		private String code;

		private ResultStatus(String code) {
			this.code = code;
		}

		// get set 方法
		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

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
					// object.put("userPermissions", getUserPermissions(name));
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

	protected static void responseText(HttpServletResponse response, String content) throws IOException {
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.flushBuffer();
	}

	protected String success(String action, int code, Object object) {
		JSONObject root = new JSONObject();

		try {
			root.put("action", action);
			root.put("code", code);
			root.put("status", ResultStatus.Success.getCode());
			root.put("data", object);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return root.toString();
	}

	protected String error(String action, int code, String message) {
		JSONObject root = new JSONObject();
		try {
			root.put("action", action);
			root.put("code", code);
			root.put("status", ResultStatus.Error.getCode());
			root.put("message", message);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return root.toString();
	}
}
