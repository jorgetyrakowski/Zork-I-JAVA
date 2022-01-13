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
 * Este comando se encarga de imprimir todas las posibles salidas
 * que tiene el cuarto en donde te encuentras.
 */
public class ComandoSalidas extends ComandoAbstracto {
    public boolean ejecutar(Juego juego){
        juego.imprimir("Las salidas disponibles son: ");
        juego.imprimirSalidas();
        return true;
    }
    
}
