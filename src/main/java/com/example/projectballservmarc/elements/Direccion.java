package com.example.projectballservmarc.elements;

import java.io.Serializable;

public enum Direccion implements Serializable {
    ARRIBA(-10),
    ABAJO(+10),
    DERECHA(+10),
    IZQUIERDA(-10);

    private int value;
    Direccion(int i) {
        value = i;
    }

    public int getValue() {
        return value;
    }
}
