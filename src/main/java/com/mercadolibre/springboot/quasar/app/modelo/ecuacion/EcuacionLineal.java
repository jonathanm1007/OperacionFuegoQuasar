package com.mercadolibre.springboot.quasar.app.modelo.ecuacion;

/**
 * 
 * Clase diseñada para abstraer como está definida la ecuación general lineal.
 * Forma de la ecuación X = aY + b
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */

public class EcuacionLineal {

	private double a;

	private double b;

	private double y;

	public EcuacionLineal() {
		super();
	}

	public EcuacionLineal(double a, double b) {
		super();
		this.a = a;
		this.b = b;
	}

	public double getA() {
		return a;
	}

	public void setA(double m) {
		this.a = m;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(b);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(a);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		EcuacionLineal other = (EcuacionLineal) obj;
		if (Double.doubleToLongBits(b) != Double.doubleToLongBits(other.b))
			return false;
		if (Double.doubleToLongBits(a) != Double.doubleToLongBits(other.a))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExpresionLineal [X = " + a + "Y" + (b < 0 ? " - " : " + ") + Math.abs(b) + "]";
	}

}
