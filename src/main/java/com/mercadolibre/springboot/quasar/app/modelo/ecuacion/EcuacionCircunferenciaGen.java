package com.mercadolibre.springboot.quasar.app.modelo.ecuacion;

/**
 * 
 * Clase diseñada para abstraer como está definida la ecuación general de una
 * circunferencia. Forma de la ecuación aX^2 + by^2 + cx + dy + e = 0
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
public class EcuacionCircunferenciaGen {

	private double a;

	private double b;

	private double c;

	private double d;

	private double e;

	private double x;

	private double y;

	public EcuacionCircunferenciaGen() {
		super();
	}

	public EcuacionCircunferenciaGen(double a, double b, double r) {
		super();
		this.a = 1;
		this.b = 1;
		this.c = 2 * a * -1;
		this.d = 2 * b * -1;
		this.e = Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(r, 2);
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}

	public double getE() {
		return e;
	}

	public void setE(double e) {
		this.e = e;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Expresion General [" + a + "X^2 + " + b + "Y^2" + (c < 0 ? " - " : " + ") + Math.abs(c) + "X"
				+ (d < 0 ? " - " : " + ") + Math.abs(d) + "Y" + (e < 0 ? " - " : " + ") + Math.abs(e) + " = 0]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(e);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(c);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(a);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(d);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(b);
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
		EcuacionCircunferenciaGen other = (EcuacionCircunferenciaGen) obj;
		if (Double.doubleToLongBits(e) != Double.doubleToLongBits(other.e))
			return false;
		if (Double.doubleToLongBits(c) != Double.doubleToLongBits(other.c))
			return false;
		if (Double.doubleToLongBits(a) != Double.doubleToLongBits(other.a))
			return false;
		if (Double.doubleToLongBits(d) != Double.doubleToLongBits(other.d))
			return false;
		if (Double.doubleToLongBits(b) != Double.doubleToLongBits(other.b))
			return false;
		return true;
	}

}
