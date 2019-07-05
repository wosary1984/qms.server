package qms.exception.def;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NullException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NullException(String message) {
		super(message);
	}
}
