import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;


public class panel extends JPanel {

    final int maxCol = 20;
    final int maxRow = 20;
    final int nodeSize = 50;
    final int screenWith = nodeSize * maxCol;
    final int screenHeight = nodeSize * maxRow;

    Node[][] node = new Node[maxCol][maxRow];
    Node startNode, goalNode, currentNode;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();
    Queue<Node> nodeQueue = new ArrayDeque<>();
    PriorityQueue<Node> pq = new PriorityQueue<>(
            (a, b) -> a.gCost - b.gCost
    );

    boolean goalReached = false;
    int step = 0;


    public panel() {
        this.setPreferredSize(new Dimension(screenWith, screenHeight));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(maxRow, maxCol, 0, 0));
        this.addKeyListener(new KeyHandler((this)));
        this.setFocusable(true);

        int col = 0;
        int row = 0;

        for (row=0;row < maxRow;row++) {
            for(col=0;col<maxCol;col++) {
                node[row][col] = new Node(row, col);
                node[row][col].gCost = Integer.MAX_VALUE;
                this.add(node[row][col]);
            }
        }

//        int[][] laberinto ={{1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1},
//                            {1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0},
//                            {1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0},
//                            {0, 1, 0, 1, 1, 1, 3, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1},
//                            {1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0},
//                            {1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0},
//                            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1},
//                            {1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1},
//                            {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1},
//                            {1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1},
//                            {0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1},
//                            {1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1},
//                            {1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0},
//                            {1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0},
//                            {0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1},
//                            {1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1},
//                            {1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1},
//                            {0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1},
//                            {1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
//                            {1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
//                            {1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1},
//                            {1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1},
//                            {1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0},
//                            {1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1},
//                            {1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1},
//                            {1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1},
//                            {1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1},
//                            {0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
//                            {1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
//                            {1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1}};
        int[][] laberinto = {
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,2,4,4,1,5,5,5,1,6,6,6,50,4,4,4,4,4,3,1},
                {1,4,1,4,1,5,1,5,1,6,1,6,1,4,1,1,1,4,1,1},
                {1,4,1,4,4,4,1,5,5,5,1,6,4,4,1,7,7,4,4,1},
                {1,4,1,1,1,4,1,1,1,5,1,1,1,4,1,7,1,1,4,1},
                {1,4,4,4,1,4,4,4,1,5,4,4,1,4,4,4,1,4,4,1},
                {1,1,1,4,1,1,1,4,1,1,1,4,1,1,1,4,1,4,1,1},
                {1,4,4,4,4,4,1,4,4,4,1,4,4,4,1,4,1,4,4,1},
                {1,4,1,1,1,4,1,1,1,4,1,1,1,4,1,4,1,1,4,1},
                {1,4,4,4,1,4,4,4,1,4,4,4,1,4,1,4,4,4,4,1},
                {1,1,1,4,1,1,1,4,1,1,1,4,1,4,1,1,1,1,4,1},
                {1,4,4,4,4,4,1,4,4,4,1,4,4,4,4,4,4,1,4,1},
                {1,4,1,1,1,4,1,1,1,4,1,1,1,1,1,1,4,1,4,1},
                {1,4,4,4,1,4,4,4,1,4,4,4,4,4,4,1,4,1,4,1},
                {1,1,1,4,1,1,1,4,1,1,1,1,1,1,4,1,4,1,4,1},
                {1,4,4,4,1,4,4,4,1,4,4,4,4,1,4,4,4,1,4,1},
                {1,4,1,1,1,4,1,1,1,4,1,1,4,1,1,1,4,1,4,1},
                {1,4,4,4,4,4,4,4,4,4,1,4,4,4,4,4,4,1,4,1},
                {1,4,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,1,4,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
        for(int i = 0; i < maxRow; i++){
            for(int j  = 0; j < maxCol; j++){
                if(laberinto[i][j] == 1){
                    setSolidNode(i,j);
                }
                else if(laberinto[i][j] == 2){
                    setStartNode(i,j);
                }
                else if(laberinto[i][j] == 3){
                    setGoalNode(i,j);
                }
                else if(laberinto[i][j] > 3 ){
                    node[i][j].weigth=laberinto[i][j];
                    node[i][j].setText(String.valueOf(laberinto[i][j]));
                }
            }
        }

    }

    private void setStartNode(int col, int row) {
        node[col][row].setAsStart();
        startNode = node[col][row];
        startNode.gCost = 0;
        currentNode = startNode;
    }

    private void setGoalNode(int col, int row) {
        node[col][row].setAsGoal();
        goalNode = node[col][row];
    }

    public void setSolidNode(int i, int j) {
        node[i][j].setAsSolid();
    }

