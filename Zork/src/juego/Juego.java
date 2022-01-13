package juego;

import artefactos.Artefacto;
import artefactos.Mochila;
import artefactos.MaquinaDelTiempo;
import personajes.Personaje;
import personajes.Vortigaunt;
import personajes.Zeppelin;
import comandos.Comando;
import java.util.Collection;
import java.util.HashMap;

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
 * Esta clase es la principal para la aplicacion "Zork". Zork es un juego de
 * aventuras, simple, y basado en texto.
 *
 * Para jugarlo, se debe crear una instancia de esta clase y llamar el metodo
 * "jugar"
 *
 * Esta clase crea inicializa a todas las otras, aqui empieza todo: crea todos
 * los Cuartos, crea los parsers (objetos que interpretan texto) y comienza el
 * juego. Tambien evalua los comandos que devuelve el parser.
 */
public class Juego {

    /**
     * Esta es la clase que se encarga de crear el juego con todos sus
     * elementos.
     */
    public Juego() {
        this.cuartos = new HashMap();
        this.personajes = new HashMap();
        parser = new Parser();
        mochila = new Mochila();
        crearElementosDelJuego();

    }

    /**
     * Este metodo se encarga de crear todas las habitaciones con sus
     * respectivas salidas, tambien se encarga de crear los artefactos y los
     * personajes que habitaran en el juego.
     */
    private void crearElementosDelJuego() {
        // Creamos todos los cuartos.
        Cuarto centro = new Cuarto("centro", "es el centro de la instalacion Black Meza");
        Cuarto laboratorio = new Cuarto("laboratorio", "es el laboratorio en donde se encuentran los experimentos de la instalacion Black Meza");
        Cuarto deposito = new Cuarto("deposito", "es el deposito de articulos utilizados en el laboratorio");
        Cuarto taller = new Cuarto("taller", "es el taller en donde se reparan las maquinas de la instalacion Black Meza");
        Cuarto transportadorMagico = new Cuarto("transportadorMagico", "es un cuarto reservado para un experimento de transportacion");
        Cuarto pasillos = new Cuarto("pasillos", "son los extensos pasillos de la instalacion Black Meza");
        Cuarto fuente = new Cuarto("fuente", "es una fuente en las afueras de la instalacion Black Meza");
        Cuarto estacion = new Cuarto("estacion", "es la estacion del tren que dirije a ciudadela");
        Cuarto estacionamiento = new Cuarto("estacionamiento", "es el estacionamiento de la instalacion Black Meza");
        Cuarto parque = new Cuarto("parque", "es el parque de la instalacion Black Meza");

        /*
        Inicializamos todas las salidas, los hacemos utilizando un arreglo de salidas y un arreglo
        de cuartos, de modo a que cada indice de salidas pertenece al mismo indice de cuartos.
         */
        centro.setSalidas(new String[]{"al laboratorio", "al taller", "subir escaleras hasta pasillos", "bajar por el ascensor"},
                          new Cuarto[]{laboratorio, taller, pasillos, deposito});
        deposito.setSalidas(new String[]{"subir por el ascensor"},
                            new Cuarto[]{centro});
        taller.setSalidas(new String[]{"al centro", "al transportador magico"},
                          new Cuarto[]{centro, transportadorMagico});
        transportadorMagico.setSalidas(new String[]{"al taller"},new Cuarto[]{taller} );
        laboratorio.setSalidas(new String[]{"al centro"},
                               new Cuarto[]{centro});
        pasillos.setSalidas(new String[]{"bajar escaleras hasta centro", "a estacion de tren", "a la fuente", "al estacionamiento"},
                            new Cuarto[]{centro, estacion, fuente, estacionamiento});
        estacion.setSalidas(new String[]{"a pasillos", "al parque"},
                            new Cuarto[]{pasillos, parque});
        fuente.setSalidas(new String[]{"a pasillos", "al parque"},
                          new Cuarto[]{pasillos, parque});
        estacionamiento.setSalidas(new String[]{"a pasillos", "al parque"},
                                   new Cuarto[]{pasillos, parque});
        parque.setSalidas(new String[]{"a estacion de tren", "a la fuente", "al estacionamiento"},
                         new Cuarto[]{estacion, fuente, estacionamiento});

        // Creamos los artefactos y los guardamos en los cuartos
        Artefacto gun = new Artefacto("Gravity Gun", 5, "Manipulador de Campos de Energía Cero, fue originalmente diseñado para manipular materiales peligrosos.. " + "\npero la mayoria de veces se utliza para levantar objetos pesados.", true);
        this.timeMachine = new MaquinaDelTiempo("Maquina del tiempo", 500, "Es una maquina del tiempo creada a finales del año 2060", false);
        deposito.addArtefacto("Gravity Gun", gun);
        laboratorio.addArtefacto("Maquina del tiempo", timeMachine);

        // Creamos los personajes y les damos una localizacion.
        Zeppelin zeppelin = new Zeppelin("Zeppelin", "Aero nave Zeppelin");
        estacion.addPersonaje(zeppelin.getNombre(), zeppelin);
        Vortigaunt vortigaunt = new Vortigaunt("vortigaunt", "Es el encargado del mantenimiento de los mas sostificados artefactos");
        taller.addPersonaje(vortigaunt.getNombre(), vortigaunt);

        // Empezaremos el juego dentro del laboratorio
        this.cuartoActual = laboratorio;

        // Almacenamos los cuartos, las keys son representadas por el nombre de los cuartos
        this.cuartos.put(centro.getNombre(), centro);
        this.cuartos.put(laboratorio.getNombre(), laboratorio);
        this.cuartos.put(deposito.getNombre(), deposito);
        this.cuartos.put(taller.getNombre(), taller);
        this.cuartos.put(transportadorMagico.getNombre(), transportadorMagico);
        this.cuartos.put(pasillos.getNombre(), pasillos);
        this.cuartos.put(fuente.getNombre(), fuente);
        this.cuartos.put(estacion.getNombre(), estacion);
        this.cuartos.put(estacionamiento.getNombre(), estacionamiento);
        this.cuartos.put(parque.getNombre(), parque);

        // almacenamos los personajes, las keys son representadas por el nombre de los personajes
        this.personajes.put(zeppelin.getNombre(), zeppelin);
        this.personajes.put(vortigaunt.getNombre(), vortigaunt);
    }

