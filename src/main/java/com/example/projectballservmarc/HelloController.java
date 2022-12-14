package com.example.projectballservmarc;

import com.example.projectballservmarc.elements.Direccion;
import com.example.projectballservmarc.elements.Position;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private ImageView imagenpelota;

    Thread t;
    BallTask ballTask;
    Thread recibirconexiones;
    @FXML
    protected void onRunButtonClick() {
            if (t==null){
                ballTask =  new BallTask(imagenpelota);
                t =  new Thread(ballTask);
                t.start();
            } else {
                ballTask.changeState();
            }


            if (recibirconexiones==null){
                recibirconexiones = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int numPuerto = 8100;

                        try (ServerSocket socketServidor = new ServerSocket(numPuerto)) {

                            System.out.printf("Creado socket de servidor en puerto %d. Esperando conexiones de clientes.\n", numPuerto);

                            ClientConection clientConection =  new ClientConection(socketServidor,ballTask);
                            while (true) {    // Acepta una conexión de cliente tras otra

                                if(!clientConection.getConection() && !clientConection.getRepetido()){
                                    t = new Thread(clientConection);
                                    t.start();
                                    System.out.println("CREADA CONEXION");
                                }else if (clientConection.getConection()){
                                    clientConection = new ClientConection(socketServidor, ballTask);
                                    System.out.println("ACTUALIZADA CONEXION");
                                }


                            }

                        } catch (IOException ex) {

                            System.out.println("Excepción de E/S");

                            ex.printStackTrace();

                            System.exit(1);

                        }
                    }
                });
                recibirconexiones.start();
            }


    }








}