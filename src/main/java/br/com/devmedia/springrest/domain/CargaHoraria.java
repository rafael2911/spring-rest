package br.com.devmedia.springrest.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CargaHoraria {
	VINTE_HORAS("20HS"), TRINTA_HORAS("30HS"), QUARENTA_HORAS("40HS");
	
	private String horas;
	
	private CargaHoraria(String horas) {
		this.horas = horas;
	}
	
	@JsonValue
	public String getHoras() {
		return horas;
	}
}
