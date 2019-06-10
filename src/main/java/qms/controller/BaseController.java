package qms.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

public abstract class BaseController {
	protected static final String MEDIA_TYPE = MediaType.APPLICATION_JSON_UTF8_VALUE;
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public BaseController() {
	}

	public enum ResultStatus {
		ERROR, SUCCESS;
	}

	protected String successResult(String action, int code, Object object) {
		JSONObject root = new JSONObject();

		try {
			root.put("action", action);
			root.put("code", code);
			root.put("status", ResultStatus.SUCCESS);
			root.put("data", object);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return root.toString();
	}

	protected String errorResult(String action, int code, String message) {
		JSONObject root = new JSONObject();
		try {
			root.put("action", action);
			root.put("code", code);
			root.put("status", ResultStatus.ERROR);
			root.put("message", message);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return root.toString();
	}
}
