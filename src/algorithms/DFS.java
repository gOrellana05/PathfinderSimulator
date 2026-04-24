package algorithms;

import model.Grid;
import model.Node;

import java.util.*;

public class DFS implements PathfindingAlgorithm{
    private Grid grid;
    private Node start;
    private Node goal;
    private boolean finished = false;
    private Stack<Node> stack;



    @Override
    public void initialize(Grid grid, Node start, Node goal) {
        this.grid = grid;
        this.start = start;
        this.goal = goal;


        this.stack = new Stack<>();


        grid.resetPathfinding();

        start.visited = true;
        stack.push(start);

    }

    @Override
    public void step() {

        if(finished||stack.isEmpty()){
            finished = true;
            return;
        }
        Node current = stack.pop();
        current.closed=true;

        if(current == goal){
            finished = true;
            reconstructPath(current);
            return;
        }

        for(Node n:grid.getNeighbors(current)){
            if(n.visited||n.solid)continue;
            n.visited = true;

            n.parent = current;
            stack.push(n);
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