    /**
     * Rutina principal: jugar. Itera hasta el fin del juego..
     */
    public void jugar() {
        imprimirBienvenida();

        // Jugar hasta que un comando me diga que ya no quiere jugar mas
        boolean continuar = true;
        while (continuar) {
            Comando comando = parser.getComando();
            continuar = comando.ejecutar(this);
        }
        this.pasarLineas(1);
        this.imprimir("El total de movimientos es de: " + this.getMovimientos());
        this.imprimir("Continuara.. [ZORK I]");
    }

    /**
     * Imprime la bienvenida del juego.
     */
    private void imprimirBienvenida() {
        this.pasarLineas(1);
        this.imprimir("Centro de Investigaciones Black Mesa");
        this.imprimir("Tú eres un cientifico, habias sido parte de un proyecto denominado ”Hyperspace”");
        this.imprimir("el cual desafortunadamente desencadeno una secuencia de hechos catastroficos en el planeta tierra..");
        this.imprimir("te encuentras atrapado en un ciclo, viajando en una linea temporal alterna tratando de evitar dichos hechos.");
        this.pasarLineas(1);
        this.imprimir(cuartoActual.descripcionLarga());
        this.pasarLineas(1);
        this.imprimir("\nDr. George: no debo puedo mas tiempo.. tengo que viajar al pasado, tengo que");
        this.imprimir("evitar que todo esto ocurra, cumplire mi mision.. ");
         
    }

