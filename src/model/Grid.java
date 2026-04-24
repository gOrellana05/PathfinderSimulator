package model;

import model.Node;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    Node[][] nodes;

    int rows;
    int cols;
    private Node start;
    private Node goal;


    public Grid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;

        nodes = new Node[cols][rows];

        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                nodes[x][y] = new Node(x, y);
            }
        }
        start = nodes[2][2];
        start.start = true;
        goal = nodes[cols - 5][rows - 5];
        goal.goal = true;
    }
    public Node getNode(int col, int row) {
        if (col < 0 || row < 0 || col >= cols || row >= rows) {
            return null;
        }
        return nodes[col][row];
    }
    public Node[][] getNodes() {
        return nodes;
    }
    public List<Node> getNeighbors(Node node) {

        List<Node> neighbors = new ArrayList<>();

        int col = node.col;
        int row = node.row;

        // arriba
        addIfValid(neighbors, col, row - 1);

        // abajo
        addIfValid(neighbors, col, row + 1);

        // izquierda
        addIfValid(neighbors, col - 1, row);

        // derecha
        addIfValid(neighbors, col + 1, row);

        return neighbors;
    }
    private void addIfValid(List<Node> list, int col, int row) {
        Node n = getNode(col, row);

        if (n != null && n.isWalkable()) {
            list.add(n);
        }
    }
    public void resetPathfinding() {
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                nodes[x][y].resetPathfinding();
            }
        }
    }
    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }
    public void setStart(Node node) {
        clearStart();

        start = node;
        start.start = true;
        start.goal = false;
        start.solid = false;
    }

    public void setGoal(Node node) {
        clearGoal();
        goal = node;
        goal.goal = true;
        goal.start = false;
        goal.solid = false;
    }

    public Node getStart() {
        return start;
    }

    public Node getGoal() {
        return goal;
    }
    public void clearStart() {

        for (Node[] row : nodes) {
            for (Node n : row) {
                n.start = false;
            }
        }

        start = null;
    }

public void clearGoal() {

    for (Node[] row : nodes) {
        for (Node n : row) {
            n.goal = false;
        }
    }

    goal = null;
}
}
