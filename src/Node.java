import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Node extends JButton implements ActionListener {
    Node parent;
    int col;
    int row;
    int gCost;
    int hCost;
    int fCost;
    int weigth;
    boolean start;
    boolean goal;
    boolean solid;
    boolean open;
    boolean checked;
    boolean marcada = false;

    public Node(int col, int row){
        this.col = col;
        this.row = row;
        setMinimumSize(new Dimension(32, 32));
        setPreferredSize(new Dimension(32, 32));
        setMaximumSize(new Dimension(32, 32));
        setFont(new Font("Monospaced", Font.PLAIN, 10));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setBackground(Color.white);
        setForeground(Color.black);
        addActionListener(this);
    }
    public void setAsStart(){
        setBackground(Color.blue);
        setForeground(Color.white);
        setText("Start");
        start = true;
    }
    public void setAsGoal(){
        setBackground(Color.yellow);
        setForeground(Color.black);
        setText("Goal");
        goal = true;
    }
    public void setAsSolid(){
        setBackground(Color.gray);
        setForeground(Color.black);
        solid = true;
    }
    public void setAsOpen(){
        open = true;
    }
    public void setAsChecked(){
        if(!start && !goal){
            setBackground(Color.orange);
            setForeground(Color.black);
        }
        checked = true;
    }
    public void setAsPath(){
        setBackground(Color.green);
        setForeground(Color.black);
    }
    public void setAsMarcada(){
        setBackground(Color.orange);
        setForeground(Color.black);
        marcada = true;
    }
    public void actionPerformed(ActionEvent e){
        setAsMarcada();
    }
}