    /**
     * Intenta ir en una direccion. Si esta fue una salida, entra a otra
     * habitacion, en caso contrario imprime un mensaje de error.
     *
     * @param direccion la salida a la que nos dirigiremos.
     */
    public void irA(String direccion) {
        if (null == direccion) {
            // si no hay direccion no sabemos a donde ir
            this.imprimir("Dr. George: ¿Ir donde?");
            return;
        }
        
        // Intenta salir del cuarto
        Cuarto siguienteCuarto = cuartoActual.getSalida(direccion);
        if (null == siguienteCuarto) {
            this.imprimir("\nDr. George: Hmm... no puedo ir para allí... algo me esta bloqueando.");
        } else {
            this.setCuartoAnterior(this.getCuartoActual());
            this.setCuartoActual(siguienteCuarto);
            this.getCuartoActual().verificaTransportadorMagico(this);
            this.imprimir(this.cuartoActual.descripcionLarga());
            // Aumenta un movimiento y mueve a los personajes    
            this.movimientos++;
            this.desplazarZeppelin();
        }
    }

    /**
     * Metodo encargado retroceder al cuarto por donde viniste.
     */
    public void irAtras() {
        // Si aun no has hecho un movimiento, no puedes ir hacia atras.
        if (null != this.cuartoAnterior) {
            this.cuartoActual = this.cuartoAnterior;
            this.imprimir(cuartoActual.descripcionLarga());
            this.movimientos++;
        } else {
            this.imprimir("\nAun no has hecho ningun movimiento");
        }
    }

    /**
     * Metodo encargado de recoger Artefactos y guardarlos en la mochila.
     * 
     * @param artefacto el artefacto a guardar en la mochila.
     */
    public void recogerArtefacto(String artefacto) {
        // si no hay artefacto no sabemos que recoger
        if (null == artefacto) {
            this.imprimir("\nDr. George: ¿Cual artefacto?");
            return;
        }

        /*
        Intenta recoger el Artefacto, si esta el artefacto en el cuarto donde te encuentras, puede
        ser recogido y aun no alcanzaste la capacidad maxima de peso, el artefacto sera guardado en la mochila.
         */
        Artefacto newArtefacto = this.cuartoActual.getArtefacto(artefacto);
        if (null == newArtefacto) {
            this.imprimir("\nDr. George: Hmm, no veo nada parecido algo llamado " + artefacto);
        } else {
            this.mochila.guardarArtefacto(this, newArtefacto);
        }
    }

    /**
     * Metodo encargado de hacer que los zeppelins se muevan por sus
     * localizaciones. solo habra un zeppelin a la vez y estos se moveran entre
     * estos diferentes cuartos: La estacion de Tren, La fuente, El parque y el
     * El Estacionamiento.
     */
    public void desplazarZeppelin() {

        // El zeppelin se desplazara cada 3 movimientos que nosotros hacemos.
        final int CADA_3_MOVIMIENTOS = 3;
        if ((this.movimientos % CADA_3_MOVIMIENTOS) == 0) {

            // Llamamos al Zeppelin
            String zeppelinNombre = "Zeppelin";
            Zeppelin zeppelin = (Zeppelin) this.personajes.get(zeppelinNombre);

            // Le decimos al Zeppelin que se mueva
            zeppelin.irA(this);
        }
    }

    /**
     * Metodo encargado de derribar al Zeppelin disparando un rayo con la
     * Gravity Gun.
     *
     * @param alZeppelin
     */
    public void derribarZeppelin(String alZeppelin) {
        String nombreZeppelin = "Zeppelin";

        //Si no hay Zeppelin, no sabemos a quien disparar
        if (!nombreZeppelin.equalsIgnoreCase(nombreZeppelin)) {
            this.imprimir("\nHmm.. eso no ayudaria en absolutamente nada.");
            return;
        }

        // Si no tenemos la Gravity Gun, no tenemos con que derribarZeppelin 
        String nombreGravityGun = "Gravity Gun";
        if (this.mochila.tieneElArtefacto(nombreGravityGun)) {
            // Lamamos al Zeppelin
            Zeppelin zeppelin = (Zeppelin) this.getPersonaje(nombreZeppelin);
            // Derribamos al Zeppelin
            zeppelin.derribarZeppelin(this);
        } else {
            this.imprimir("\nDr. George: No tengo ningun arma para realizar esa accion!");
        }
    }

