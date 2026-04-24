package algorithms;
import model.*;
import java.util.*;

public class AStar implements PathfindingAlgorithm{
    private Grid grid;
    private Node start;
    private Node goal;
    private boolean finished = false;
    private PriorityQueue<Node> openSet;
    @Override
    public void initialize(Grid grid, Node start, Node goal) {

        this.grid = grid;
        this.start = start;
        this.goal = goal;

        finished = false;

        grid.resetPathfinding();

        openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.fCost));

        start.gCost = 0;
        start.hCost =  heuristic(start, goal);
        start.fCost = start.gCost + start.hCost;

        openSet.add(start);
    }
    @Override
    public void step() {

        if (finished || openSet.isEmpty()) {
            finished = true;
            return;
        }

        Node current = openSet.poll();
        current.closed = true;


        if (current == goal) {
            finished = true;
            reconstructPath(current);
            return;
        }

        for (Node n : grid.getNeighbors(current)) {

            if (n.solid ) continue;

            int newG = current.gCost + 1;

            if (newG < n.gCost) {

                n.gCost = newG;
                n.hCost = (int) Math.round(heuristic(n, goal));
                n.fCost = n.gCost + n.hCost;

                n.visited = true;
                n.parent = current;

                if(!openSet.contains(n)){
                    openSet.add(n);
                } else {
                    openSet.remove(n);
                    openSet.add(n);
                }

            }
        }
    }
    private int heuristic(Node a, Node b) {
        return Math.abs(a.col-b.col) + Math.abs(a.row - b.row);
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
