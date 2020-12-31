package com.mercadolibre.springboot.quasar.app.modelo.api;

import java.util.List;

/**
 * 
 * Clase diseñada para guardar la información de entrada al momento de consumir
 * el api rest que trae la información de todos los satélites.
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
public class InformacionAuxilio {

	private List<InformacionAuxilioSatelite> satellites;

	public List<InformacionAuxilioSatelite> getSatellites() {
		return satellites;
	}

	public void setSatellites(List<InformacionAuxilioSatelite> iInformacionAuxilioSatelites) {
		this.satellites = iInformacionAuxilioSatelites;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((satellites == null) ? 0 : satellites.hashCode());
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
		InformacionAuxilio other = (InformacionAuxilio) obj;
		if (satellites == null) {
			if (other.satellites != null)
				return false;
		} else if (!satellites.equals(other.satellites))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EntradaInfoMensajes [satellites=" + satellites + "]";
	}
}
