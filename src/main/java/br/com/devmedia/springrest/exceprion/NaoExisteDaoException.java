package br.com.devmedia.springrest.exceprion;

public class NaoExisteDaoException extends RuntimeException {

	public NaoExisteDaoException(String message) {
		super(message);
	}
	
}
