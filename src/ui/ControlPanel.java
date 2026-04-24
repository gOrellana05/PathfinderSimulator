package ui;
import javax.swing.*;
import java.awt.*;
import engine.*;
import model.*;
import algorithms.*;
public class ControlPanel extends JPanel{
    private SimulationEngine engine;
    private SimulatorPanel panel;

    private JComboBox<String> algorithmSelector;

    public ControlPanel(SimulationEngine engine, SimulatorPanel panel,
                        Node start, Node goal) {

        this.engine = engine;
        this.panel = panel;

        setLayout(new FlowLayout());
        setBackground(Color.DARK_GRAY);
        algorithmSelector = new JComboBox<>(new String[]{
                "Dijkstra",
                "BFS",
                "DFS",
                "A*"
        });
        add(algorithmSelector);

        JButton startBtn = new JButton("START");
        startBtn.addActionListener(e -> {

            String algo = (String) algorithmSelector.getSelectedItem();
            Node startnode = engine.getGrid().getStart();
            Node goalnode = engine.getGrid().getGoal();

            switch (algo) {
                case "BFS":
                    engine.setAlgorithm(new BFS(), startnode, goalnode);
                    break;

                case "DFS":
                    engine.setAlgorithm(new DFS(), startnode, goalnode);
                    break;

                case "A*":
                    engine.setAlgorithm(new AStar(), startnode, goalnode);
                    break;
            }

            engine.start(50, panel::repaint);
        });

        add(startBtn);
        JButton stepBtn = new JButton("STEP");
        stepBtn.addActionListener(e -> {
            engine.step(panel::repaint);
        });
        add(stepBtn);

        JButton stopBtn = new JButton("STOP");
        stopBtn.addActionListener(e -> {
            engine.stop();
        });
        add(stopBtn);

        JButton resetBtn = new JButton("RESET");
        resetBtn.addActionListener(e -> {
            engine.reinitialize();
            panel.repaint();
        });
        add(resetBtn);
        JButton wallBtn = new JButton("WALL");
        wallBtn.addActionListener(e -> panel.setMode(EditMode.WALL));

        JButton startMode = new JButton("START NODE");
        startMode.addActionListener(e -> panel.setMode(EditMode.START));

        JButton goalBtn = new JButton("GOAL NODE");
        goalBtn.addActionListener(e -> panel.setMode(EditMode.GOAL));

        add(wallBtn);
        add(startMode);
        add(goalBtn);
    }
}
