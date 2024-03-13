package tama;

import java.util.EnumMap;
import java.util.Map;

public class TamagochiNecesidades {
    private static final Map<Necesidad, Integer> TASAS_DECAIMIENTO = new EnumMap<>(Necesidad.class);

    static {
    
        TASAS_DECAIMIENTO.put(Necesidad.HAMBRE, 5); 
        TASAS_DECAIMIENTO.put(Necesidad.ENERGIA, 10); 
        TASAS_DECAIMIENTO.put(Necesidad.HIGIENE, 4); 
        TASAS_DECAIMIENTO.put(Necesidad.FELICIDAD, 6); 
        TASAS_DECAIMIENTO.put(Necesidad.SOCIAL, 7); 
        TASAS_DECAIMIENTO.put(Necesidad.SALUD, 3); 
    }

    public static void actualizarNecesidadesConTiempo(Tamagochi tamagochi) {
        long currentTime = System.currentTimeMillis();
        long elapsedTicks = calculateElapsedTicks(tamagochi.getLastInteractionTime(), currentTime);

        if (elapsedTicks > 0) {
            TASAS_DECAIMIENTO.forEach((necesidad, tasa) -> updateNecesidad(tamagochi, necesidad, elapsedTicks));
            tamagochi.setLastInteractionTime(currentTime);
            TamagochiEstado.actualizarEstado(tamagochi);
        }
    }

    private static long calculateElapsedTicks(long lastInteractionTime, long currentTime) {
        return (currentTime - lastInteractionTime) / (3600 * 1000);
    }

    private static void updateNecesidad(Tamagochi tamagochi, Necesidad necesidad, long elapsedTicks) {
        int decayAmount = TASAS_DECAIMIENTO.get(necesidad) * (int) elapsedTicks;
        
        switch (necesidad) {
            case HAMBRE:
                tamagochi.setHambre(Math.max(tamagochi.getHambre() - decayAmount, 0));
                break;
            case ENERGIA:
                tamagochi.setEnergia(Math.max(tamagochi.getEnergia() - decayAmount, 0));
                break;
            case HIGIENE:
                tamagochi.setHigiene(Math.max(tamagochi.getHigiene() - decayAmount, 0));
                break;
            case FELICIDAD:
                tamagochi.setFelicidad(Math.max(tamagochi.getFelicidad() - decayAmount, 0));
                break;
            case SOCIAL:
                tamagochi.setSocial(Math.max(tamagochi.getSocial() - decayAmount, 0));
                break;
            case SALUD:
                tamagochi.setSalud(Math.max(tamagochi.getSalud() - decayAmount, 0));
                break;
        }
    }
}
