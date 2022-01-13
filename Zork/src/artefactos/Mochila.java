package artefactos;

import java.util.*;
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
 * Esta clase representa una mochila donde el jugador podra almacenar los distintos
 * artefactos que se encuentra en lo largo de su camino. 
 * 
 */
public class Mochila {
    public Mochila(){
       this.mochila = new HashMap();     
    }
    
    /**
     * Metodo encargado de guardar un artefacto a la mochila.
     * 
     * @param artefacto para el artefacto a ser guardado. 
     */
    public void guardarArtefacto(Juego juego, Artefacto artefacto){
        // Si el artefacto puede recogerse (Algunos no pueden) y la mochila puede cargar su peso
        if(artefacto.puedeRecogerse() && this.puedeSostenerlo(artefacto)){
            this.mochila.put(artefacto.getNombre(), artefacto);
            this.setPeso(this.getPeso() + artefacto.getPeso());
            juego.getCuartoActual().removeArtefacto(artefacto);
            juego.imprimir("\nHas recogido: " + artefacto.getDescripcion());
        } else {
            juego.imprimir("Ya no puedo contener mas peso.");
        }
    }
    
    /**
     * Metodo encargado de verficar si la mochila puede cargar el peso de un artefacto.
     * @param artefacto el artefacto a verificar si es posible ser cargado en la mochila.
     * @return true si la mochila puede cargar  su peso
     *         false si la mochila no puede cargar su peso.
     * 
     */
    private boolean puedeSostenerlo(Artefacto artefacto){
        return this.getCapacidad() >=(this.getPeso()+ artefacto.getPeso());
    }
 
    
    /**
     * Metodo encargado de retornar un artefacto desde la mochila.
     * @param artefacto para la key del artefacto a retornar
     * @return el artefacto dentro de la mochila.
     */
    public Artefacto getArtefacto(String artefacto){
        return (Artefacto)this.mochila.get(artefacto);
    }
    
    /**
     * Metodo encargado de remover un artefacto de la mochila.
     * @param artefacto para el artefactoa ser removido.
     */
    public void sacarArtefacto(Artefacto artefacto){
        this.mochila.remove(artefacto.getNombre());
        this.setPeso(this.getPeso() - artefacto.getPeso());
    }
    
    /**
     * Metodo encargado de verificar si un artefacto esta dentro de la mochila.
     * @param nombreArtefacto nombre del artefacto que representa la key.
     * @return true si la mochila contiene el artefacto.
     *         false si la mochila no contiene el artefacto.
     */
    public boolean tieneElArtefacto(String nombreArtefacto){
        return this.mochila.containsKey(nombreArtefacto);
    }
    
    /**
     * Metodo encargado de retornar la capacidad maxima de la mochila.
     * @return la capacidad maxima de la mochila.
     */
    public int getCapacidad(){
        return this.CAPACIDAD;
    }
    
    /**
     * Metodo encargado de retornar el peso actual de la mochila.
     * @return peso actual de la mochila.
     */
    public int getPeso(){
        return this.peso;
    }
    
    /**
     * Metodo encargado de modificar el peso de la mochila.
     * @param newPeso el nuevo peso de la mochila.
     */
    public void setPeso(int newPeso){
        this.peso = newPeso;
    }
    
    // Un HashMap para guardar los artefactos en la mochila.
    private HashMap mochila;
    
    // Una constante que define la capacidad maxima de la mochila.
    private final int CAPACIDAD = 10;
    
    // Un enntero que representa el peso actual de la mochila.
    private int peso;
}
