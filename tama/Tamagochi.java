package tama;

import java.util.EnumSet;


public class Tamagochi {
    // Constantes
    public static final int[] NIVEL_UP_REQUISITOS = {100, 200, 300, 400};

    private Nivel nivelActual = Nivel.BEBE;
    private Estado estadoActual = Estado.FELIZ;
    private EnumSet<Scenario> unlockedScenarios = EnumSet.noneOf(Scenario.class);

    // Variables de instancia
    private String nombre;
    private int hambre, vida, energia, paz, higiene, medicina, peso, cordura, experiencia, necesidadCaca, ganasDeJugar, alegria;

    NecesidadManager necesidadManager;
    private EstadoManager estadoManager;
    private InteraccionManager interaccionManager;
    
    private long lastInteractionTime;

	private int salud;

	private int inteligencia;

	private int social;

	private int felicidad;
	private int actividadreciente;
    private long ultimainteraccion;
    private int nivelsocial;

    public Tamagochi(String nombre, int hambre, int cordura, int vida, int paz, int higiene, int medicina, int peso, int energia, int alegria, Temperamento temperamento) throws TamagochiException {
        this.nombre = nombre;
        this.hambre = hambre;
        this.cordura = cordura;
        // Asegurarse de que vida inicie en 100 (o el valor deseado)
        this.vida = 100; // Ajusta según lo deseado
        this.setPaz(paz);
        this.higiene = higiene;
        this.medicina = medicina;
        this.peso = peso;
        this.energia = energia;
        this.alegria = alegria;
        // Inicializar experiencia en 0 (o el valor deseado)
        this.experiencia = 0; // Ajusta según lo deseado
        necesidadManager = new NecesidadManager();
        estadoManager = new EstadoManager();
        interaccionManager = new InteraccionManager();

        validateInitialValues();
    }


    private void validateInitialValues() throws TamagochiException {
    }

	
    
    public void alimentar(boolean comidaSana) {
        interaccionManager.alimentar(this, comidaSana);
    }

    public void jugar() {
        interaccionManager.jugar(this);
    }

    public void dormir() {
        interaccionManager.dormir(this);
    }

    public void acariciar() {
        interaccionManager.acariciar(this);
    }

    public void ejercitar() {
        interaccionManager.ejercitar(this);
    }

    public void companion() {
        interaccionManager.companion(this);
    }

    public void limpiar() {
        higiene += 20;
        if (higiene > 100) {
            higiene = 100;
        }
        System.out.println(nombre + " se siente más limpio!");
    }

    public void medicar() {
        if (medicina > 0) {
            medicina--;
            vida += 20;
            System.out.println(nombre + " se siente mejor!");
        } else {
            System.out.println("No tienes medicinas!");
        }
    }

    public void ganarExperiencia(int cantidad) {
        experiencia += cantidad;
        if (experiencia >= NIVEL_UP_REQUISITOS[nivelActual.ordinal()]) {
            subirNivel();
        }
    }
    
    
    public void hacerCaca() {
        necesidadCaca = 0; // Restablece la necesidad de caca
        higiene -= 20; // Asume que hacer caca disminuye la higiene
        if (higiene < 0) {
            higiene = 0; // Asegura que la higiene no sea negativa
        }
        System.out.println("Que nadie entre el baño en un ratillo.");
    }

    
    
    
    
    
    
    
    
    

    private void subirNivel() {
        int currentLevelIndex = nivelActual.ordinal();
        if (currentLevelIndex < Nivel.values().length - 1) {
            nivelActual = Nivel.values()[currentLevelIndex + 1];
            System.out.println("Tu Tamagochi ha subido al nivel " + nivelActual + "!");
            unlockScenarios();
        }
    }

