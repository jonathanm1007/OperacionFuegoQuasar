package com.mercadolibre.springboot.quasar.app;

import java.io.FileNotFoundException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

/**
 * 
 * Clase principal de la aplicación de Springboot, con la cual se inicia el hilo
 * de ejecución
 * 
 * @author: Jonatan Alexander Méndez
 * 
 * @version: 0.0.1-SNAPSHOT
 * 
 * 
 */
@SpringBootApplication
public class OperacionFuegoQuasarApplication {

	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		SpringApplication.run(OperacionFuegoQuasarApplication.class, args);
	}

}
