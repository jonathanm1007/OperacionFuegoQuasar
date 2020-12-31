package com.mercadolibre.springboot.quasar.app.modelo.util.excepcion;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.mercadolibre.springboot.quasar.app.modelo.util.constantes.ExcepcionEnum;

/**
 * 
 * Clase que encapsula la estructura de una excepción general del sistema
 * FuegoQuasar.
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
public class FuegoQuasarException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6372849678091205619L;

	private String codigoError;
	private String mensajeError;
	private String trazaError;

	public FuegoQuasarException() {
		super();
	}

	public FuegoQuasarException(ExcepcionEnum excepcionEnum) {
		super();
		this.codigoError = excepcionEnum.getCodigoError();
		this.mensajeError = excepcionEnum.getMensajeError();
		this.trazaError = excepcionEnum.getMensajeError();
	}

	public FuegoQuasarException(ExcepcionEnum excepcionEnum, Exception e) {
		super();
		this.codigoError = excepcionEnum.getCodigoError();
		this.mensajeError = excepcionEnum.getMensajeError();
		this.trazaError = (null == e ? excepcionEnum.getMensajeError() : ExceptionUtils.getStackTrace(e));
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

	public String getTrazaError() {
		return trazaError;
	}

	public void setTrazaError(String trazaError) {
		this.trazaError = trazaError;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoError == null) ? 0 : codigoError.hashCode());
		result = prime * result + ((mensajeError == null) ? 0 : mensajeError.hashCode());
		result = prime * result + ((trazaError == null) ? 0 : trazaError.hashCode());
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
		FuegoQuasarException other = (FuegoQuasarException) obj;
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
		if (trazaError == null) {
			if (other.trazaError != null)
				return false;
		} else if (!trazaError.equals(other.trazaError))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FuegoQuasarException [codigoError=" + codigoError + ", mensajeError=" + mensajeError + ", trazaError="
				+ trazaError + "]";
	}

}
