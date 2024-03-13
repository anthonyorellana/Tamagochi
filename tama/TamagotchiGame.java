package tama;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TamagotchiGame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JProgressBar expBar, healthBar;
    private JTextPane textOutput;
    private JLabel hungerLabel, happinessLabel, energyLabel;
    private JComboBox<Actividad> actividadesComboBox;

    private TamagotchiGameLogic gameLogic;

    public TamagotchiGame() {
        initializeGame();
        initializeUI();
        startUpdateThread();
    }

    private void initializeGame() {
        try {
            gameLogic = new TamagotchiGameLogic();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al iniciar TamagotchiGameLogic: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initializeUI() {
        setTitle("Tamagotchi Game");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        addComponents();
    }

    private void addComponents() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // EXP Bar
        addLabelAndComponent("EXP", expBar = new JProgressBar(0, 100), constraints, 0);
        // Health Bar
        addLabelAndComponent("Vida", healthBar = new JProgressBar(0, 100), constraints, 1);
        // Text Output
        textOutput = new JTextPane();
        textOutput.setEditable(false);
        constraints.gridx = 0; constraints.gridy = 2; constraints.gridwidth = 2; constraints.weightx = 1.0; constraints.weighty = 1.0; constraints.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(textOutput), constraints);
        // Reset for next components
        constraints.weightx = 0; constraints.weighty = 0; constraints.fill = GridBagConstraints.HORIZONTAL;
        // Labels
        addLabel("Hambre: ", hungerLabel = new JLabel(), constraints, 3);
        addLabel("Felicidad: ", happinessLabel = new JLabel(), constraints, 4);
        addLabel("Energía: ", energyLabel = new JLabel(), constraints, 5);
        // Actividades ComboBox
        actividadesComboBox = new JComboBox<>(Actividad.values());
        actividadesComboBox.addActionListener(this::actividadSelected);
        constraints.gridy = 6; add(actividadesComboBox, constraints);
    }

    private void addLabelAndComponent(String labelText, JComponent component, GridBagConstraints constraints, int row) {
        constraints.gridx = 0; constraints.gridy = row;
        add(new JLabel(labelText), constraints);
        constraints.gridx = 1;
        add(component, constraints);
    }

    private void addLabel(String labelText, JLabel label, GridBagConstraints constraints, int row) {
        constraints.gridx = 0; constraints.gridy = row; constraints.gridwidth = 1;
        add(new JLabel(labelText), constraints);
        constraints.gridx = 1;
        add(label, constraints);
    }

    private void actividadSelected(ActionEvent e) {
        Actividad actividadSeleccionada = (Actividad) actividadesComboBox.getSelectedItem();
        if (actividadSeleccionada != null) {
            actividadSeleccionada.realizar(gameLogic.getTamagochi());
            updateGame("Has realizado la actividad: " + actividadSeleccionada.name());
            updateNeedsInfo();
        }
    }

    private void updateGame(String message) {
        textOutput.setText(message);
    }

    private void updateNeedsInfo() {
        hungerLabel.setText("Hambre: " + gameLogic.getHambre());
        happinessLabel.setText("Felicidad: " + gameLogic.getFelicidad());
        energyLabel.setText("Energía: " + gameLogic.getEnergia());
        // Añade la actualización de las barras de experiencia y vida
        expBar.setValue(gameLogic.getExperiencia());
        healthBar.setValue(gameLogic.getVida());
    }


    private void startUpdateThread() {
        new Thread(() -> {
            while (true) {
                try {
                    SwingUtilities.invokeLater(this::updateNeedsInfo);
                    
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TamagotchiGame game = new TamagotchiGame();
            game.setVisible(true);
        });
    }
}
