package personajes;

import artefactos.Artefacto;
import juego.Juego;

/*
 * Universidad Nacional de Itapua.
 * Proyecto Zork.
 *
 * Autor Original: Michael Kolling, Universidad de Monash
 * Version: 1.1
 * Date: March 2000
 * Copyright (c) Michael Kolling
 *
 * Nombre del Alumno: Jorge Rafael Tyrakowski Santa Cruz.
 */

/**
 * Esta clase representa a un personaje aeronave zeppelin el cual
 * contiene una bateria que sera esencial para la trama del juego
 * este recorrera por ciertos cuartos en el juego en donde el jugador
 * tendra que encontrarlo y posteriormente derribarlo.
 */
public class Zeppelin extends Personaje {
    public Zeppelin(String nombre, String descripcion) {
        super(nombre, descripcion);
        this.bateria = new Artefacto("bateria", 5, "Bateria de Zeppelin", true);
    }

    /***
     * Metodo encargado de mover al Zeppelin de un cuarto a otro.
     * @param juego el juego.
     */
    public void irA(Juego juego) {
        // Abandona el cuarto anterior.
        this.salirDelCuartoAnterior(juego);
        
        // Escoge su direccion de manera aleatoria.
        String direccion = this.escogerDireccion();
        
        // Se mueve hasta el cuarto que tiene la direccion.
        juego.getCuarto(direccion).addPersonaje(this.getNombre(), this);
    }
    
    /**
     * Metodo encargado hacer que el Zeppelin abandone el cuarto en el cual esta.
     * @param juego para el juego.
     */
    private void salirDelCuartoAnterior(Juego juego) {
        if (this.getUltimaLocalizacion() != null) {
            juego.getCuarto(ultminaLocalizacion).removePersonaje(this.getNombre());
        }
    }
    /**
     * Metodo encargado de derribar el Zeppelin 
     * @param juego el juego.
     */
    public void derribarZeppelin(Juego juego) {
        // El zeppelin soltara la bateria
        juego.getCuartoActual().addArtefacto(this.soltarBateria().getNombre(), this.soltarBateria());
        // El zeppelin deja de estar en el cuarto actual
        this.salirseDelCuartoActual(juego);
        juego.imprimir("\nHas derribado a la aeronave Zeppelin.. "
                      +"\nDr. George: parece ser que su fuente de energia esta en buen estado..");
        juego.imprimir(juego.getCuartoActual().artefactosDisponibles());
    }

    /**
     * Metodo encargado de hacer que el Zeppelin abandone el cuarto en donde se encuentra el jugador.
     * @param juego para el juego.
     */
    private void salirseDelCuartoActual(Juego juego) {
        juego.getCuartoActual().removePersonaje(this.getNombre());
    }
    /**
     * Metodo encargado de retornar una direccion aleatoria entre los cuartos en
     * el que el Zeppelin se puede dirigir, son en total 5
     * cuartos: Estacion de tren, La fuente, El estacionamiento, El parque.
     * @return Un string con la direccion del cuarto.
     */
    private String escogerDireccion() {
        // Inicializamos las posibles direcciones.
        String direccion = "";
        String cuartoEstacion = "estacion";
        String cuartoFuente = "fuente";
        String cuartoEstacionamiento = "estacionamiento";
        String cuartoParque = "parque";
        // Inicializamos un numero aleatorio el cual decidira que dirrecion retornara.
        final int POSIBILIDADES_ALEATORIAS = 4;
        int selector = (int) (Math.random() * POSIBILIDADES_ALEATORIAS);
        
        // Se escoge la direccion.
        switch (selector) {
            case 0:
                direccion = cuartoEstacion;
                break;
            case 1:
                direccion = cuartoFuente;
                break;
            case 2:
                direccion = cuartoEstacionamiento;
                break;
            case 3:
                direccion = cuartoParque;
                break;
        }
        this.setUltimaLocalizacion(direccion);
        return direccion;
    }

    /**
     * Metodo encargado de retornar la bateria del Zeppelin
     * @return artefacto bateria del zeppelin.
     */
    public Artefacto soltarBateria() {
        return bateria;
    }

    /**
     * Metodo encargado de añadorla un artefacto bateria al zeppelin.
     * @param baterias el artefacto bateria.
     */
    public void setBaterias(Artefacto baterias) {
        this.bateria = baterias;
    }

    /**
     * Metodo encargado de modificar la ultima localizacion del Zeppelin.
     * @param direccion parala nueva localizacion del Zeppelin
     */
    public void setUltimaLocalizacion(String direccion) {
        this.ultminaLocalizacion = direccion;
    }

    /**
     * Metodo encargado en retornar la ultima localizacion del Zeppelin.
     * @return un String de la ultima localizacion del Zeppelin.
     */
    public String getUltimaLocalizacion() {
        return this.ultminaLocalizacion;
    }

    // Artefacto que almacena la baria del Zeppelin
    private Artefacto bateria;
    
    // Una cadena de caracteres que almacena la ultima localizacion del Zeppelin.
    private String ultminaLocalizacion;
}
