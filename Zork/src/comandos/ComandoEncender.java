package comandos;

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
 *
 */

/**
 * Este comando esta encargado de encender la maquina del tiempo.
 */
public class ComandoEncender extends ComandoAbstracto {
    public boolean ejecutar(Juego juego){
         if (getPalabras().size() < 2) {
             
            throw new IllegalArgumentException("Faltan palabras");
        }             
        return juego.viajarEnElTiempo();
    }
    
}
