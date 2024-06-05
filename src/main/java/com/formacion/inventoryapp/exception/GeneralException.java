package com.formacion.inventoryapp.exception;

public class GeneralException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String tipoError;

	public GeneralException(String tipoError, String message) {
		super(message);
		this.tipoError = tipoError;
	}

	public String getTipoError() {
		return tipoError;
	}
}
