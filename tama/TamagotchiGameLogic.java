package tama;

public class TamagotchiGameLogic {
    private Tamagochi tamagochi;

    public TamagotchiGameLogic() throws tama.Tamagochi.TamagochiException {
        tamagochi = new Tamagochi("Tama", 50, 50, 50, 50, 50, 50, 50, 50, 50, Temperamento.CALMADO);
    }


    public void alimentar(boolean comidaSana) {
        tamagochi.alimentar(comidaSana);
        actualizarEstado();
    }

    public void jugar() {
        tamagochi.jugar();
        actualizarEstado();
    }

    public void dormir() {
        tamagochi.dormir();
        actualizarEstado();
    }

    public void acariciar() {
        tamagochi.acariciar();
        actualizarEstado();
    }

    public void ejercitar() {
        tamagochi.ejercitar();
        actualizarEstado();
    }

    public void companion() {
        tamagochi.companion();
        actualizarEstado();
    }

    public int getExperiencia() {
        return tamagochi.getExperiencia();
    }

    public int getVida() {
        return tamagochi.getVida();
    }

    public int getHambre() {
        return tamagochi.getHambre();
    }

    public int getFelicidad() {
        return tamagochi.getFelicidad();
    }

    public int getEnergia() {
        return tamagochi.getEnergia();
    }
    public void hacerCaca() {
        tamagochi.hacerCaca();
        actualizarEstado();
    }


    // Otros métodos para obtener los valores de las necesidades del Tamagotchi

    private void actualizarEstado() {
        tamagochi.getNeedManager().actualizarNecesidadesConTiempo(tamagochi);
        EstadoManager.actualizarEstado(tamagochi);
    }

    public Tamagochi getTamagochi() {
        return tamagochi;
    }


   
}