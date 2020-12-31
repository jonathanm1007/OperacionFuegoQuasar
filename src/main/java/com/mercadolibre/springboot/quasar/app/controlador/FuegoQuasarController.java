package com.mercadolibre.springboot.quasar.app.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mercadolibre.springboot.quasar.app.modelo.api.InformacionAuxilio;
import com.mercadolibre.springboot.quasar.app.modelo.api.InformacionAuxilioSatelite;
import com.mercadolibre.springboot.quasar.app.modelo.api.InformacionEmisor;
import com.mercadolibre.springboot.quasar.app.modelo.api.RespuestaRegistro;
import com.mercadolibre.springboot.quasar.app.modelo.util.constantes.RutaConst;
import com.mercadolibre.springboot.quasar.app.modelo.util.excepcion.FuegoQuasarException;
import com.mercadolibre.springboot.quasar.app.service.FuegoQuasarService;

/**
 * 
 * Clase principal de la aplicación de Springboot, con la cual se inicia el hilo
 * de ejecución
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
@RestController
public class FuegoQuasarController {

	@Autowired
	private FuegoQuasarService fuegoQuasarService;

	/**
	 * 
	 * Método expuesto en el api rest que permite calcular la posición del emisor
	 * del mensaje y el mensaje secreto según la información de entrada de los tres
	 * satélites.
	 * 
	 * @author: Jonatan Alexander Méndez
	 * 
	 * @version: 0.0.1-SNAPSHOT
	 * 
	 * @param informacionAuxilio Parámetro que trae la información de los tres
	 *                           satélites, como lo son la distancia al emisor y el
	 *                           mensaje secreto recibido
	 * 
	 * @return La posición (x,y) del emisor del mensaje junto con el mensaje secreto
	 *         descifrado.
	 * 
	 */
	@PostMapping(RutaConst.RUTA_TOP_SECRET)
	public InformacionEmisor obtenerInfoMensajeAuxilio(@RequestBody InformacionAuxilio informacionAuxilio) {
		try {
			return fuegoQuasarService.obtenerInformacionAuxilio(informacionAuxilio);
		} catch (FuegoQuasarException f) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, f.getTrazaError(), f);
		}
	}

	/**
	 * 
	 * Método expuesto en el api rest que permite registrar la información de uno de
	 * los satélites de manera independiente.
	 * 
	 * @author: Jonatan Alexander Méndez
	 * 
	 * @version: 0.0.1-SNAPSHOT
	 * 
	 * @param satelliteName       Parámetro que trae el nombre del satélite que
	 *                            envía la información
	 * 
	 * @param informacionSatelite Parámetro que trae la distancia al emisor y el
	 *                            mensaje secreto recibido por este satélite
	 * 
	 * @return Código y mensaje de error o éxito del registro.
	 * 
	 */
	@PostMapping(RutaConst.RUTA_TOP_SECRET_POR_ID)
	public RespuestaRegistro registrarMensajeAuxilio(@PathVariable String satelliteName,
			@RequestBody InformacionAuxilioSatelite informacionSatelite) {
		try {
			informacionSatelite.setName(satelliteName);
			return fuegoQuasarService.registrarInformacionAuxilioSatelite(informacionSatelite);
		} catch (FuegoQuasarException f) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, f.getMessage(), f);
		}
	}

	/**
	 * 
	 * Método expuesto en el api rest que permite consultar la posición y mensaje
	 * secreto luego de haber utilizado el método que registra individualmente la
	 * información enviada por cada satélite.
	 * 
	 * @author: Jonatan Alexander Méndez
	 * 
	 * @version: 0.0.1-SNAPSHOT
	 * 
	 * @return La posición (x,y) del emisor del mensaje junto con el mensaje secreto
	 *         descifrado.
	 * 
	 * @see registrarMensajeAuxilio
	 * 
	 */
	@GetMapping(RutaConst.RUTA_TOP_SECRET_SPLIT)
	public InformacionEmisor obtenerInfoMensajeAuxilio() {
		try {
			return fuegoQuasarService.obtenerInformacionAuxilio();
		} catch (FuegoQuasarException f) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, f.getMessage(), f);
		}
	}

}
