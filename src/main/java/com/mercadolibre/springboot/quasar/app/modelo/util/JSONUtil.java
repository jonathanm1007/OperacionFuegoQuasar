package com.mercadolibre.springboot.quasar.app.modelo.util;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.mercadolibre.springboot.quasar.app.modelo.util.constantes.ExcepcionEnum;
import com.mercadolibre.springboot.quasar.app.modelo.util.excepcion.FuegoQuasarException;

/**
 * 
 * Clase utilitaria para manejo de fuentes de datos de tipo JSON. Provee métodos
 * útiles para leer y mapear archivos de tipo JSON.
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
public class JSONUtil {

	private final static Gson gson = new Gson();

	/**
	 * 
	 * Método que convierte un archivo JSON leido en el sistema y mapea su
	 * información en un objeto
	 * 
	 * @author: Jonatan Alexander Méndez
	 * 
	 * @version: 0.0.1-SNAPSHOT
	 * 
	 * @param rutaArchivo Ruta del archivo a leer
	 * @param clazz       Tipo de Objeto en donde se mapea la información
	 * @return Objeto con la información mapeada
	 * 
	 */
	public static <T> Object convertirJsonAObjeto(String rutaArchivo, Class<T> clazz) throws FuegoQuasarException {
		validarConversion(rutaArchivo, clazz);
		try {
			return gson.fromJson(new FileReader(rutaArchivo), clazz);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			throw new FuegoQuasarException(ExcepcionEnum.ERROR_CONVERSION_JSON, e);
		}
	}

	private static <T> void validarConversion(String rutaArchivo, Class<T> clazz) throws FuegoQuasarException {
		if (StringUtils.isEmpty(rutaArchivo))
			throw new FuegoQuasarException(ExcepcionEnum.NO_SE_ENCONTRO_ARCHIVO_JSON);
		if (null == clazz)
			throw new FuegoQuasarException(ExcepcionEnum.TIPO_DE_OBJETO_NO_ENVIADO);
	}

}
