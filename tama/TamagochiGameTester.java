package tama;
import java.util.Random;

public class TamagochiGameTester {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
                Tamagochi tamagochi;
        try {
            tamagochi = new Tamagochi("Tamy", 50, 80, 100, 60, 80, 3, 50, 70, 80, Temperamento.ALEGRE);
        } catch (Tamagochi.TamagochiException e) {
            System.err.println("Error creating Tamagochi: " + e.getMessage());
            return;
        }

        simulateActivities(tamagochi);
    }

    private static void simulateActivities(Tamagochi tamagochi) {
        for (int day = 1; day <= 7; day++) {
            System.out.println("\nDay " + day + ":");

            for (int hour = 0; hour < 24; hour++) {
                // Advance time
                tamagochi.necesidadManager.actualizarNecesidadesConTiempo(tamagochi);

                System.out.println("Hour " + hour + ": " + tamagochi.getStateMessage());

                performRandomActivity(tamagochi);
            }
        }
    }

    private static void performRandomActivity(Tamagochi tamagochi) {
        int activityIndex = RANDOM.nextInt(Actividad.values().length);
        Actividad activity = Actividad.values()[activityIndex];

        System.out.println("Performing activity: " + activity);
        activity.realizar(tamagochi);
    }
}