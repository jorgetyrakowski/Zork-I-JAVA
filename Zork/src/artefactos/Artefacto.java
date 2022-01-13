package artefactos;

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
 * Esta clase representan un artefacto dentro del juego la cual tiene
 * un nombre, determinado peso, una descripcion y algunos artefactos
 * pueden ser recogidos por el jugador.
 * 
 */
public class Artefacto {
    public Artefacto(String nombre, int peso, String descripcion, boolean puedeRecogerse){
        this.NOMBRE = nombre;
        this.PESO = peso;
        this.descripcion = descripcion;
        this.puedeRecogerse = puedeRecogerse;
    }
    
    /**
     * Metodo encargado de verificar si un artefacto puede recogerse.
     * @return true si el artefacto puede recogerse.
     *         false si el artefacto no puede recogerse.
     */
    public boolean puedeRecogerse(){
        return this.puedeRecogerse;
    }
    
    /**
     * Metodo encargado de retornar el peso del artefacto.
     * @return un entero que representa el peso del artefacto.
     */
    public int getPeso(){
        return this.PESO;
    }
    
    /**
     * Metodo encargado de retornar el nombre de un artefacto.
     * @return un String con el nombre del artefacto.
     */
    public String getNombre(){
        return this.NOMBRE; 
    }
    
    /**
     * Metodo encargado de retornar la descripcion del artefacto.
     * @return un String con la descripcion del artefacto.
     */
    public String getDescripcion(){
        return this.descripcion;
    }
    
    // Un entero que almacena el peso de casa artefacto.
    private final int PESO;
    
    // Una cadena de caracteres que almacena el nombre del artefacto.
    private final String NOMBRE; 
    
    // Una cade de caracteres que almacena la descripcion del artefacto.
    private String descripcion;
    
    // Una variable booleana el indica si el artefacto puede ser recogido.
    private boolean puedeRecogerse;
}