    /**
     * Metodo encargado de interactuar con un Vortigaunt para que te ayude a
     * encender la maquina del tiempo.
     *
     * @param nombreDeVortigaunt nombre del Vortigaunt
     */
    public void conversarVortigaunt(String nombreDeVortigaunt) {
        // Estas variables String representan las keys de los cuartos.
        String cuartoTaller = "taller";
        String cuartoLaboratorio = "laboratorio";
        String artefactoBateria = "bateria";

        // Llamamos a Vortigaunt.
        Vortigaunt vortigaunt = (Vortigaunt) this.getPersonaje(nombreDeVortigaunt.toLowerCase());

        // Si no esta vortigaunt no sabremos con quien hablar
        if (vortigaunt == null) {
            this.imprimir("Dr. George: ¿hablar con quien?");
            return;
        }

        // Si vortigaunt y tu estan en el taller tendran esta conversacion
        if (vortigaunt.estaEn(cuartoTaller) && (this.getCuartoActual() == this.getCuarto(cuartoTaller))) {
            vortigaunt.conversarEnTaller(this);
            vortigaunt.irHastaLaboratorio(this);
        }

        // Si voirtigaunt y tu estan en el laboratorio tendran esta conversacion
        if (vortigaunt.estaEn(cuartoLaboratorio) && (this.getCuartoActual() == this.getCuarto(cuartoLaboratorio))) {
            vortigaunt.conversacionEnLaboratorio(this);
        }

        // Si vortigaunt y tu estan en el laboratorio y tienes la bateria para entregarselo ocurria esto
        if (vortigaunt.estaEn(cuartoLaboratorio) && this.tengoElArtefacto(artefactoBateria)) {
            Artefacto bateria = mochila.getArtefacto(artefactoBateria);
            // damos la bateria al vortigaunt
            this.darBateriaVortigaunt(vortigaunt, bateria);
            // el vortigaunt conecta la bateria
            vortigaunt.colocarBateriaMaquinaDelTiempo(this.getMaquinaDelTiempo(), this);
        }
    }

    /**
     * Metodo encargado de entregar la bateria al vortigaunt
     * @param vortigaunt el vortigaunt que recibira la bateria
     * @param bateria la bateria que le entregaremos
     */
    private void darBateriaVortigaunt(Vortigaunt vortigaunt, Artefacto bateria) {
        vortigaunt.agarrarBateria(bateria);
        this.mochila.sacarArtefacto(bateria);
        this.imprimir("\nHe conseguido la bateria.. toma, espero que con esto sea suficiente.");
    }

    
    /**
     * Metodo encargado de verificar si la maquina del tiempo tiene la fuente de poder necesaria
     * si asi lo es, la maquina se enciende, viajas en el tiempo y el juego acaba.
     * @return 
     */
    public boolean viajarEnElTiempo() {
        // Si la maquina esta lista es decir, si contiene las dos baterias.
        if (this.timeMachine.encender()) {
            this.imprimir("\nLa Maquina del tiempo ha sido encendida con exito.");
            this.imprimir("\nDr. George: viajare al instante en donde todo ha empezado a corromperse,"
                    + "\ntengo que evitarlo. "
                    + "\nUn momento, recuerdo esto..");
            return false;
        }
        this.imprimir("\nDr. George: La maquina del tiempo no enciende, necesita una"
                + "\nfuente de poder mas poderosa. Debo hablar con Vortigaunt.");
        return true;
    }

    /**
     * Metodo encargado de imprimir las salidas del cuarto actual.
     */
    public void imprimirSalidas() {
        this.imprimir(this.cuartoActual.listarSalidas());
    }

    /**
     * Metodo encargado de imprimir un mensaje en el juego.
     * @param mensaje el mensaje que sera imprimido.
     */
    public void imprimir(String mensaje) {
        System.out.println(mensaje);
    }
    
