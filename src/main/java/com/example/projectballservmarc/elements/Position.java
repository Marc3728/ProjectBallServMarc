package com.example.projectballservmarc.elements;

import java.io.Serializable;

public class Position implements Serializable {
    private float layoutY;
    private float layoutX;
    private boolean arrancado;

    private Direccion direccionx;
    private Direccion direcciony;

    public Position(float layoutX,float layoutY){
        this.layoutX = layoutX;
        this.layoutY = layoutY;

        direccionx=Direccion.DERECHA;
        direcciony=Direccion.ABAJO;

        arrancado=true;
    }

    public void setLayoutY(float layoutY) {
        this.layoutY = layoutY;
    }

    public void setLayoutX(float layoutX) {
        this.layoutX = layoutX;
    }

    public float getLayoutY() {
        return layoutY;
    }

    public float getLayoutX() {
        return layoutX;
    }

    public void chanegXY(){
        if (this.getLayoutX()>500){
            direccionx = Direccion.IZQUIERDA;
            System.out.println("toca derecha");
        } else if (this.getLayoutY()>500){
            direcciony = Direccion.ARRIBA;
            System.out.println("toca debajo");
        } else if ( this.getLayoutX()<0){
            direccionx = Direccion.DERECHA;
            System.out.println("toda izquierda");
        } else if (this.getLayoutY()<0){
            direcciony = Direccion.ABAJO;
            System.out.println("toca arriba");
        }

        this.setLayoutX(this.getLayoutX()+direccionx.getValue());
        this.setLayoutY(this.getLayoutY()+direcciony.getValue());
    }

    public boolean getArrancado() {
        return arrancado;
    }

    public void changeState(){
        if(arrancado)
            arrancado=false;
        else
            arrancado=true;
    }



    @Override
    public String toString() {
        return "Position{" + layoutY + ", " + layoutX + '}';
    }
}
