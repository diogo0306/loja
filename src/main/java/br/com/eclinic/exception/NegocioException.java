package br.com.eclinic.exception;

@SuppressWarnings("serial")
public class NegocioException extends Exception {

	public NegocioException(String mensagem) {
		super(mensagem);
	}

}
