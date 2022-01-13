
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
 * Este comando esta encargado de hacer que interactues con el Vortigaunt
 */
public class ComandoHablar extends ComandoAbstracto{
    public boolean ejecutar(Juego juego){
        if (getPalabras().size() < 2) {
            throw new IllegalArgumentException("Faltan palabras");
        }   
        String personaje = (String) this.getPalabras().get(1);
        juego.conversarVortigaunt(personaje);     
        return true;
    }
    
}
