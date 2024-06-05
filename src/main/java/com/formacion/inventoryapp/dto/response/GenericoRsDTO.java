package com.formacion.inventoryapp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericoRsDTO {
	
	Boolean exitoso;
	String tipoError;
	String descripcionError;
		
	@JsonProperty("exitoso")
	public Boolean getExitoso() {
		return exitoso;
	}
	
	@JsonProperty("exitoso")
	public void setExitoso(Boolean exitoso) {
		this.exitoso = exitoso;
	}

	@JsonProperty("tipoError")
	public String getTipoError() {
		return tipoError;
	}
	
	@JsonProperty("tipoError")
	public void setTipoError(String tipoError) {
		this.tipoError = tipoError;
	}
	
	@JsonProperty("descripcionError")
	public String getDescripcionError() {
		return descripcionError;
	}
	
	@JsonProperty("descripcionError")
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}
	
	/** Metodo para setear la respuesta del método */
	public GenericoRsDTO setRespuestaError (String parTipoError, String parDescripcionError) {
		
			this.setExitoso(Boolean.FALSE);
			this.setTipoError(parTipoError);
			this.setDescripcionError(parDescripcionError);
		
		return this;
	}
	
	/** Metodo para setear la respuesta del método */
	public GenericoRsDTO setRespuesta (Boolean parValidacionMetodo, String parTipoError, String parDescripcionError) {
		
			this.setExitoso(parValidacionMetodo);
			this.setTipoError(parTipoError);
			this.setDescripcionError(parDescripcionError);
		
		return this;
	}

}
