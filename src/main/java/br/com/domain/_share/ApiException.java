package br.com.domain._share;

import java.util.List;
import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException{

	private HttpStatus statusCode;
	private List<String> erros;

	public ApiException(HttpStatus statusCode, List<String> message){
		this.statusCode = statusCode;
		this.erros = message;
	}
	public ApiException(HttpStatus statusCode, String message){
		this.statusCode = statusCode;
		erros = List.of(message);
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public List<String> getErros() {
		return erros;
	}
}
