package comandos;

import juego.Juego;
import java.util.*;

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
 * Esta clase implementa la ayuda para el juego
 */
public class ComandoAyuda extends ComandoAbstracto {

    /**
     * Imprime a pantalla alguna informacion de ayuda.
     *
     * Aqui nosotros imprimimos algo estupido, mensajes cripticos y
     * una lista de los comandos disponibles.
     */
    public boolean ejecutar(Juego juego) {
        juego.pasarLineas(1);
        juego.pasarLineas(1);
        juego.imprimir("Eres un cientifico y tu mision es viajar en el tiempo.");
        juego.imprimir("Para poder viajar necesitas hablar con Vontigaunt y reparar la Maquina del tiempo.");
        juego.imprimir("te encuentras en las instalaciones Black Meza");
        juego.pasarLineas(1);
        juego.imprimir("Los comandos disponibles son:");

        // pasarLineas todos los comandos
        FabricaDeComandos fabrica = new FabricaDeComandos();
        Collection listaComandos = fabrica.comandosConocidos();
        for (Iterator iter = listaComandos.iterator(); iter.hasNext(); ) {
 
            juego.imprimirCont("["+(String) iter.next()+"]  ");
        }
        juego.pasarLineas(1);

        // no parar el juego
        return true;
    }

}
