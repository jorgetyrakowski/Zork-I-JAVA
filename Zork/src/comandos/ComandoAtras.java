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
 * Comando encargado de cambiar el cuarto actual por la del cuarto anterior del jugador.
 */
public class ComandoAtras extends ComandoAbstracto {
    
    public boolean ejecutar(Juego juego){
        juego.irAtras();
        return true;     
    }
    
}
