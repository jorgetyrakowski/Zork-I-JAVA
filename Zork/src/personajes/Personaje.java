package personajes;

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
 * Esta clase representa a los personajes que son personas o animales o monstruos
 * que se mueven por los cuartos de forma independiente.
 */
public class Personaje {
    public Personaje(String nombre, String personaje){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
       
    /**
     * Metodo encargado de retornar el nombre del personaje.
     * @return un String con el nombre del personaje.
     */
    public String getNombre(){
        return this.nombre;
    }
    
    /**
     * Metodo encargado de retornar una descripcion del personaje.
     * @return un String con la descripcion del personaje.
     */
    public String getDescripcion(){
        return this.descripcion;
    }
    
    // Una cadena de caracteres que almacena el nombre del personaje.
    private String nombre;
    
    // Una cadena de caracteres que almacena la descripcion del personaje.
    private String descripcion;
  
}
