package ui;
import engine.SimulationEngine;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimulatorPanel extends JPanel {
    private final Grid grid;
    private final SimulationEngine engine;
    private final int nodeSize = 25;

    private EditMode mode = EditMode.WALL;


    public SimulatorPanel(Grid grid, SimulationEngine engine) {
        this.grid = grid;
        this.engine = engine;

        setPreferredSize(new Dimension(
                grid.getCols() * nodeSize,
                grid.getRows() * nodeSize
        ));

        setBackground(Color.BLACK);

        MouseAdapter mouse = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                handleClick(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                handleClick(e);
            }
        };

        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    private void handleClick(MouseEvent e) {

        int col = e.getX() / nodeSize;
        int row = e.getY() / nodeSize;

        Node node = grid.getNode(col, row);

        if (node == null) return;

        switch (mode) {

            case WALL:
                if (!node.start && !node.goal) {
                    node.solid = !node.solid;
                }
                break;

            case START:
                if (node.solid) node.solid = false;
                grid.setStart(node);
                engine.reinitialize();
                break;

            case GOAL:
                if (node.solid) node.solid = false;
                grid.setGoal(node);
                engine.reinitialize();
                break;
        }

        repaint();
    }

    public void setMode(EditMode mode) {
        this.mode = mode;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Node[][] nodes = grid.getNodes();

        for(int x = 0; x < grid.getCols(); x++){
            for(int y = 0; y < grid.getRows(); y++){
                Node node = nodes[x][y];
                if (node.start) {
                    g.setColor(Color.GREEN);
                }
                else if (node.goal) {
                    g.setColor(Color.RED);
                }
                else if (node.solid) {
                    g.setColor(Color.DARK_GRAY);
                }
                else if (node.open) {
                    g.setColor(Color.CYAN);
                }
                else if (node.closed) {
                    g.setColor(Color.ORANGE);
                }
                else if (node.visited) {
                    g.setColor(Color.YELLOW);
                }
                else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(
                        x * nodeSize,
                        y * nodeSize,
                        nodeSize,
                        nodeSize
                );
                g.setColor(Color.BLACK);
                g.drawRect(
                        x * nodeSize,
                        y * nodeSize,
                        nodeSize,
                        nodeSize
                );
            }
        }
    }


}
