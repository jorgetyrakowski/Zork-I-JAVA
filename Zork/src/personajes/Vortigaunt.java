package personajes;

import artefactos.Artefacto;
import juego.Juego;
import artefactos.MaquinaDelTiempo;

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
 * Esta clase representa a un personaje Vorigaunt es de una raza alienigena y se
 * encarga de reparar artefactos en las instalaciones de Black Mesa. En el juego
 * nos ayudara a reparar la Maquina del tiempo.
 */
public class Vortigaunt extends Personaje {

    public Vortigaunt(String nombre, String descripcion) {
        super(nombre, descripcion);
        this.direccionActual = "taller";
        this.bateria = null;
    }

    /**
     * Metodo encargado de hacer que el Vortigaunt coloque las baterias a la
     * Maquina del Tiempo.
     *
     * @param timeMachine para la maquina del tiempo
     * @param juego para el juego.
     */
    public void colocarBateriaMaquinaDelTiempo(MaquinaDelTiempo timeMachine, Juego juego) {
        timeMachine.introducirFuenteDeEnergia(this.conectarBateria());
        this.removerBateria();
        juego.imprimir("Vortigaunt: La bateria ha sido conectada con exito.");
        juego.imprimir("Dr George debes intentar encenderlo, de otro modo no sabremos si es suficiente.");
    }
    /**
     * Metodo que se encarga de que Vortigaunt vaya hasta el laboratorio.
     * @param juego para el juego.
     */
    public void irHastaLaboratorio(Juego juego) {
        String nombreLaboratorio = "laboratorio";

        // Vortigaunt debe salirse del taller para ir al laboratorio
        this.salirDelTaller(juego);

        // Lo movemos hasta el laboratorio
        juego.getCuarto(nombreLaboratorio).addPersonaje(this.getNombre(), this);

        // actualizamos la direccion acutal
        this.setDireccionActual(nombreLaboratorio);
    }

    /**
     * Metodo que se encarga de que Vortigaunt abandone el taller.
     * @param juego 
     */
    private void salirDelTaller(Juego juego) {
        String nombreTaller = "taller";
        if (this.getDireccionActual().equals(nombreTaller)) {
            juego.getCuarto(nombreTaller).removePersonaje(this.getNombre());
        }
    }

    /**
     * Metodo encargado de representar la conversacion con el Dr. George en el taller.
     * @param juego para el juego.
     */
    public void conversarEnTaller(Juego juego) {
        juego.pasarLineas(1);
        juego.imprimir("Vortigaunt: Buenos dias Dr. George.");
        juego.imprimir("Dr. George: Vortigaunt.. necesito que me ayudes a encender la maquina del tiempo."
                + "\nparece ser que no tiene la fuente de poder necesaria.");
        juego.imprimir("Vortigaunt: Hmm, puedo arreglarlo, pero necesitare baterias, te estare esperando en laboratorio.");

    }

    /**
     * Metodo encargado de representar la conversacion con el Dr. George en el laboratorio.
     * @param juego 
     */
    public void conversacionEnLaboratorio(Juego juego) {
        juego.pasarLineas(1);
        juego.imprimir("Vortigaunt: Dr. George necesito mas baterias para reparar la Maquina del tiempo.");
        juego.imprimir("Dr. George: estoy en eso..");
    }

    /**
     * Metodo encargado de retornar la bateria que carga el Vortigaunt.
     * @return artefacto bateria.
     */
    public Artefacto conectarBateria() {
        return (Artefacto) this.bateria;
    }

    /**
     * Metodo encargado de almacenar la bateria.
     * @param bateria para el artefacto bateria a ser almacenado.
     */
    public void agarrarBateria(Artefacto bateria) {
        this.bateria = bateria;
    }

    /**
     * Metodo encargado de hacer que el Vortigaunt no este almacenando baterias.
     */
    public void removerBateria() {
        this.bateria = null;
    }

    /**
     * Metodo encargado de verificar si el Vortigaunt esta en la direccion dada.
     * @param direccion para la direccion a verificar.
     * @return true si Vortigaunt esta en la direccion dada.
     *         false si Vortigaunt no se encuentra en la direccion dada.
     */
    public boolean estaEn(String direccion) {
        return this.direccionActual == direccion;
    }

    /**
     * Metodo encargado de retornar la direccion actual de Vortigaunt.
     * @return un String de la direccion actual.
     */
    public String getDireccionActual() {
        return this.direccionActual;
    }

    /**
     * Metodo encargado de modificar la direccion actual de Vortigaunt.
     * @param direccion para la nueva direccion.
     */
    public void setDireccionActual(String direccion) {
        this.direccionActual = direccion;
    }
    
    // Una cadena de caracteres que almacena la direccion actual.
    private String direccionActual;
    
    // Un artefacto que almacenara una bateria.
    private Artefacto bateria;
}
