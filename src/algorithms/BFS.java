package algorithms;
import model.*;

import java.util.*;

public class BFS implements PathfindingAlgorithm{
    private Grid grid;
    private Node start;
    private Node goal;

    private Queue<Node> queue;
    private boolean finished = false;

    @Override
    public void initialize(Grid grid, Node start, Node goal) {
        this.grid = grid;
        this.start = start;
        this.goal = goal;

        this.queue = new ArrayDeque<>();

        grid.resetPathfinding();

        start.gCost = 0;
        queue.add(start);
        start.visited = true;
    }
    @Override
    public void step() {

        if (finished || queue.isEmpty()) {
            finished = true;
            return;
        }

        Node current = queue.poll();
        current.closed = true;

        if (current == goal) {
            finished = true;
            reconstructPath(current);
            return;
        }
        List<Node> neighbors = grid.getNeighbors(current);

        for (Node n : neighbors) {

            if (n.visited || n.solid) continue;

            n.visited = true;
            n.parent = current;

            queue.add(n);
        }
    }
    private void reconstructPath(Node node) {
        Node current = node;

        while (current != null) {
            current.open = true;
            current = current.parent;
        }
    }
    @Override
    public boolean isFinished() {
        return finished;
    }

}
