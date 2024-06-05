package com.formacion.inventoryapp.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericoListaRsDTO<T> extends GenericoRsDTO {
	
	private List<T> listaDatos;

	@JsonProperty("listaDatos")
	public List<T> getListaDatos() {
		return listaDatos;
	}

	@JsonProperty("listaDatos")
	public void setListaDatos(List<T> listaDatos) {
		this.listaDatos = listaDatos;
	}
	
}
