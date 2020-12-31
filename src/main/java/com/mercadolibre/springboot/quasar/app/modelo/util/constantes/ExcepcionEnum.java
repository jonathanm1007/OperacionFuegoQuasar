package com.mercadolibre.springboot.quasar.app.modelo.util.constantes;

/**
 * 
 * Enum con los mensajes de error y excepción del sistema.
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
public enum ExcepcionEnum {
	EXITO("0", ""), NO_SE_ENCONTRO_ARCHIVO_JSON("1", "No se encontró un archivo JSON con la data de los satélites"),
	TIPO_DE_OBJETO_NO_ENVIADO("2", "No se recibió el tipo de Objeto a mapear el JSON"),
	ERROR_CONVERSION_JSON("3", "Ocurrió un error convirtiendo el JSON a Objeto"),
	MENSAJE_AUXILIO_NULO("4", "No fue enviada la información de auxilio de ningún satélite"),
	NOMBRE_SATELITE_NO_ENVIADO("5", "No fue enviado el nombre del satélite del mensaje"),
	DISTANCIA_NO_VALIDA("6", "La distancia enviada en el mensaje no es válida, debe ser mayor o igual a cero"),
	MENSAJE_NO_ENVIADO("7", "No fue enviada la información del mensaje asociado al satélite"),
	SATELITES_NO_CONSULTADOS("8", "No fue posible consultar los Satélites en la BD de Quasar"),
	SATELITE_NO_ENCONTRADO("9", "El nombre del satélite enviado no se encuentra registrado en Quasar"),
	CANTIDAD_SATELITES_INCORRECTA("10",
			"La cantidad de satélites para procesar no corresponden con los satélites parametrizados"),
	SATELITE_SIN_ECUACION("11",
			"Uno de los satélites no cuenta con ecuación necesaria para calcular el posicionamiento"),
	SATELITE_SIN_MENSAJE("12", "Uno de los satélites no cuenta con mensaje de auxilio"),
	MENSAJE_DIFERENTE_TAMANIO("13", "El tamaño del mensaje es distinto entre los diferentes satélites"),
	MENSAJE_NO_DESCIFRADO("14", "No fue posible determinar el mensaje secreto. Los mensajes no corresponden."),
	MENSAJE_INCOMPLETO("15", "No fue posible determinar el mensaje secreto. El mensaje no llegó completo"),
	NO_SE_ENCONTRO_POSICION("16",
			"No fue posible determinar la posicion del emisor del mensaje con la información suministrada"),
	NO_EXISTEN_SOLUCIONES_CUADRATICA("17", "No existen soluciones reales para la ecuación cuadrática");

	private String codigoError;
	private String mensajeError;

	private ExcepcionEnum(String codigoError, String mensajeError) {
		this.codigoError = codigoError;
		this.mensajeError = mensajeError;
	}

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

}
