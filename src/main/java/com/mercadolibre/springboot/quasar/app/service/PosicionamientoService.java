package com.mercadolibre.springboot.quasar.app.service;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.springboot.quasar.app.modelo.Satelite;
import com.mercadolibre.springboot.quasar.app.modelo.api.Posicion;
import com.mercadolibre.springboot.quasar.app.modelo.ecuacion.EcuacionCircunferencia;
import com.mercadolibre.springboot.quasar.app.modelo.util.constantes.ExcepcionEnum;
import com.mercadolibre.springboot.quasar.app.modelo.util.constantes.SateliteEnum;
import com.mercadolibre.springboot.quasar.app.modelo.util.excepcion.FuegoQuasarException;

/**
 * 
 * Servicio de negocio que permite calcular el posicionamiento según la
 * información de los satélites
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
@Service
public class PosicionamientoService {

	@Autowired
	private MathService mathService;

	/**
	 * 
	 * Método de negocio que crea una nueva ecuación para una circunferencia
	 * 
	 * @author: Jonatan Alexander Méndez
	 * 
	 * @version: 0.0.1-SNAPSHOT
	 * 
	 * @param posicionCentro Posición del centro de la circunferencia
	 * 
	 * @param radio          Radio de la circunferencia
	 * 
	 * @return Objeto que abstrae la ecuación de una circunferencia
	 * 
	 */
	public EcuacionCircunferencia crearEcuacionSatelite(Posicion posicionCentro, double radio) {
		return new EcuacionCircunferencia(posicionCentro, radio);
	}

	/**
	 * 
	 * Método de negocio que se encarga de calcular la posición del emisor de los
	 * mensajes
	 * 
	 * @author: Jonatan Alexander Méndez
	 * 
	 * @version: 0.0.1-SNAPSHOT
	 * 
	 * @param mapaSatelites Mapa que contiene la información registrada para cada
	 *                      uno de los satélites
	 * @return Posición del emisor del mensaje
	 * 
	 */
	public Posicion obtenerPosicionEmisor(Map<String, Satelite> mapaSatelites) throws FuegoQuasarException {
		EcuacionCircunferencia ecuacionKenobi = ((Satelite) mapaSatelites.get(SateliteEnum.KENOBI.getValor()))
				.getEcuacionCircunferencia();
		EcuacionCircunferencia ecuacionSkywalker = ((Satelite) mapaSatelites.get(SateliteEnum.SKYWALKER.getValor()))
				.getEcuacionCircunferencia();
		EcuacionCircunferencia ecuacionSato = ((Satelite) mapaSatelites.get(SateliteEnum.SATO.getValor()))
				.getEcuacionCircunferencia();
		Map<Integer, Posicion> puntosInterseccion = mathService.obtenerPuntosInterseccion(ecuacionKenobi,
				ecuacionSkywalker);
		Posicion posicion = seleccionarPosicionEmisor(ecuacionSato, puntosInterseccion);
		return posicion;
	}

	private Posicion seleccionarPosicionEmisor(EcuacionCircunferencia ecuacionReemplazo,
			Map<Integer, Posicion> puntosInterseccion) throws FuegoQuasarException {
		for (Entry<Integer, Posicion> entry : puntosInterseccion.entrySet())
			if (mathService.validarIgualdadCircunferencia(ecuacionReemplazo, entry.getValue()))
				return entry.getValue();
		throw new FuegoQuasarException(ExcepcionEnum.NO_SE_ENCONTRO_POSICION);
	}

}