    private void unlockScenarios() {
        unlockedScenarios.clear();
        switch (nivelActual) {
            case BEBE:
                showTutorial();
                break;
            case INFANTE:
                unlockedScenarios.addAll(EnumSet.of(Scenario.COCINA, Scenario.BAÑO));
                break;
            case JOVEN:
                unlockedScenarios.add(Scenario.PARQUE);
                break;
            case ADULTO:
                unlockedScenarios.add(Scenario.HABITACION);
                break;
        }
    }

    private void showTutorial() {
        System.out.println("Bienvenido al tutorial de Tamagochi.");
        System.out.println("1. Alimenta a tu Tamagochi para evitar que tenga hambre.");
        System.out.println("2. Mantén a tu Tamagochi limpio y feliz llevándolo al baño.");
        System.out.println("3. Juega con tu Tamagochi para mantenerlo feliz.");
        System.out.println("Sigue estos consejos para cuidar bien a tu Tamagochi.");
    }

    public void interactuarConScenario(Scenario scenario) {
        if (!unlockedScenarios.contains(scenario)) {
            System.out.println("Este escenario no está disponible en tu nivel actual.");
            return;
        }

        switch (scenario) {
            case COCINA:
                alimentar(true);
                break;
            case BAÑO:
                hacerCaca();
                break;
            case PARQUE:
                jugar();
                break;
            case HABITACION:
                dormir();
                break;
        }
    }

    public String getMensajeEstado() {
        necesidadManager.actualizarNecesidadesConTiempo(this);
        EstadoManager.actualizarEstado(this);
        return estadoManager.getMensajeEstado(estadoActual);
    }

    
    
 // En Tamagochi, añade un contador para enfermedades
    
    
    
    
    
    
    
    // Getters y Setters
    public NecesidadManager getNeedManager() {
        return necesidadManager;
    }
    
    
    public class TamagochiException extends Exception {
        private static final long serialVersionUID = 1L;

		public TamagochiException(String message) {
            super(message);
        }
    }
    public int getHigiene() {
        return higiene;
    }

    public void setHigiene(int higiene) {
        this.higiene = higiene;
    }

    public int getPeso() {
        return peso;
    }
    

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getMedicina() {
        return medicina;
    }

    public void setMedicina(int medicina) {
        this.medicina = medicina;
    }

    public int getHambre() {
        return hambre;
    }

    public void setHambre(int hambre) {
        this.hambre = hambre;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getNecesidadCaca() {
        return necesidadCaca;
    }
    public void setNecesidadCaca(int necesidadCaca) {
        this.necesidadCaca = necesidadCaca;
    }

    public int getGanasDeJugar() {
        return ganasDeJugar;
    }

    public int getCordura() {
        return cordura;
    }

    public void setCordura(int cordura) {
        this.cordura = cordura;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public long getLastInteractionTime() {
        return this.lastInteractionTime;
    }


    public void setLastInteractionTime(long lastInteractionTime) {
        this.lastInteractionTime = lastInteractionTime;
    }

    
    public int getFelicidad() {
        return felicidad;
    }

    public void setFelicidad(int felicidad) {
        this.felicidad = felicidad;
    }


    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public int getSalud() {
        return this.salud;
    }

    
    public int getpaz() {
        return this.paz;
    }

    public void setPaz(int paz) {
        this.paz = paz;
    }



    public int getAlegria() {
        return this.alegria;
    }

    public void setAlegria(int alegria) {
        this.alegria = alegria;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

	
    public int getSocial() {
        return this.social;
    }

    public void setSocial(int social) {
        this.social = social;
    }


    public String getStateMessage() {
        return "Tamagochi " + this.nombre + " is currently " + this.estadoActual + ".";
    }

	public int getExperiencia() {
		return this.experiencia;
	}
	
	
	public int getactividadreciente() {
        return actividadreciente;
    }
    
    public long getultimainteraccion() {
        // puede ser necesario calcular las horas desde la última interacción
        return ultimainteraccion;
    }
    
    public int getnivelsocial() {
        return nivelsocial;
    }

	
}