package juego;

import artefactos.Artefacto;
import personajes.Personaje;
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
 * Nombre del Alumno: Jorge Rafael Tyrakowski Santa Cruz
 *
 */

/**
 * Un cuarto representa una ubicacion en el escenario del juego. Aunque se llama
 * "cuarto", puede representar una ubicacion que esta adentro o afuera.
 *
 * Los cuartos estan intercontectados por medio de salidas. Cada cuarto tiene sus 
 * salidas y tambien la cantidad de salidas.
 *
 */
public class Cuarto {

    /**
     * Crea una habitacion descrita como "descripcion". Inicialmente, esta no
     * existe. "descripcion" es alguna cosa como "una cocina" o "la sala de
     * descanso"
     */
    public Cuarto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.salidas = new HashMap();
        this.artefactos = new HashMap();
        this.personajes = new HashMap();

    }

    /*
    * Define las salidas de la habitacion. Cada direccion te dirige a otra habitaccion
    * Funciona de modo a que cada salida posicionado en un indice de salidas, dirigira a 
      el cuarto posicionado en el mismo indice en el arreglo de cuartos, por ejemplo:
      la salida en el indice 0, dirigira a al cuarto en el indice 0
    
    * @para salidas un arreglo de Strings el cual define la salida 
    * @para cuartos un arreglo de Cuartos el cual define el cuarto al cual ira
     */
    public void setSalidas(String[] salidas, Cuarto cuartos[]) {
        for (int i = 0; i < salidas.length; i++) {
            if (cuartos[i] != null) {
                this.salidas.put(salidas[i], cuartos[i]);
            }
        }
    }

    /**
     * Metodo encargado de verificar y hacer funcionar el cuarto transportador magico.
     * cuando el jugador vaya a este cuarto sera transportado a uno de los cuartos que hay en todo el juego.
     * @param juego el juego.
     */
    public void verificaTransportadorMagico(Juego juego) {
        // verifica si el cuarto actual es el cuarto magico.
        String nombreTransportadorMagico = "transportadorMagico";
        if (juego.getCuartoActual().getNombre().equals(nombreTransportadorMagico)) {
            // transporta al jugador en un cuarto aleatorio
            this.IrCuartoAleatorio(juego);
        }
    }

    /**
     * Metodo encargado de transportar al jugador a un cuarto aleatorio.
     * @param juego el juego.
     */
    private void IrCuartoAleatorio(Juego juego) {
        // Se crea una collection con todos los cuartos y pasamos a un Array de Cuarto.
        Collection<Cuarto> collectionCuartos = juego.getCuartosColecction();
        Cuarto[] cuartos = collectionCuartos.toArray(new Cuarto[collectionCuartos.size()]);
        // creamos un numero aleatorio el cual decidira en que cuarto iremos.
        int numeroAleatorio = (int) (Math.random() * cuartos.length);
        // vamos al cuarto posicionado dentro del Array en el indice escogido por el numero aleatorio.
        juego.setCuartoActual(cuartos[numeroAleatorio]);
        this.verificaTransportadorMagico(juego);
    }

    /**
     * Metodo encargado de retornar una salida.
     * @param direccion para el nombre de la salida.
     * @return retorna el cuarto que representa la salida.
     */
    public Cuarto getSalida(String direccion) {
        return (Cuarto) this.salidas.get(direccion);
    }

    /**
     * Metodo encargado de añadir un persojae al cuarto.
     * @param nombre utilizamos el nombre del personaje como key.
     * @param personaje para el personaje que se quiere añadir.
     */
    public void addPersonaje(String nombre, Personaje personaje) {
        this.personajes.put(nombre, personaje);
    }

    /**
     * Metodo encargado de eliminar a un personaje del cuarto.
     * @param personaje para el personaje a ser eliminado.
     */
    public void removePersonaje(String personaje) {
        this.personajes.remove(personaje);
    }

    /**
     * Define los objetos que estaran dentro la habitacion
     * @param nombre el nombre del artefacto que representara su key.
     * @param artefacto para el artefacto que sera almacenado.
     */
    public void addArtefacto(String nombre, Artefacto artefacto) {
        this.artefactos.put(nombre, artefacto);
    }

    /**
     * Metodo encargado de remover un artefacto de el cuarto.
     * @param artefacto para el artefacto a remover.
     */
    public void removeArtefacto(Artefacto artefacto) {
        this.artefactos.remove(artefacto.getNombre());
    }

    /**
     * Metodo encargado de verificar si el cuarto contiene cierto artefacto.
     * @param nombre para el nombre del artefacto.
     * @return true si el cuarto contiene el artefacto.
     *         false si el cuarto no contiene el artefacto.
     */         
    public boolean tieneElArtefacto(String nombre) {
        return this.artefactos.containsKey(nombre);
    }

    /**
     * Metodo encargado de retornar el nombre del cuarto.
     * @return un String que representa el nombre del cuarto.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Metodo encargado de retornar la descripcion del cuarto.
     * @return un String de la descripcion del cuarto.
     */
    public String descripcionCorta() {
        return descripcion;
    }

    /**
     * Retorna una descripcion extensa de esta habitacion en la que estas,si hay
     * personajes en la habitacion, este te lo notificara.
     * @return un String con la descripcion.
     */
    public String descripcionLarga() {
        String nombreZeppelin = "Zeppelin";
        String nombreVortigaunt = "vortigaunt";
        String nombreMaquinaDelTiempo = "Maquina del tiempo";
        this.pasarLineas(1);
        
        String resultado = "Tu estas en " + descripcion + ".\n" + listarSalidas() + "\n" + artefactosDisponibles();
        if (this.estaElPersonaje(nombreZeppelin)) {
            resultado = resultado.concat("\n" + this.zeppelinAlaVista());
        }
        if (this.estaElPersonaje(nombreVortigaunt)) {
            resultado = resultado.concat("\n" + this.vortigauntAlaVista());
        }
        this.pasarLineas(1);
        return resultado;
    }

    /**
     * Retorna todas las posibles salidas del cuarto
     * @return un String que contiene las salidas del cuarto.
     */
    public String listarSalidas() {
        String resultado = "Salidas:";
        Set keys = salidas.keySet();
        for (Iterator iter = keys.iterator(); iter.hasNext();) {
            resultado += "     [" + iter.next() + "]     ";
        }
        return resultado;
    }

    /**
     * Retorna todos los artefactos que estan en el cuarto actual.
     * @return un String con los artefactos en el cuarto.
     */
    public String artefactosDisponibles() {
        String resultado = "Artefactos:";
        Set keys = this.artefactos.keySet();
        for (Iterator iter = keys.iterator(); iter.hasNext();) {
            resultado += "  [" + iter.next() + "]     ";
        }
        return resultado;
    }

    /**
     * Metodo encargado de notificarte que hay un Zeppelin en el aire.
     * @return 
     */
    private String zeppelinAlaVista() {
        String zeppelinAlaVista = "¡Aeronave Zeppelin a la vista!" + "\nDr. George: Hmm, estas aeronavaves tienen "
                + "una gran fuente de poder, si tan solo pudiera derribarla.. "; 
        return zeppelinAlaVista;
    }

    /**
     * Metodo encargado de notificarte que Vortigaunt esta en este cuarto.
     * @return un String que notifica que Vorgaunt esta aqui.
     */
    private String vortigauntAlaVista() {
        String vortigauntAlaVista = "Vortigaunt esta aqui.";
        return vortigauntAlaVista;
    }

    /**
     * Metodo encargado de imprimr las lineas indicadas.
     * @param lineas un entero que indica las lineas que imprimira.
     */
    private void pasarLineas(int lineas) {
        for (int i = 0; i < lineas; i++) {
            System.out.println("");
        }
    }

    /**
     * Metodo encargado de verificar si un personaje esta en el cuarto.
     * @param personaje para el personaje a verificar.
     * @return true si el personaje esta en el cuarto.
     *         false si el personaje no esta en el cuarto.
     */
    private boolean estaElPersonaje(String personaje) {
        return this.personajes.containsKey(personaje);
    }

    /**
     * Metodo encagado de retornar un artefacto dentro del cuarto.
     * @param artefacto para el nombre del artefacto que se representara como la key.
     * @return el artefacto si es que esta dentro del cuarto
     *         null si el artefacto no esta dentro del cuarto.
     */
    public Artefacto getArtefacto(String artefacto) {
        return (Artefacto) artefactos.get(artefacto);
    }

    // el nombre del cuarto
    private String nombre;

    // la descripcion del cuarto
    private String descripcion;

    // contiene la lista de salidas para este cuarto
    private HashMap salidas;

    // contiene la lista de artefactos para este cuarto
    private HashMap artefactos;

    // contiene los personajes para este cuarto
    private HashMap personajes;

}
