package com.mercadolibre.springboot.quasar.app.modelo;

import java.util.ArrayList;

/**
 * 
 * Clase diseñada para consultar la información de los satélites en el sistema,
 * como lo son la posición fija y el nombre. Dicha información se obtiene del
 * archivo dataSatelites.json
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
public class DataSatelites {

	private ArrayList<Satelite> satelites;

	public ArrayList<Satelite> getSatelites() {
		return satelites;
	}

	public void setSatelites(ArrayList<Satelite> satelites) {
		this.satelites = satelites;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((satelites == null) ? 0 : satelites.hashCode());
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
		DataSatelites other = (DataSatelites) obj;
		if (satelites == null) {
			if (other.satelites != null)
				return false;
		} else if (!satelites.equals(other.satelites))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DataSatelites [satelites=" + satelites + "]";
	}

}
