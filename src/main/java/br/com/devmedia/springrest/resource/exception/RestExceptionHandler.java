package br.com.devmedia.springrest.resource.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.devmedia.springrest.domain.DetalheErro;
import br.com.devmedia.springrest.exceprion.NaoExisteDaoException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({NaoExisteDaoException.class})
	public ResponseEntity<Object> naoExisteDaoException(RuntimeException ex, WebRequest request){
		return handleExceptionInternal(
				ex, 
				DetalheErro.builder()
				.addDetalhe("Recurso não encontrado na base de dados!")
				.addErro(ex.getMessage())
				.addStatus(HttpStatus.NOT_FOUND)
				.addHttpMethod(getHttpMethod(request))
				.addPath(getPath(request))
				.build(),
				new HttpHeaders(),
				HttpStatus.NOT_FOUND,
				request);
	}
	
	@ExceptionHandler({NullPointerException.class, IllegalArgumentException.class})
	public ResponseEntity<Object> serverException(RuntimeException ex, WebRequest request){
		return handleExceptionInternal(
				ex, 
				DetalheErro.builder()
				.addDetalhe("Uma exceção foi lançada!")
				.addErro(ex.getMessage())
				.addStatus(HttpStatus.INTERNAL_SERVER_ERROR)
				.addHttpMethod(getHttpMethod(request))
				.addPath(getPath(request))
				.build(),
				new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}

	private String getPath(WebRequest request) {
		return ((ServletWebRequest) request).getRequest().getRequestURI();
	}

	private String getHttpMethod(WebRequest request) {
		return ((ServletWebRequest) request).getRequest().getMethod();
	}

}
