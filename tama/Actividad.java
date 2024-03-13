package tama;

public enum Actividad {

    COMER_SANO {
        @Override
        public void realizar(Tamagochi tamagochi) {
            tamagochi.setHambre(Math.max(tamagochi.getHambre() - 30, 0));
            tamagochi.setPeso(Math.min(tamagochi.getPeso() + 1, 100));
            tamagochi.setVida(Math.min(tamagochi.getVida() + 10, 100)); 
            tamagochi.setEnergia(Math.min(tamagochi.getEnergia() + 10, 100));
            tamagochi.setNecesidadCaca(tamagochi.getNecesidadCaca() + 20); // incrementar la necesidad de hacer caca
            tamagochi.ganarExperiencia(10);
            System.out.println("Comiendo algo saludable mejora varios aspectos de la salud de tu Tamagochi. Los beneficios aumentan si se mantiene una dieta balanceada.");
        }
    },
    COCINAR_SANO {
        @Override
        public void realizar(Tamagochi tamagochi) {
            tamagochi.setHambre(Math.max(tamagochi.getHambre() - 40, 0));
            tamagochi.setPeso(Math.min(tamagochi.getPeso(), 100)); 
            tamagochi.setVida(Math.min(tamagochi.getVida() + 15, 100)); 
            tamagochi.setEnergia(Math.min(tamagochi.getEnergia() + 15, 100));
            tamagochi.ganarExperiencia(20);
            System.out.println("Cocinar y comer alimentos saludables proporciona mayores beneficios a la salud de tu Tamagochi.");
        }
    },
    BUSCAR_ALIMENTOS {
        @Override
        public void realizar(Tamagochi tamagochi) {
            System.out.println("buscando alimentos...");
            
            // simula la búsqueda de alimentos
            boolean comidasanaencontrada = Math.random() < 0.5; // 50% de posibilidades
            if (comidasanaencontrada) {
                System.out.println("¡has encontrado comida saludable! esto beneficia la salud de tu tamagochi.");
                tamagochi.alimentar(true); 
                tamagochi.setFelicidad(tamagochi.getFelicidad() + 10); 
            } else {
                System.out.println("has encontrado comida, pero no es la más saludable.");
                tamagochi.alimentar(false);
                tamagochi.setFelicidad(tamagochi.getFelicidad() + 5);
            }
            tamagochi.ganarExperiencia(30);
}
    },
    PLANIFICAR_DIETA {
        @Override
        public void realizar(Tamagochi tamagochi) {
            System.out.println("planificando una dieta balanceada...");
            
            tamagochi.setSalud(tamagochi.getSalud() + 20); 
            tamagochi.setFelicidad(tamagochi.getFelicidad() + 5); 
            
            System.out.println("una dieta balanceada ha sido planificada. la salud y felicidad de tu tamagochi han mejorado.");
            tamagochi.ganarExperiencia(10);

        }

		@Override
		public void realizar(Tamagochi tamagochi) {
			// TODO Auto-generated method stub
			
		
		}
    },
    COMER_CHATARRA {
        @Override
        public void realizar(Tamagochi tamagochi) {
            tamagochi.setHambre(Math.max(tamagochi.getHambre() - 50, 0));
            tamagochi.setPeso(Math.min(tamagochi.getPeso() + 5, 100));
            tamagochi.setVida(Math.max(tamagochi.getVida() - 5, 0));
            tamagochi.setNecesidadCaca(tamagochi.getNecesidadCaca() + 30); // comer chatarra podría aumentar más la necesidad de hacer caca
            tamagochi.ganarExperiencia(2);
            System.out.println("Comer chatarra sacia más el hambre, pero tiene efectos negativos en la salud.");
        }
    },
    DORMIR {
        @Override
        public void realizar(Tamagochi tamagochi) {
            tamagochi.setEnergia(Math.min(tamagochi.getEnergia() + 50, 100));
            tamagochi.setPeso(Math.min(tamagochi.getPeso() + 20, 100));
            tamagochi.ganarExperiencia(1);
            System.out.println("Dormir restaura energía y aumenta la paz.");
       
        }
    },
    JUGAR {
        @Override
        public void realizar(Tamagochi tamagochi) {
            tamagochi.setEnergia(Math.max(tamagochi.getEnergia() - 20, 0));
            tamagochi.setFelicidad(Math.min(tamagochi.getFelicidad() + 30, 100));
            tamagochi.setPeso(Math.max(tamagochi.getPeso() - 10, 0));
            tamagochi.ganarExperiencia(5);

            System.out.println("Jugar incrementa la felicidad pero consume energía.");
        }
    },
    EJERCITAR {
        @Override
        public void realizar(Tamagochi tamagochi) {
            tamagochi.setEnergia(Math.max(tamagochi.getEnergia() - 30, 0));
            tamagochi.setPeso(Math.max(tamagochi.getPeso() - 3, 0));
            tamagochi.setFelicidad(Math.min(tamagochi.getFelicidad() + 10, 100));
            System.out.println("Hacer ejercicio es bueno para mantener un peso saludable y mejora la felicidad.");
            tamagochi.ganarExperiencia(10);

        }
    },
    JUGAR_CON_AMIGOS {
        @Override
        public void realizar(Tamagochi tamagochi) {
            tamagochi.setEnergia(Math.max(tamagochi.getEnergia() - 30, 0));
            tamagochi.setFelicidad(Math.min(tamagochi.getFelicidad() + 40, 100));
            tamagochi.setSocial(Math.min(tamagochi.getSocial() + 20, 100));
            tamagochi.ganarExperiencia(10);
            System.out.println("Jugar con amigos aumenta significativamente la felicidad y mejora las habilidades sociales, pero consume más energía.");
        }
    },
    APRENDER_CON_JUGUETES {
        @Override
        public void realizar(Tamagochi tamagochi) {
            tamagochi.setEnergia(Math.max(tamagochi.getEnergia() - 10, 0));
            tamagochi.setFelicidad(Math.min(tamagochi.getFelicidad() + 20, 100));
            tamagochi.setInteligencia(Math.min(tamagochi.getInteligencia() + 15, 100)); 
            tamagochi.ganarExperiencia(10);
            System.out.println("Aprender con juguetes educativos mejora la inteligencia además de incrementar la felicidad.");
        }
    },
    HACER_DEPORTE {
        @Override
        public void realizar(Tamagochi tamagochi) {
            tamagochi.setEnergia(Math.max(tamagochi.getEnergia() - 40, 0));
            tamagochi.setPeso(Math.max(tamagochi.getPeso() - 5, 0));
            tamagochi.setFelicidad(Math.min(tamagochi.getFelicidad() + 25, 100));
            tamagochi.setSalud(Math.min(tamagochi.getSalud() + 20, 100)); 
            tamagochi.ganarExperiencia(10);
            System.out.println("Hacer deporte incrementa la salud y la felicidad pero es más exigente en términos de energía.");
        }
    },
    PRACTICAR_YOGA {
        @Override
        public void realizar(Tamagochi tamagochi) {
            tamagochi.setEnergia(Math.max(tamagochi.getEnergia() - 15, 0));
            tamagochi.setPeso(Math.max(tamagochi.getPeso() - 1, 0));
            tamagochi.setFelicidad(Math.min(tamagochi.getFelicidad() + 20, 100));
            tamagochi.setPeso(Math.min(tamagochi.getPeso() + 30, 100)); 
            tamagochi.ganarExperiencia(10);
            System.out.println("Practicar yoga aumenta la paz interior y la felicidad, con un leve consumo de energía.");
        }
    };

    public abstract void realizar(Tamagochi tamagochi);
}