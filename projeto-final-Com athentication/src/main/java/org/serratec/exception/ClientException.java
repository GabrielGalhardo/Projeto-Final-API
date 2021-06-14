package org.serratec.exception;

public class ClientException extends RuntimeException {

	private static final long serialVersionUID = -5158124944337512806L;
	
	public ClientException(String message) {
		super(message);
	}

}
