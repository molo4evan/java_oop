package ru.nsu.ccfit.molochev.task1;

import java.io.IOException;
import java.util.ArrayList;

public class Field {
    public class Pair{
        private int x;
        private int y;

        private Pair(){}

        Pair(int x_, int y_){
            x = x_;
            y = y_;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private Pair size;
    private Pair AI_pos;
    private boolean drawing;
    private ArrayList<ArrayList<Cell>> current;

    public void assign(int x_, int y_, int AI_x, int AI_y){
        size = new Pair(x_, y_);

        AI_pos = new Pair(AI_x, AI_y);

        current = new ArrayList<>(y_);
        for (int i = 0; i < y_; i++){
            current.add(i, new ArrayList<Cell>());
            for (int j = 0; j < x_; j++){
                current.get(i).add(j, new Cell());
            }
        }

        current.get(AI_pos.y).get(AI_pos.x).state = States.AI;
    }

    public void print(){
        try { Runtime.getRuntime().exec("cls"); }
        catch (IOException e){}
        try { Runtime.getRuntime().exec("clear"); }
        catch (IOException e){}

        System.out.print("+");
        for (int i = 0; i < size.x; i++){
            System.out.print("-");
        }
        System.out.println("+");

        for (int j = 0; j < size.y; j++){
            System.out.print("|");
            for (int i = 0; i < size.x; i++){
                current.get(j).get(i).print();
            }
            System.out.println("|");
        }

        System.out.print("+");
        for (int i = 0; i < size.x; i++){
            System.out.print("-");
        }
        System.out.println("+");
    }

    public void draw(){
        drawing = true;
    }

    public void undraw(){
        drawing = false;
    }

    private States track(){
        if (drawing){
            return States.colored;
        }
        else return States.empty;
    }

    public Pair getSize(){
        return size;
    }

    public Pair getAI_pos(){
        return AI_pos;
    }

    public void setAI_pos(int x_, int y_){
        if (x_ > size.x || y_ > size.y){
            //TODO: exception
        }

        current.get(AI_pos.y).get(AI_pos.x).state = track();

        AI_pos.x = x_;
        AI_pos.y = y_;

        current.get(AI_pos.y).get(AI_pos.x).state = States.AI;
    }
}

enum States{empty, colored, AI};

class Cell{
    States state;

    Cell(){
        state = States.empty;
    }

    void print() {
        if (state == States.empty){
            System.out.print(" ");
        }
        else if (state == States.colored){
            System.out.print(".");
        }
        else if (state == States.AI){
            System.out.print("0");
        }
        else {
            ;       //TODO: exception
        }
    }
}