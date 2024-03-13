package tama;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Predicate;

public class TamagochiEstado {
    private static final Map<Estado, Predicate<Tamagochi>> ESTADO_CONDICIONES = new EnumMap<>(Estado.class);
    private static final Map<Estado, String> MENSAJES_ESTADO = new EnumMap<>(Estado.class);

    static {
        inicializarCondiciones();
        inicializarMensajes();
    }

    private static void inicializarCondiciones() {
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
    }

    private static void inicializarMensajes() {
        MENSAJES_ESTADO.put(Estado.HAMBRIENTO, "¡Estoy tan hambriento que podría desmayarme! Por favor, ¿puedes alimentarme?");
        MENSAJES_ESTADO.put(Estado.SUCIO, "¡Ugh! Me siento realmente sucio... ¿Podrías darme un baño?");
        MENSAJES_ESTADO.put(Estado.NECESITA_MEDICINA, "No me siento bien y no tenemos medicinas. ¿Podrías conseguir algo para mí?");
        MENSAJES_ESTADO.put(Estado.SOBREPESO, "Me siento pesado y lento. Tal vez debería hacer más ejercicio y comer menos.");
        MENSAJES_ESTADO.put(Estado.DESNUTRIDO, "Me siento débil y mi cuerpo se siente ligero... necesito comer más para recuperar mi energía.");
        MENSAJES_ESTADO.put(Estado.CANSADO, "¡Me siento increíblemente cansado! Necesito descansar...");
        MENSAJES_ESTADO.put(Estado.ENFERMO, "Me siento terrible... ¿Podrías cuidarme? No quiero sentirme así.");
        MENSAJES_ESTADO.put(Estado.NECESITA_CACA, "¡Es urgente! Realmente necesito ir al baño ahora mismo o... ¡no puedo aguantarlo más!");
        MENSAJES_ESTADO.put(Estado.QUIERE_JUGAR, "Me siento tan solo... ¿Podrías jugar un poco conmigo? Realmente necesito algo de atención.");
        MENSAJES_ESTADO.put(Estado.TIENE_SUEÑO, "Mis ojos apenas pueden mantenerse abiertos... Necesito dormir o no podré seguir adelante.");
        MENSAJES_ESTADO.put(Estado.INQUIETO, "Me siento inquieto... algo no va bien. ¿Puedes chequear qué sucede?");
    }
    

    public static void actualizarEstado(Tamagochi tamagochi) {
        for (Map.Entry<Estado, Predicate<Tamagochi>> condicion : ESTADO_CONDICIONES.entrySet()) {
            if (condicion.getValue().test(tamagochi)) {
                tamagochi.setEstadoActual(condicion.getKey());
                System.out.println(MENSAJES_ESTADO.get(condicion.getKey()));
                break; 
            }
        }
    }


    public static String getMensajeEstado(Tamagochi tamagochi) {
        return MENSAJES_ESTADO.get(tamagochi.getEstadoActual());
    }
}