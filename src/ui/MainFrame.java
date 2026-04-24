package ui;
import model.*;
import engine.*;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private Grid grid;
    private SimulatorPanel simulatorPanel;
    private SimulationEngine engine;
    private ControlPanel controlPanel;

    private Node startNode;
    private Node goalNode;
    public MainFrame(){
        grid = new Grid(30,30);

        startNode = grid.getNode(2, 2);
        goalNode = grid.getNode(25, 25);

        startNode.start = true;
        goalNode.goal = true;

        engine = new SimulationEngine(grid);

        simulatorPanel = new SimulatorPanel(grid,engine);

        controlPanel = new ControlPanel(engine, simulatorPanel, startNode, goalNode);
        setLayout(new BorderLayout());

        add(simulatorPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setTitle("Pathfinding Simulator Pro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
    public static void main(String[] args) {
        new MainFrame();
    }
}
