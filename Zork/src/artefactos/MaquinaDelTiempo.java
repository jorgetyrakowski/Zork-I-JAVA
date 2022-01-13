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
 * Esta clase representa una Maquina del tiempo dentro del juego
 * el cual para encenderse necesita dos artefactos llamados baterias
 */
public class MaquinaDelTiempo extends Artefacto{
    public MaquinaDelTiempo(String nombre,int peso, String descripcion, boolean puedeRecogerse) {
        super(nombre, peso, descripcion, puedeRecogerse);
        this.baterias = new Artefacto[CAPACIDAD];
        this.cantidadDeFuentes = 0;
        
    }
    
    /**
     * Metodo encargado de encender la Maquina del tiempo.
     * @return true si esta listo para encenderse
     *         false si aun no esta listo.
     */
    public boolean encender(){
        return this.estaListo();
    }

    /**
     * Metodo encargado de cargar las baterias en la maquina del tiempo.
     * @param bateria el artefacto bateria que sera introducida en la maquina del tiempo.
     */
    public void introducirFuenteDeEnergia(Artefacto bateria) {
        this.baterias[this.getCantidadDeFuentesDeEnergia()] = bateria;
         this.cantidadDeFuentes++;      
    }

    /**
     * Metodo encargado de verificar si la Maquina del tiempo esta lista para encenderse
     * lo hace si es que la Maquina contiene dos artefactos.
     * @return true si tiene dos artefactos baterias.
     *         false si aun no tiene los dos artefactos baterias.
     */
    public boolean estaListo() {
        return this.baterias[CAPACIDAD-1] != null;   
    }

    /**
     * Metodo encargado de retornar la cantidad de baterias que tiene la Maquina del tiempo.
     * @return retorna un entero que indica la cantidad de baterias que tiene.
     */
    public int getCantidadDeFuentesDeEnergia() {
        return this.cantidadDeFuentes;
    }
    
    // Un arreglo de Artefactos donde seran almacenadas las baterias.
    private Artefacto[] baterias;
    
    // Un entero constante que representa la capacidad de baterias que puede llevar.
    private int CAPACIDAD = 2;
    
    // Un entero que representa la cantidad de baterias que contiene.
    private int cantidadDeFuentes;
}
