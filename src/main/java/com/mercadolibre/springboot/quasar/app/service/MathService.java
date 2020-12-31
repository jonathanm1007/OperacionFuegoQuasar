package com.mercadolibre.springboot.quasar.app.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mercadolibre.springboot.quasar.app.modelo.api.Posicion;
import com.mercadolibre.springboot.quasar.app.modelo.ecuacion.EcuacionCircunferencia;
import com.mercadolibre.springboot.quasar.app.modelo.ecuacion.EcuacionCircunferenciaGen;
import com.mercadolibre.springboot.quasar.app.modelo.ecuacion.EcuacionCuadratica;
import com.mercadolibre.springboot.quasar.app.modelo.ecuacion.EcuacionLineal;
import com.mercadolibre.springboot.quasar.app.modelo.util.MathUtil;
import com.mercadolibre.springboot.quasar.app.modelo.util.constantes.ExcepcionEnum;
import com.mercadolibre.springboot.quasar.app.modelo.util.excepcion.FuegoQuasarException;

/**
 * 
 * Servicio de negocio que permite realizar cálculos de ecuaciones matemáticas.
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
@Service
public class MathService {
	/**
	 * 
	 * Método de Calcula los puntos de intersección entre dos circunferencias
	 * 
	 * @author: Jonatan Alexander Méndez
	 * 
	 * @version: 0.0.1-SNAPSHOT
	 * 
	 * @param ecuacionCircunferencia1 Primera circunferencia
	 * 
	 * @param ecuacionCircunferencia2 Segunda circunferencia
	 * 
	 * @return Mapa que contiene los dos puntos de intersección
	 * 
	 */
	public Map<Integer, Posicion> obtenerPuntosInterseccion(EcuacionCircunferencia ecuacionCircunferencia1,
			EcuacionCircunferencia ecuacionCircunferencia2) throws FuegoQuasarException {
		Map<Integer, Posicion> mapaPuntosInterseccion = new HashMap<Integer, Posicion>();
		EcuacionCuadratica ecuacionCuadratica = calcularEcuacionCuadratica(ecuacionCircunferencia1,
				ecuacionCircunferencia2);
		double[] raicesY = calcularRaicesCuadratica(ecuacionCuadratica);
		EcuacionCuadratica ecuacionCuadTerminosX = calcularEcuacionCuadraticaTerminosX(ecuacionCircunferencia1,
				raicesY[0]);
		double[] valoresEcuacion1 = calcularRaicesCuadratica(ecuacionCuadTerminosX);
		ecuacionCuadTerminosX = calcularEcuacionCuadraticaTerminosX(ecuacionCircunferencia2, raicesY[0]);
		double[] valoresEcuacion2 = calcularRaicesCuadratica(ecuacionCuadTerminosX);
		mapaPuntosInterseccion.put(1, obtenerPuntoValido(raicesY[0], valoresEcuacion1, valoresEcuacion2));
		ecuacionCuadTerminosX = calcularEcuacionCuadraticaTerminosX(ecuacionCircunferencia1, raicesY[1]);
		valoresEcuacion1 = calcularRaicesCuadratica(ecuacionCuadTerminosX);
		ecuacionCuadTerminosX = calcularEcuacionCuadraticaTerminosX(ecuacionCircunferencia2, raicesY[1]);
		valoresEcuacion2 = calcularRaicesCuadratica(ecuacionCuadTerminosX);
		mapaPuntosInterseccion.put(2, obtenerPuntoValido(raicesY[1], valoresEcuacion1, valoresEcuacion2));
		return mapaPuntosInterseccion;
	}

	/**
	 * 
	 * Método de Valida si un punto determinado satisface la ecuación de una
	 * circunferencia
	 * 
	 * @author: Jonatan Alexander Méndez
	 * 
	 * @version: 0.0.1-SNAPSHOT
	 * 
	 * @param ecuacionReemplazo Ecuación de la circunderencia
	 * 
	 * @param posicion          Posición (x,y) a validar
	 * 
	 * @return Indicador true o false de si se satisface la igualdad.
	 * 
	 */
	public boolean validarIgualdadCircunferencia(EcuacionCircunferencia ecuacionReemplazo, Posicion posicion)
			throws FuegoQuasarException {
		double izquierdaIgualdad = (Math
				.pow((posicion.getX() - ecuacionReemplazo.getEcuacionCircunferenciaOrd().getA()), 2))
				+ (Math.pow((posicion.getY() - ecuacionReemplazo.getEcuacionCircunferenciaOrd().getB()), 2));
		double derechaIgualdad = Math.pow(ecuacionReemplazo.getEcuacionCircunferenciaOrd().getR(), 2);
		return izquierdaIgualdad == derechaIgualdad;
	}

	private EcuacionCircunferenciaGen restarEcuaciones(EcuacionCircunferenciaGen expresionGeneral1,
			EcuacionCircunferenciaGen expresionGeneral2) throws FuegoQuasarException {
		EcuacionCircunferenciaGen expresionReducida = new EcuacionCircunferenciaGen();
		expresionReducida.setA(expresionGeneral1.getA() - expresionGeneral2.getA());
		expresionReducida.setB(expresionGeneral1.getB() - expresionGeneral2.getB());
		expresionReducida.setC(expresionGeneral1.getC() - expresionGeneral2.getC());
		expresionReducida.setD(expresionGeneral1.getD() - expresionGeneral2.getD());
		expresionReducida.setE(expresionGeneral1.getE() - expresionGeneral2.getE());
		return expresionReducida;
	}

	private EcuacionCuadratica calcularEcuacionCuadratica(EcuacionCircunferencia ecuacionCircunferencia1,
			EcuacionCircunferencia ecuacionCircunferencia2) throws FuegoQuasarException {
		EcuacionCircunferenciaGen ecuacionReducida = restarEcuaciones(
				ecuacionCircunferencia1.getEcuacionCircunferenciaGen(),
				ecuacionCircunferencia2.getEcuacionCircunferenciaGen());
		double aRecta = -1 * (ecuacionReducida.getD() / ecuacionReducida.getC());
		double bRecta = -1 * (ecuacionReducida.getE() / ecuacionReducida.getC());
		EcuacionLineal rectaEjeRadical = new EcuacionLineal(aRecta, bRecta);
		double a = 1 + Math.pow(rectaEjeRadical.getA(), 2);
		double b = (2 * rectaEjeRadical.getA() * rectaEjeRadical.getB())
				+ ecuacionCircunferencia1.getEcuacionCircunferenciaGen().getD()
				+ (ecuacionCircunferencia1.getEcuacionCircunferenciaGen().getC() * aRecta);
		double c = Math.pow(rectaEjeRadical.getB(), 2) + ecuacionCircunferencia1.getEcuacionCircunferenciaGen().getE()
				+ (ecuacionCircunferencia1.getEcuacionCircunferenciaGen().getC() * rectaEjeRadical.getB());
		return new EcuacionCuadratica(a, b, c);
	}

	private EcuacionCuadratica calcularEcuacionCuadraticaTerminosX(EcuacionCircunferencia ecuacionCircunferencia,
			double raizY) {
		double a = ecuacionCircunferencia.getEcuacionCircunferenciaGen().getA();
		double b = ecuacionCircunferencia.getEcuacionCircunferenciaGen().getC();
		double c = (ecuacionCircunferencia.getEcuacionCircunferenciaGen().getB() * (Math.pow(raizY, 2))
				+ ecuacionCircunferencia.getEcuacionCircunferenciaGen().getD() * raizY
				+ ecuacionCircunferencia.getEcuacionCircunferenciaGen().getE());
		return new EcuacionCuadratica(a, b, c);
	}

	private Posicion obtenerPuntoValido(double raizY, double[] valoresEcuacion1, double[] valoresEcuacion2) {
		double valorValido = 0;
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++) {
				if (valoresEcuacion1[i] != valoresEcuacion2[j])
					continue;
				valorValido = valoresEcuacion1[i];
				break;
			}
		return new Posicion(valorValido, raizY);
	}

	private double[] calcularRaicesCuadratica(EcuacionCuadratica ecuacionCuadratica) throws FuegoQuasarException {
		double[] raices = new double[2];
		double discriminante = Math.sqrt(
				Math.pow(ecuacionCuadratica.getB(), 2) - (4 * ecuacionCuadratica.getA() * ecuacionCuadratica.getC()));
		if (discriminante < 0)
			throw new FuegoQuasarException(ExcepcionEnum.NO_EXISTEN_SOLUCIONES_CUADRATICA);
		if (discriminante == 0) {
			raices[0] = (-1 * ecuacionCuadratica.getB()) / (2 * ecuacionCuadratica.getA());
			raices[1] = (-1 * ecuacionCuadratica.getB()) / (2 * ecuacionCuadratica.getA());
		} else {
			raices[0] = MathUtil.redondearNumero(
					((-1 * ecuacionCuadratica.getB()) + discriminante) / (2 * ecuacionCuadratica.getA()));
			raices[1] = MathUtil.redondearNumero(
					((-1 * ecuacionCuadratica.getB()) - discriminante) / (2 * ecuacionCuadratica.getA()));
		}
		return raices;
	}

}
