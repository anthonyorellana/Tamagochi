package tama;

import java.util.EnumMap;
import java.util.Map;

public class NecesidadManager {
    private Map<Necesidad, Integer> tasasDecaimiento = new EnumMap<>(Necesidad.class);
    private long lastInteractionTime;

    public NecesidadManager() {
        this.lastInteractionTime = System.currentTimeMillis();
        inicializarTasasDecaimiento();
    }

    private void inicializarTasasDecaimiento() {
        tasasDecaimiento.put(Necesidad.HAMBRE, 5); // Puntos de decaiminiento por hora
        tasasDecaimiento.put(Necesidad.ENERGIA, 10);
        tasasDecaimiento.put(Necesidad.HIGIENE, 4);
        tasasDecaimiento.put(Necesidad.FELICIDAD, 7);
        tasasDecaimiento.put(Necesidad.SOCIAL, 6);
        tasasDecaimiento.put(Necesidad.SALUD, 3);
    }

    public void actualizarNecesidadesConTiempo(Tamagochi tamagochi) {
        long currentTime = System.currentTimeMillis();
        long elapsedTicks = calculateElapsedTicks(currentTime);

        if (elapsedTicks > 0) {
            tasasDecaimiento.forEach((necesidad, tasaDecaimiento) -> {
                updateNecesidad(tamagochi, necesidad, elapsedTicks, tasaDecaimiento);
            });
            lastInteractionTime = currentTime;
        }
    }

    private long calculateElapsedTicks(long currentTime) {
        return (currentTime - lastInteractionTime) / (3600 * 1000);
    }

    private void updateNecesidad(Tamagochi tamagochi, Necesidad necesidad, long elapsedTicks, int tasaDecaimiento) {
        int totalDecay = (int) elapsedTicks * tasaDecaimiento;
        switch (necesidad) {
            case HAMBRE:
                tamagochi.setHambre(Math.max(tamagochi.getHambre() - totalDecay, 0));
                break;
            case ENERGIA:
                tamagochi.setEnergia(Math.max(tamagochi.getEnergia() - totalDecay, 0));
                break;
		default:
			break;
        }
    }

	public void updateNeedsOverTime(Tamagochi tamagochi) {
		
	}
}
