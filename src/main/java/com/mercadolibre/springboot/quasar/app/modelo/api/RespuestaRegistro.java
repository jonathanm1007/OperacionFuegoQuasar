package com.mercadolibre.springboot.quasar.app.modelo.api;

/**
 * 
 * Clase diseñada para guardar devolver una respuesta luego de registrar la
 * información de uno de los satélites de manera individual.
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
public class RespuestaRegistro {

	private String codigoError;
	private String mensajeError;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoError == null) ? 0 : codigoError.hashCode());
		result = prime * result + ((mensajeError == null) ? 0 : mensajeError.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RespuestaRegistro other = (RespuestaRegistro) obj;
		if (codigoError == null) {
			if (other.codigoError != null)
				return false;
		} else if (!codigoError.equals(other.codigoError))
			return false;
		if (mensajeError == null) {
			if (other.mensajeError != null)
				return false;
		} else if (!mensajeError.equals(other.mensajeError))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RespuestaRegistro [codigoError=" + codigoError + ", mensajeError=" + mensajeError + "]";
	}

}
