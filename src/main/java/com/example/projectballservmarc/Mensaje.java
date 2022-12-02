package com.example.projectballservmarc;

import java.io.Serializable;

public class Mensaje implements Serializable {

    private String peticion;

    public Mensaje(String peticion){
        this.peticion = peticion;
    }

    public String getPeticion() {
        return peticion;
    }

    public void setPeticion(String peticion) {
        this.peticion = peticion;
    }
}
