package com.mercadolibre.springboot.quasar.app.modelo.ecuacion;

/**
 * 
 * Clase diseñada para abstraer como está definida la ecuación de una circunferencia. 
 * Contiene dos atributos que determinan la ecuación ordinaria y general según corresponda.
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
import com.mercadolibre.springboot.quasar.app.modelo.api.Posicion;

public class EcuacionCircunferencia {

	private EcuacionCircunferenciaOrd ecuacionCircunferenciaOrd;

	private EcuacionCircunferenciaGen ecuacionCircunferenciaGen;

	public EcuacionCircunferencia(Posicion posicionCentro, double radio) {
		super();
		this.ecuacionCircunferenciaOrd = new EcuacionCircunferenciaOrd(posicionCentro.getX(), posicionCentro.getY(),
				radio);
		this.ecuacionCircunferenciaGen = new EcuacionCircunferenciaGen(posicionCentro.getX(), posicionCentro.getY(),
				radio);
	}

	public EcuacionCircunferenciaOrd getExpresionCircunferenciaOrdinaria() {
		return ecuacionCircunferenciaOrd;
	}

	public void setExpresionCircunferenciaOrdinaria(EcuacionCircunferenciaOrd expresionOrdinaria) {
		this.ecuacionCircunferenciaOrd = expresionOrdinaria;
	}

	public EcuacionCircunferenciaOrd getEcuacionCircunferenciaOrd() {
		return ecuacionCircunferenciaOrd;
	}

	public void setEcuacionCircunferenciaOrd(EcuacionCircunferenciaOrd expresionOrdinaria) {
		this.ecuacionCircunferenciaOrd = expresionOrdinaria;
	}

	public EcuacionCircunferenciaGen getEcuacionCircunferenciaGen() {
		return ecuacionCircunferenciaGen;
	}

	public void setEcuacionCircunferenciaGen(EcuacionCircunferenciaGen expresionGeneral) {
		this.ecuacionCircunferenciaGen = expresionGeneral;
	}

	@Override
	public String toString() {
		return "EcuacionCircunferencia [expresionOrdinaria=" + ecuacionCircunferenciaOrd + ", expresionGeneral="
				+ ecuacionCircunferenciaGen + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ecuacionCircunferenciaGen == null) ? 0 : ecuacionCircunferenciaGen.hashCode());
		result = prime * result + ((ecuacionCircunferenciaOrd == null) ? 0 : ecuacionCircunferenciaOrd.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EcuacionCircunferencia other = (EcuacionCircunferencia) obj;
		if (ecuacionCircunferenciaGen == null) {
			if (other.ecuacionCircunferenciaGen != null)
				return false;
		} else if (!ecuacionCircunferenciaGen.equals(other.ecuacionCircunferenciaGen))
			return false;
		if (ecuacionCircunferenciaOrd == null) {
			if (other.ecuacionCircunferenciaOrd != null)
				return false;
		} else if (!ecuacionCircunferenciaOrd.equals(other.ecuacionCircunferenciaOrd))
			return false;
		return true;
	}

}
