package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Node{
    public Node parent = null;

    //position
    public int col;
    public int row;

    public int gCost = Integer.MAX_VALUE;//coste desde inicio
    public int hCost = 0;//heuristica
    public int fCost = Integer.MAX_VALUE;//g+h

    public int terrainCost = 1;

    public boolean start = false;
    public boolean goal = false;
    public boolean solid = false;

    public boolean open = false;
    public boolean closed = false;
    public boolean visited = false;

    public Node(int col, int row){
        this.col = col;
        this.row = row;
    }
    public void setAsStart(){
        this.start=true;
    }
    public void setAsGoal(){
        this.goal=true;
    }
    public void setAsSolid(){
        this.solid=true;
    }
    public void setAsOpen(){

        this.open=true;
    }
    public boolean isWalkable(){
        return !solid;
    }
    public void resetPathfinding() {
        open = false;
        closed = false;
        visited = false;

        gCost = 1000000;
        hCost = 0;
        fCost = 1000000;

        parent = null;
    }
    public int calculateFCost() {
        this.fCost = this.gCost + this.hCost;
        return this.fCost;
    }
    @Override
    public String toString() {
        return "(" + col + "," + row + ")";
    }

}
