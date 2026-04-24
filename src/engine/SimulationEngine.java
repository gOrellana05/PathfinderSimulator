package engine;
import algorithms.PathfindingAlgorithm;
import model.*;
import javax.swing.Timer;
public class SimulationEngine {
    PathfindingAlgorithm algorithm;
    Grid grid;
    private Timer timer;
    private Node start;
    private Node goal;
    private boolean running = false;

    public SimulationEngine(Grid grid) {
        this.grid = grid;
        start = grid.getStart();
        goal = grid.getGoal();
    }

    public void setAlgorithm(PathfindingAlgorithm algorithm, Node start, Node goal) {
        this.algorithm = algorithm;
        this.algorithm.initialize(grid, start, goal);
    }
    public void start(int delayMs, Runnable repaintCallback) {

        if (algorithm == null) return;

        running = true;

        timer = new Timer(delayMs, e -> {

            if (!algorithm.isFinished()) {
                algorithm.step();
                repaintCallback.run();
            } else {
                timer.stop();
            }

        });

        timer.start();
    }
    public void stop() {
        running = false;

        if (timer != null) {
            timer.stop();
        }
    }
    public void step(Runnable repaintCallback) {
        if (algorithm == null || algorithm.isFinished()) return;

        algorithm.step();
        repaintCallback.run();
    }
    public void reset(Node start, Node goal) {
        stop();

        grid.resetPathfinding();

        if (algorithm != null) {
            algorithm.initialize(grid, start, goal);
        }
    }
    public boolean isRunning() {
        return running;
    }
    public void setStart(Node start) {
        this.start = start;
    }

    public void setGoal(Node goal) {
        this.goal = goal;
    }

    public Node getStart() {
        return start;
    }

    public Node getGoal() {
        return goal;
    }

    public void reset() {

        if (algorithm != null) {

            Node start = grid.getStart();
            Node goal = grid.getGoal();

            algorithm.initialize(grid, start, goal);
        }
    }
    public void reinitialize() {

        if (algorithm != null) {

            Node start = grid.getStart();
            Node goal = grid.getGoal();

            algorithm.initialize(grid, start, goal);
        }
    }
    public Grid getGrid() {
        return grid;
    }
}
