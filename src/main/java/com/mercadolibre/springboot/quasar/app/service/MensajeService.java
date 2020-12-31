package com.mercadolibre.springboot.quasar.app.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.mercadolibre.springboot.quasar.app.modelo.Satelite;
import com.mercadolibre.springboot.quasar.app.modelo.util.constantes.CadenaEnum;
import com.mercadolibre.springboot.quasar.app.modelo.util.constantes.ExcepcionEnum;
import com.mercadolibre.springboot.quasar.app.modelo.util.excepcion.FuegoQuasarException;

/**
 * 
 * Servicio de negocio que permite descifrar el mensaje secreto
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
@Service
public class MensajeService {
	/**
	 * 
	 * Método de descifra el mensaje de auxilio según la información obtenida en
	 * cada satélite
	 * 
	 * @author: Jonatan Alexander Méndez
	 * 
	 * @version: 0.0.1-SNAPSHOT
	 * 
	 * @param mapaSatelites Mapa con la información de los satélites
	 * 
	 * @return Cadena con el mensaje descifrado
	 * 
	 */
	public String descifrarMensajeAuxilio(Map<String, Satelite> mapaSatelites) throws FuegoQuasarException {
		Map<Integer, String> mapaMensajes = crearMapaMensaje(mapaSatelites);
		mapaMensajes = llenarMapaMensaje(mapaSatelites, mapaMensajes);
		StringBuilder stringBuilder = new StringBuilder();
		for (Entry<Integer, String> entry : mapaMensajes.entrySet()) {
			if (StringUtils.isEmpty(entry.getValue()))
				throw new FuegoQuasarException(ExcepcionEnum.MENSAJE_INCOMPLETO);
			stringBuilder.append(entry.getValue()).append(CadenaEnum.ESPACIO.getValor());
		}
		String mensajeAuxilio = stringBuilder.toString();
		return mensajeAuxilio.substring(0, mensajeAuxilio.length() - 1);
	}

	private Map<Integer, String> crearMapaMensaje(Map<String, Satelite> mapaSatelites) {
		Map<Integer, String> mapaMensaje = new LinkedHashMap<Integer, String>();
		Satelite primerSatelite = ((Satelite) mapaSatelites.entrySet().iterator().next().getValue());
		for (int i = 0; i < primerSatelite.getMensaje().length; i++)
			mapaMensaje.put(i, CadenaEnum.VACIO.getValor());
		return mapaMensaje;
	}

	private Map<Integer, String> llenarMapaMensaje(Map<String, Satelite> mapaSatelites,
			Map<Integer, String> mapaMensajes) throws FuegoQuasarException {
		for (Entry<String, Satelite> entry : mapaSatelites.entrySet()) {
			Satelite satelite = (Satelite) entry.getValue();
			for (int i = 0; i < satelite.getMensaje().length; i++) {
				if (StringUtils.isEmpty(satelite.getMensaje()[i]))
					continue;
				if (StringUtils.isEmpty(mapaMensajes.get(i))) {
					mapaMensajes.put(i, satelite.getMensaje()[i]);
					continue;
				}
				if (!mapaMensajes.get(i).equals(satelite.getMensaje()[i]))
					throw new FuegoQuasarException(ExcepcionEnum.MENSAJE_NO_DESCIFRADO);
			}
		}
		return mapaMensajes;
	}

}
