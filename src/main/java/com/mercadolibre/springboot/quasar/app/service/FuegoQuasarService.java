package com.mercadolibre.springboot.quasar.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.springboot.quasar.app.modelo.DataSatelites;
import com.mercadolibre.springboot.quasar.app.modelo.Satelite;
import com.mercadolibre.springboot.quasar.app.modelo.api.InformacionAuxilio;
import com.mercadolibre.springboot.quasar.app.modelo.api.InformacionAuxilioSatelite;
import com.mercadolibre.springboot.quasar.app.modelo.api.InformacionEmisor;
import com.mercadolibre.springboot.quasar.app.modelo.api.Posicion;
import com.mercadolibre.springboot.quasar.app.modelo.api.RespuestaRegistro;
import com.mercadolibre.springboot.quasar.app.modelo.ecuacion.EcuacionCircunferencia;
import com.mercadolibre.springboot.quasar.app.modelo.util.ColeccionesUtil;
import com.mercadolibre.springboot.quasar.app.modelo.util.constantes.ExcepcionEnum;
import com.mercadolibre.springboot.quasar.app.modelo.util.constantes.GeneralConst;
import com.mercadolibre.springboot.quasar.app.modelo.util.excepcion.FuegoQuasarException;

/**
 * 
 * Servicio de negocio principal de la aplicación. Contiene los métodos que se
 * consumen desde el api rest.
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
@Service
public class FuegoQuasarService {

	private Map<String, Satelite> mapaSatelites;

	@Autowired
	private SateliteService sateliteService;

	@Autowired
	private PosicionamientoService posicionamientoService;

	@Autowired
	private MensajeService mensajeService;

	/**
	 * 
	 * Método que se ejecuta cuando se crea la instancia del servicio. Consulta en
	 * el sistema la información fija de los satélites y los guarda en un HashMap
	 * 
	 * @author: Jonatan Alexander Méndez
	 * 
	 * @version: 0.0.1-SNAPSHOT
	 * 
	 * 
	 */
	@PostConstruct
	public void init() {
		consultarSatelites();
	}

	/**
	 * 
	 * Método de negocio que se encarga de calcular la posición del emisor del
	 * mensaje y descifrar el mensaje secreto
	 * 
	 * @author: Jonatan Alexander Méndez
	 * 
	 * @version: 0.0.1-SNAPSHOT
	 * 
	 * @param informacionAuxilio Información de entrada que viene desde el consumo
	 *                           del servicio rest
	 * @return Posición del emisor y mensaje descifrado
	 * 
	 */
	public InformacionEmisor obtenerInformacionAuxilio(InformacionAuxilio informacionAuxilio)
			throws FuegoQuasarException {
		validarInformacionAuxilio(informacionAuxilio);
		for (InformacionAuxilioSatelite informacionSatelite : informacionAuxilio.getSatellites())
			registrarInformacionAuxilioSatelite(informacionSatelite);
		InformacionEmisor informacionEmisor = obtenerInformacionAuxilio();
		return informacionEmisor;
	}

	/**
	 * 
	 * Método de negocio que se encarga de registrar en el mapa de satélites la
	 * información enviada de un nuevo satélite
	 * 
	 * @author: Jonatan Alexander Méndez
	 * 
	 * @version: 0.0.1-SNAPSHOT
	 * 
	 * @param informacionSatelite Información de la distancia y mensaje secreto
	 *                            enviada al satélite
	 * @return Objeto con la información de exito o error de registro de la
	 *         información
	 * 
	 */
	public RespuestaRegistro registrarInformacionAuxilioSatelite(InformacionAuxilioSatelite informacionSatelite)
			throws FuegoQuasarException {
		RespuestaRegistro respuestaRegistro = new RespuestaRegistro();
		Satelite satelite = registrarInformacionAuxilio(informacionSatelite);
		registrarEcuacionSatelite(satelite);
		respuestaRegistro.setCodigoError(ExcepcionEnum.EXITO.getCodigoError());
		respuestaRegistro.setMensajeError(ExcepcionEnum.EXITO.getMensajeError());
		return respuestaRegistro;
	}

	/**
	 * 
	 * Método de negocio que se encarga de calcular la posición del emisor del
	 * mensaje y descifrar el mensaje secreto
	 * 
	 * @author: Jonatan Alexander Méndez
	 * 
	 * @version: 0.0.1-SNAPSHOT
	 * 
	 * @return Posición del emisor y mensaje descifrado
	 * 
	 */
	public InformacionEmisor obtenerInformacionAuxilio() throws FuegoQuasarException {
		validarMapaSatelites();
		Posicion posicion = posicionamientoService.obtenerPosicionEmisor(mapaSatelites);
		String mensajeAuxilio = mensajeService.descifrarMensajeAuxilio(mapaSatelites);
		InformacionEmisor informacionEmisor = new InformacionEmisor(posicion, mensajeAuxilio);
		return informacionEmisor;
	}

	private void consultarSatelites() {
		DataSatelites dataSatelites = new DataSatelites();
		try {
			dataSatelites = sateliteService.consultarSatelites();
		} catch (FuegoQuasarException e) {
			dataSatelites.setSatelites(new ArrayList<Satelite>());
		}
		mapaSatelites = new HashMap<>();
		dataSatelites.getSatelites().forEach((Satelite satelite) -> mapaSatelites.put(satelite.getNombre(), satelite));
	}

	private void validarInformacionAuxilio(InformacionAuxilio informacionAuxilio) throws FuegoQuasarException {
		if (null == informacionAuxilio || ColeccionesUtil.isEmpty(informacionAuxilio.getSatellites()))
			throw new FuegoQuasarException(ExcepcionEnum.MENSAJE_AUXILIO_NULO);
		for (InformacionAuxilioSatelite informacionSatelite : informacionAuxilio.getSatellites())
			validarInformacionSatelite(informacionSatelite);
	}

	private void validarInformacionSatelite(InformacionAuxilioSatelite informacionSatelite)
			throws FuegoQuasarException {
		if (StringUtils.isEmpty(informacionSatelite.getName()))
			throw new FuegoQuasarException(ExcepcionEnum.NOMBRE_SATELITE_NO_ENVIADO);
		if (0.0 > informacionSatelite.getDistance())
			throw new FuegoQuasarException(ExcepcionEnum.DISTANCIA_NO_VALIDA);
		if (ColeccionesUtil.isEmpty(informacionSatelite.getMessage()))
			throw new FuegoQuasarException(ExcepcionEnum.MENSAJE_NO_ENVIADO);
		if (ColeccionesUtil.isEmpty(mapaSatelites))
			throw new FuegoQuasarException(ExcepcionEnum.SATELITES_NO_CONSULTADOS);
		if (!existeSatelite(informacionSatelite))
			throw new FuegoQuasarException(ExcepcionEnum.SATELITE_NO_ENCONTRADO);
	}

	private boolean existeSatelite(InformacionAuxilioSatelite informacionSatelite) {
		if (null == mapaSatelites.get(informacionSatelite.getName()))
			return false;
		return true;
	}

	private Satelite registrarInformacionAuxilio(InformacionAuxilioSatelite informacionSatelite) {
		Satelite satelite = mapaSatelites.get(informacionSatelite.getName());
		satelite.setDistancia((informacionSatelite.getDistance()));
		satelite.setMensaje(informacionSatelite.getMessage());
		return satelite;
	}

	private void registrarEcuacionSatelite(Satelite satelite) throws FuegoQuasarException {
		EcuacionCircunferencia ecuacionCircunferencia = posicionamientoService
				.crearEcuacionSatelite(satelite.getPosicion(), satelite.getDistancia());
		satelite.setEcuacionCircunferencia(ecuacionCircunferencia);
	}

	private void validarMapaSatelites() throws FuegoQuasarException {
		if (ColeccionesUtil.isEmpty(mapaSatelites))
			throw new FuegoQuasarException(ExcepcionEnum.SATELITES_NO_CONSULTADOS);
		if (GeneralConst.CANTIDAD_SATELITES != mapaSatelites.size())
			throw new FuegoQuasarException(ExcepcionEnum.CANTIDAD_SATELITES_INCORRECTA);
		Satelite primerSatelite = ((Satelite) mapaSatelites.entrySet().iterator().next().getValue());
		for (Entry<String, Satelite> entry : mapaSatelites.entrySet()) {
			Satelite satelite = (Satelite) entry.getValue();
			validarEcuacionSatelite(satelite);
			validarMensajeSatelite(primerSatelite, satelite);
		}
	}

	private void validarEcuacionSatelite(Satelite satelite) throws FuegoQuasarException {
		if (null == satelite.getEcuacionCircunferencia())
			throw new FuegoQuasarException(ExcepcionEnum.SATELITE_SIN_ECUACION);
	}

	private void validarMensajeSatelite(Satelite primerSatelite, Satelite satelite) throws FuegoQuasarException {
		if (ColeccionesUtil.isEmpty(satelite.getMensaje()))
			throw new FuegoQuasarException(ExcepcionEnum.SATELITE_SIN_MENSAJE);
		if (!ColeccionesUtil.isEmpty(primerSatelite.getMensaje())
				&& primerSatelite.getMensaje().length != satelite.getMensaje().length)
			throw new FuegoQuasarException(ExcepcionEnum.MENSAJE_DIFERENTE_TAMANIO);
	}
}
