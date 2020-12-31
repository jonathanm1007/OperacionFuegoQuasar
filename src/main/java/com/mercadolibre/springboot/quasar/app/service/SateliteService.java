package com.mercadolibre.springboot.quasar.app.service;

import org.springframework.stereotype.Service;

import com.mercadolibre.springboot.quasar.app.modelo.DataSatelites;
import com.mercadolibre.springboot.quasar.app.modelo.util.JSONUtil;
import com.mercadolibre.springboot.quasar.app.modelo.util.constantes.RutaConst;
import com.mercadolibre.springboot.quasar.app.modelo.util.excepcion.FuegoQuasarException;

/**
 * 
 * Servicio de negocio que permite dar manejo a los Satélites
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
@Service
public class SateliteService {
	/**
	 * 
	 * Método de consulta el listado de satélites del sistema en un archivo JSON
	 * 
	 * @author: Jonatan Alexander Méndez
	 * 
	 * @version: 0.0.1-SNAPSHOT
	 * 
	 * @return Objeto que contiene el listado de satélites.
	 * 
	 */
	public DataSatelites consultarSatelites() throws FuegoQuasarException {
		DataSatelites dataSatelites = new DataSatelites();
		dataSatelites = (DataSatelites) JSONUtil.convertirJsonAObjeto(RutaConst.ARCHIVO_JSON_SATELITES,
				DataSatelites.class);
		return dataSatelites;
	}
}
