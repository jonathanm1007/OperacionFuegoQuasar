package com.mercadolibre.springboot.quasar.app.modelo.api;

/**
 * 
 * Clase diseñada para guardar la información de salida al momento de consumir
 * el api rest que consulta la posición y mensaje secreto del emisor del
 * mensaje.
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
public class InformacionEmisor {

	private Posicion position;

	private String message;

	public InformacionEmisor(Posicion position, String message) {
		super();
		this.position = position;
		this.message = message;
	}

	public Posicion getPosition() {
		return position;
	}

	public void setPosition(Posicion position) {
		this.position = position;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
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
		InformacionEmisor other = (InformacionEmisor) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SalidaInfoPosicion [position=" + position + ", message=" + message + "]";
	}

}
