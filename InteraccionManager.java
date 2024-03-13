package tama;

public class InteraccionManager {

    public void alimentar(Tamagochi tamagochi, boolean comidaSana) {
        if (comidaSana) {
            tamagochi.setHambre(Math.max(tamagochi.getHambre() - 20, 0));
            tamagochi.setVida(Math.min(tamagochi.getVida() + 10, 100));
            tamagochi.setCordura(Math.min(tamagochi.getCordura() + 5, 100));
            tamagochi.setPeso(Math.min(tamagochi.getPeso() + 2, 100));
        } else {
            tamagochi.setHambre(Math.max(tamagochi.getHambre() - 50, 0));
            tamagochi.setVida(Math.max(tamagochi.getVida() - 5, 0));
            tamagochi.setCordura(Math.max(tamagochi.getCordura() - 10, 0));
            tamagochi.setPeso(Math.min(tamagochi.getPeso() + 5, 100));
        }
        tamagochi.setEnergia(Math.min(tamagochi.getEnergia() + 5, 100));

        tamagochi.setNecesidadCaca(Math.min(tamagochi.getClass() + 40, 100));

        System.out.println("Tamagochi alimentado!");
        EstadoManager.actualizarEstado(tamagochi);
    }

    public void jugar(Tamagochi tamagochi) {
        tamagochi.setCordura(Math.min(tamagochi.getCordura() + 15, 100));
        tamagochi.setEnergia(Math.max(tamagochi.getEnergia() - 20, 0));
        EstadoManager.actualizarEstado(tamagochi);
    }

    public void dormir(Tamagochi tamagochi) {
        tamagochi.setEnergia(Math.max(tamagochi.getEnergia() + 40, 0));
        tamagochi.setVida(Math.min(tamagochi.getVida() + 30, 100));
        System.out.println("Tamagochi descansando!");
        EstadoManager.actualizarEstado(tamagochi);
    }

    public void acariciar(Tamagochi tamagochi) {
        tamagochi.setPaz(Math.min(tamagochi.getPaz() + 20, 100));
        tamagochi.setCordura(Math.min(tamagochi.getCordura() + 10, 100));
    }

    public void ejercitar(Tamagochi tamagochi) {
        tamagochi.setEnergia(Math.max(tamagochi.getEnergia() - 15, 0));
        tamagochi.setVida(Math.min(tamagochi.getVida() + 15, 100));
        tamagochi.setCordura(Math.min(tamagochi.getCordura() + 5, 100));
        tamagochi.setPeso(Math.max(tamagochi.getPeso() - 3, 0));
    }

    public void companion(Tamagochi tamagochi) {
        tamagochi.setPaz(Math.min(tamagochi.getPaz() + 10, 100));
        tamagochi.setCordura(Math.min(tamagochi.getCordura() + 10, 100));
    }
}