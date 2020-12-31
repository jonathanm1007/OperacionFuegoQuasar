package com.mercadolibre.springboot.quasar.app.modelo.ecuacion;

/**
 * 
 * Clase diseñada para abstraer como está definida la ecuación general de una
 * circunferencia. Forma de la ecuación (x - a)^2 + (y-b)^2 = r^2
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
public class EcuacionCircunferenciaOrd {

	private double x;

	private double a;

	private double y;

	private double b;

	private double r;

	public EcuacionCircunferenciaOrd(double a, double b, double r) {
		super();
		this.x = 1;
		this.a = a;
		this.y = 1;
		this.b = b;
		this.r = r;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getA() {
		return a;
	}

	public void setA(double alfa) {
		this.a = alfa;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getB() {
		return b;
	}

	public void setB(double beta) {
		this.b = beta;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	@Override
	public String toString() {
		return "Expresion Ordinaria [(X" + (a < 0 ? " + " : " - ") + Math.abs(a) + ")^2 + (Y" + (b < 0 ? " + " : " - ")
				+ Math.abs(b) + ")^2 = (" + r + ")^2]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(a);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(b);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(r);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
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
		EcuacionCircunferenciaOrd other = (EcuacionCircunferenciaOrd) obj;
		if (Double.doubleToLongBits(a) != Double.doubleToLongBits(other.a))
			return false;
		if (Double.doubleToLongBits(b) != Double.doubleToLongBits(other.b))
			return false;
		if (Double.doubleToLongBits(r) != Double.doubleToLongBits(other.r))
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

}
