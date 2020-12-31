package com.mercadolibre.springboot.quasar.app.modelo.util;

/**
 * 
 * Clase utilitaria para manejo de fórmulas matemáticas.
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
public class MathUtil {
	/**
	 * 
	 * Método que redondea un número
	 * 
	 * @author: Jonatan Alexander Méndez
	 * 
	 * @version: 0.0.1-SNAPSHOT
	 * 
	 * @param number Número a redondear
	 * @return Número redondeado
	 * 
	 */
	public static double redondearNumero(double number) {
		return Math.round(number * Math.pow(10, 1)) / Math.pow(10, 1);
	}

}
