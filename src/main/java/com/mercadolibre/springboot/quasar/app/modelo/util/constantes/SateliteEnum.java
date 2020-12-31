package com.mercadolibre.springboot.quasar.app.modelo.util.constantes;

/**
 * 
 * Enum con los nombres de los diferentes satélites.
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
public enum SateliteEnum {

	KENOBI("Kenobi"), SKYWALKER("Skywalker"), SATO("Sato");

	private String valor;

	private SateliteEnum(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
