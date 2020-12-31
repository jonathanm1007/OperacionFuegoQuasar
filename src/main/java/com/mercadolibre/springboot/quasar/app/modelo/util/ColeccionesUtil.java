package com.mercadolibre.springboot.quasar.app.modelo.util;

import java.util.Collection;
import java.util.Map;

/**
 * 
 * Clase utilitaria para manejo de listas, arreglos y demás topos de
 * colecciones.
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
public class ColeccionesUtil {
	/**
	 * 
	 * Método que valida si un objeto de tipo Collection se encuentra vacio o no
	 * 
	 * @author: Jonatan Alexander Méndez
	 * 
	 * @version: 0.0.1-SNAPSHOT
	 * 
	 * @param collection Colección a validar
	 * 
	 * @return Indicador booleano que determina si está vacia o no
	 * 
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return null == collection || collection.isEmpty();
	}

	/**
	 * 
	 * Método que valida si un objeto de tipo Arreglo de objetos se encuentra vacio
	 * o no
	 * 
	 * @author: Jonatan Alexander Méndez
	 * 
	 * @version: 0.0.1-SNAPSHOT
	 * 
	 * @param arreglo Arreglo a validar
	 * 
	 * @return Indicador booleano que determina si está vacio o no
	 * 
	 */
	public static boolean isEmpty(Object[] arreglo) {
		return null == arreglo || 0 == arreglo.length;
	}

	/**
	 * 
	 * Método que valida si un objeto de tipo Map se encuentra vacio o no
	 * 
	 * @author: Jonatan Alexander Méndez
	 * 
	 * @version: 0.0.1-SNAPSHOT
	 * 
	 * @param map Mapa a validar
	 * 
	 * @return Indicador booleano que determina si está vacio o no
	 * 
	 */
	public static <K, V> boolean isEmpty(Map<K, V> map) {
		return null == map || 0 == map.size();
	}
}
