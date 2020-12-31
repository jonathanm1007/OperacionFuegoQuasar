package com.mercadolibre.springboot.quasar.app.modelo.util.constantes;

/**
 * 
 * Enum con cadenas de texto generales
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
public enum CadenaEnum {

	SALTO_DE_LINEA("\n"), VACIO(""), ESPACIO(" "), ECUACION_GENERAL("Ecuación General"),
	ECUACION_CIRCUNFERENCIA("Ecuación Circunferencia");

	private String valor;

	private CadenaEnum(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
