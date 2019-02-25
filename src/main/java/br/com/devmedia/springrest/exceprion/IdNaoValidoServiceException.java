package br.com.devmedia.springrest.exceprion;

public class IdNaoValidoServiceException extends RuntimeException {

	public IdNaoValidoServiceException(String message) {
		super(message);
	}
	
}
