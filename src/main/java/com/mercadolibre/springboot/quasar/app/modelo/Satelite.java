package com.mercadolibre.springboot.quasar.app.modelo;

/**
 * 
 * Clase diseñada para guardar la información de cada uno de los satelites. Dicha información se obtiene del
 * archivo dataSatelites.json y se complementa con el consumo de los métodos del api rest expuesto.
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
import java.util.Arrays;

import com.mercadolibre.springboot.quasar.app.modelo.api.Posicion;
import com.mercadolibre.springboot.quasar.app.modelo.ecuacion.EcuacionCircunferencia;

public class Satelite {

	private String nombre;

	private Posicion posicion;

	private double distancia;

	private String mensaje[];

	private EcuacionCircunferencia ecuacionCircunferencia;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public String[] getMensaje() {
		return mensaje;
	}

	public void setMensaje(String[] mensaje) {
		this.mensaje = mensaje;
	}

	public EcuacionCircunferencia getEcuacionCircunferencia() {
		return ecuacionCircunferencia;
	}

	public void setEcuacionCircunferencia(EcuacionCircunferencia ecuacionCircunferencia) {
		this.ecuacionCircunferencia = ecuacionCircunferencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(distancia);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((ecuacionCircunferencia == null) ? 0 : ecuacionCircunferencia.hashCode());
		result = prime * result + Arrays.hashCode(mensaje);
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((posicion == null) ? 0 : posicion.hashCode());
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
		Satelite other = (Satelite) obj;
		if (Double.doubleToLongBits(distancia) != Double.doubleToLongBits(other.distancia))
			return false;
		if (ecuacionCircunferencia == null) {
			if (other.ecuacionCircunferencia != null)
				return false;
		} else if (!ecuacionCircunferencia.equals(other.ecuacionCircunferencia))
			return false;
		if (!Arrays.equals(mensaje, other.mensaje))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (posicion == null) {
			if (other.posicion != null)
				return false;
		} else if (!posicion.equals(other.posicion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Satelite [nombre=" + nombre + ", posicion=" + posicion + ", distancia=" + distancia + ", mensaje="
				+ Arrays.toString(mensaje) + ", ecuacionCircunferencia=" + ecuacionCircunferencia + "]";
	}

}