    /**
     * Metodo encargado de imprimir un mensaje para el usuario, pero
     * se queda en la misma linea.
     * @param mensaje
     */
    public void imprimirCont(String mensaje) {
        System.out.print(mensaje);
    }

    /**
     * Metodo encargado de dejar vacias cuantas lineas le indiquemos.
     * @param lineas un entero que indica cuantas lineas quiere pasar.
     */
    public void pasarLineas(int lineas) {
        for (int i = 0; i < lineas; i++) {
            System.out.println("");
        }
    }

    /**
     * Metodo encargado de retonar el cuarto en el que se encuentra el jugador.
     * @return el cuartdo en el que se encuentra el jugador.
     */
    public Cuarto getCuartoActual() {
        return (Cuarto) this.cuartoActual;
    }

    /**
     * Metodo encargado de retornar un cuarto segun la key pasada como argumento.
     * @param cuarto nombre del cuarto el cual representa ña key.
     * @return el cuarto que estamos buscando.
     */
    public Cuarto getCuarto(String cuarto) {
        return (Cuarto) this.cuartos.get(cuarto);
    }

    /**
     * Metodo encargado de retornar un personaje del juego segun la key que pasamos como argumento.
     * @param personaje el nombre del personaje, el nombre representa su key.
     * @return el personaje que estamos buscando.
     */
    public Personaje getPersonaje(String personaje) {
        return (Personaje) this.personajes.get(personaje);
    }
    
    /**
     * Metodo encargado de verificar si tenemos un artefacto en la mochila.
     * @param artefacto el nombre del artefacto el cual representa su key.
     * @return true si contenemos el artefacto en la mochila.
     *         false si no contenemos el artefacto en la mochila.
     */
    public boolean tengoElArtefacto(String artefacto) {
        return this.mochila.getArtefacto(artefacto) != null;
    }
    
    /**
     * Metodo encargado de retornar la cantidad de movimientos que llevas.
     * @return cantidad de movimientos que llevas en el juego.
     */
    public int getMovimientos() {
        return this.movimientos;
    }

    /**
     * Metodo encargado de retornar la Maquina del tiempo.
     * @return la Maquina del tiempo.
     */
    public MaquinaDelTiempo getMaquinaDelTiempo() {
        return this.timeMachine;
    }
    
    /**
     * Metodo encargado de modificar el cuarto actual.
     * @param newCuartoActual 
     */
    public void setCuartoActual(Cuarto newCuartoActual){
        this.cuartoActual = newCuartoActual;     
    }
    /**
     * Metodo encargado en modificar el cuarto anterior.
     * @param newCuartoAnterior 
     */
    public void setCuartoAnterior(Cuarto newCuartoAnterior){
        this.cuartoAnterior = newCuartoAnterior;
    }
    
    /**
     * Metodo encargado de retornar la cantidad de cuartos.
     * @return la cantidad de cuartos en el juego.
     */
    public int getCantCuartos(){
        return this.cuartos.size();
    }
    
    /**
     * Metodo encargado de retornar una Collection con todos los cuartos.
     * @return un Collection con todos los cuartos del juego.
     */
    public Collection getCuartosColecction(){
        return  this.cuartos.values();
    }
 
    // contiene una coleccion con todos los cuartos del juego.
    private HashMap cuartos;
    
    // contiene una conleccion con todos los personajes del juego.
    private HashMap personajes;
    
    // es el parser del juego.
    private Parser parser;
    
    // contiene el cuarto actual del juego.
    private Cuarto cuartoActual;
    
    // contiene el caurto anterior del juego.
    private Cuarto cuartoAnterior;
    
    // contiene todos los artefactos que recogemos en el juego.
    private Mochila mochila;
    
    // es la maquina del tiempo en el juego.
    private MaquinaDelTiempo timeMachine;
    
    // es un entero que cuenta la cantidad de veces que hacemos movimientos.
    private int movimientos;  
}