    private void getCost(Node node,Node ant) {
        node.gCost = Math.abs(ant.gCost + 1);
        int xDistance = Math.abs(node.col - goalNode.col);
        int yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;
        node.fCost = node.gCost + node.hCost;

    }
public void search(String algorithm){
        switch(algorithm){
            case "A*": searchAStar();break;
            case "BFS":searchBFS();break;
            case "DFS":searchDFS();break;
            case "Dijkstra":searchDijkstra();break;
            default: System.out.println("Algoritmo invalido");break;

        }

}
    private void searchAStar() {
        Node ant = startNode;
        ant.gCost = 0;
        while (!goalReached && step < 300) {
            int col = currentNode.col;
            int row = currentNode.row;
                currentNode.setAsChecked();
                checkedList.add(currentNode);
                openList.remove(currentNode);


            if (row - 1 >= 0) {
                openNode(node[col][row - 1]);
                getCost(node[col][row-1], ant);
            }
            if (col - 1 >= 0) {
                openNode(node[col - 1][row]);
                getCost(node[col-1][row], ant);
            }
            if (row + 1 < maxRow) {
                openNode(node[col][row + 1]);
                getCost(node[col][row+1], ant);
            }
            if (col + 1 < maxCol) {
                openNode(node[col+1][row]);
                getCost(node[col+1][row], ant);
            }

            int bestNodeIndex = 0;
            int bestNodefCost = 999;
            int bestNodegCost = 999;

            for (int i = 0; i < openList.size(); i++) {
                if (openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                if (openList.get(i).fCost == bestNodefCost) {
                    if(openList.get(i).gCost < bestNodegCost){
                        bestNodeIndex = i;
                        bestNodegCost = openList.get(i).gCost;
                    }
                }
            }
            currentNode = openList.get(bestNodeIndex);
            ant = currentNode;
            if (currentNode == goalNode) {
                goalReached = true;
                trackPath();
            }
            step++;
    }
}
    private void searchDijkstra(){
        Node ant = startNode;
        ant.gCost=0;
        pq.add(ant);
        while(!pq.isEmpty()){
            currentNode = pq.remove();
            if(currentNode.checked)continue;
            currentNode.setAsChecked();
            checkedList.add(currentNode);

            if(currentNode == goalNode){
                goalReached = true;
                trackPath();
                return;
            }
            ArrayList<Node> adyacentes = adyacentesDe(currentNode);
            for(Node w:adyacentes){
                if(w.solid)continue;
                int newCost = currentNode.gCost + w.weigth;
                if(!w.checked) {
                    w.gCost=newCost;
                    w.parent = currentNode;
                    pq.add(w);
                }

            }

        }

    }
    private void searchBFS(){
        Node ant = startNode;
        ant.setAsChecked();
        checkedList.add(ant);
        nodeQueue.add(ant);
        while(!nodeQueue.isEmpty()&&!goalReached){
            currentNode = nodeQueue.remove();
            if(currentNode == goalNode){
                goalReached = true;
                trackPath();
                break;
            }
            ArrayList<Node> adyacentes = adyacentesDe(currentNode);
            for(Node w:adyacentes){
                if(!w.checked && !w.solid) {
                    w.setAsChecked();
                    checkedList.add(w);
                    nodeQueue.add(w);
                    w.parent = currentNode;
                }

            }

        }
    }
    private void searchDFS(){
        Node ant = startNode;
        searchDFS(ant);
    }
    private void searchDFS(Node nodo) {
        nodo.setAsChecked();
        checkedList.add(nodo);
            if(nodo == goalNode){
                goalReached = true;
                trackPath();
                return;
            }
            ArrayList<Node> adyacentes = adyacentesDe(nodo);
            for(Node w:adyacentes){
                if(!w.checked && !w.solid) {
                    w.parent = nodo;
                    searchDFS(w);
                }

            }

    }
    private ArrayList<Node> adyacentesDe(Node nodo){
        ArrayList<Node> adyacentes = new ArrayList<>();
        int col = nodo.col;
        int row = nodo.row;

        if (row - 1 >= 0) {
            adyacentes.add(node[col][row - 1]);

        }
        if (col - 1 >= 0) {
            adyacentes.add(node[col - 1][row]);


        }
        if (row + 1 < maxRow) {
            adyacentes.add(node[col][row + 1]);

        }
        if (col + 1 < maxCol) {
            adyacentes.add(node[col+1][row]);

        }
        return adyacentes;

    }
    private void openNode(Node node){
        if(!node.open && !node.checked && !node.solid){
            node.setAsOpen();

            node.parent = currentNode;
            openList.add(node);
        }

    }
    private void trackPath(){
        Node current = goalNode;
        while(current != startNode){
            current = current.parent;
            if(current != startNode){
                current.setAsPath();
            }
        }
    }
}
