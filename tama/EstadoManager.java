package tama;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class EstadoManager {
    private static final Map<Estado, Predicate<Tamagochi>> ESTADO_CONDICIONES = new EnumMap<>(Estado.class);
    private Map<Estado, String> mensajesEstado;

    public EstadoManager() {
        inicializarCondicionesEstado();
        inicializarMensajesEstado();
    }

    private void inicializarCondicionesEstado() {
        ESTADO_CONDICIONES.put(Estado.SUCIO, tamagochi -> tamagochi.getHigiene() <= 30);
        ESTADO_CONDICIONES.put(Estado.NECESITA_MEDICINA, tamagochi -> tamagochi.getMedicina() == 0);
        ESTADO_CONDICIONES.put(Estado.SOBREPESO, tamagochi -> tamagochi.getPeso() >= 80);
        ESTADO_CONDICIONES.put(Estado.DESNUTRIDO, tamagochi -> tamagochi.getPeso() <= 20);
        ESTADO_CONDICIONES.put(Estado.HAMBRIENTO, tamagochi -> tamagochi.getHambre() > 5);
        ESTADO_CONDICIONES.put(Estado.CANSADO, tamagochi -> tamagochi.getEnergia() < 3);
        ESTADO_CONDICIONES.put(Estado.ENFERMO, tamagochi -> tamagochi.getVida() <= 50);
        ESTADO_CONDICIONES.put(Estado.NECESITA_CACA, tamagochi -> tamagochi.getNecesidadCaca() >= 80);
        ESTADO_CONDICIONES.put(Estado.QUIERE_JUGAR, tamagochi -> tamagochi.getGanasDeJugar() >= 60);
        ESTADO_CONDICIONES.put(Estado.TIENE_SUEÑO, tamagochi -> tamagochi.getPeso() >= 60);
        ESTADO_CONDICIONES.put(Estado.INQUIETO, tamagochi -> tamagochi.getCordura() <= 30);
        ESTADO_CONDICIONES.put(Estado.NORMAL, tamagochi -> true);
        ESTADO_CONDICIONES.put(Estado.ESTRESADO, tamagochi -> tamagochi.getactividadreciente() > 70 && tamagochi.getEnergia() < 30);
        ESTADO_CONDICIONES.put(Estado.ABURRIDO, tamagochi -> tamagochi.getultimainteraccion() > 2); // horas desde la última interacción
        ESTADO_CONDICIONES.put(Estado.ANSIOSO, tamagochi -> tamagochi.getSocial() < 20);
    }

        

    private void inicializarMensajesEstado() {

        mensajesEstado = new HashMap<>();
        mensajesEstado.put(Estado.HAMBRIENTO, "¡Estoy tan hambriento que podría desmayarme! Por favor, ¿puedes alimentarme?");
        mensajesEstado.put(Estado.DEBIL, "¡Me siento increíblemente débil! Casi no tengo energía para jugar...");
        mensajesEstado.put(Estado.ENFERMO, "Me siento terrible... ¿Podrías cuidarme? No quiero sentirme así.");
        mensajesEstado.put(Estado.NECESITA_CACA, "¡Es urgente! Realmente necesito ir al baño ahora mismo o... ¡no puedo aguantarlo más!");
        mensajesEstado.put(Estado.QUIERE_JUGAR, "Me siento tan solo... ¿Podrías jugar un poco conmigo? Realmente necesito algo de atención.");
        mensajesEstado.put(Estado.TIENE_SUEÑO, "Mis ojos apenas pueden mantenerse abiertos... Necesito dormir o no podré seguir adelante.");
        mensajesEstado.put(Estado.NORMAL, "Me siento bastante bien ahora, ¡gracias por cuidarme!");
        mensajesEstado.put(Estado.INQUIETO, "Me siento inquieto... algo no va bien. ¿Puedes chequear qué sucede?");
        mensajesEstado.put(Estado.SUCIO, "¡Ugh! Me siento realmente sucio... ¿Podrías darme un baño?");
        mensajesEstado.put(Estado.NECESITA_MEDICINA, "No me siento bien y no tenemos medicinas. ¿Podrías conseguir algo para mí?");
        mensajesEstado.put(Estado.SOBREPESO, "Me siento pesado y lento. Tal vez debería hacer más ejercicio y comer menos.");
        mensajesEstado.put(Estado.DESNUTRIDO, "Me siento débil y mi cuerpo se siente ligero... necesito comer más para recuperar mi energía.");
        }

    public static void actualizarEstado(Tamagochi tamagochi) {
        Estado estadoActual = Estado.NORMAL; 
        for (Map.Entry<Estado, Predicate<Tamagochi>> entry : ESTADO_CONDICIONES.entrySet()) {
            Estado estado = entry.getKey();
            Predicate<Tamagochi> condicion = entry.getValue();
            if (condicion.test(tamagochi)) {
                estadoActual = estado;
                break; 
            }
        }
        tamagochi.setEstadoActual(estadoActual); 
        System.out.println("Estado actualizado a: " + estadoActual);
    }

    public String getMensajeEstado(Estado estado) {
        return mensajesEstado.getOrDefault(estado, "Estado no reconocido.");
    }
    public void verificarynotificarestados(Tamagochi tamagochi) {
        String mensaje = getMensajeEstado(tamagochi.getEstadoActual());
        if (tamagochi.getEstadoActual() == Estado.ENFERMO || tamagochi.getEstadoActual() == Estado.DESNUTRIDO) {
            mostraralerta("¡atención! " + mensaje);
        } else {
            System.out.println(mensaje);
        }
    }

    private void mostraralerta(String mensaje) {
        // implementar lógica para mostrar una alerta en la ui
        System.out.println(mensaje); // placeholder para la implementación de ui
    }
    

}