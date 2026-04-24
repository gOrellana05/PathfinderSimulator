package algorithms;
import model.Node;
import model.Grid;

public interface PathfindingAlgorithm {
    void initialize(Grid grid, Node start, Node goal);
    void step();
    boolean isFinished();
}
