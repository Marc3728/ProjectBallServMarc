package com.example.projectballservmarc;

import com.example.projectballservmarc.elements.Position;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientController {
    @FXML
    private Label welcomeText;

    @FXML
    private ImageView imagenpelotacliente;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    public void onRunClientButtonClick() {

         Thread t =  new Thread(new Runnable() {
            @Override
            public void run() {
                int numPuerto = 8100;
                String host = "127.0.0.1";


                try (
                        Socket echoSocket = new Socket(host, numPuerto);
                        ObjectOutputStream oos = new ObjectOutputStream(echoSocket.getOutputStream());
                        ObjectInputStream ois = new ObjectInputStream( echoSocket.getInputStream());
                        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
                ) {
                    String userInput;
                    Position pos;
                    System.out.println("hola");
                    while ((pos = ((Position) ois.readObject())) != null) {
                        System.out.println("echo: " + pos.toString());
                        imagenpelotacliente.setLayoutX(pos.getLayoutX());
                        imagenpelotacliente.setLayoutY(pos.getLayoutY());
                    }
                } catch (UnknownHostException e) {
                    System.err.println("Don't know about host " + host);
                    System.exit(1);
                } catch (IOException e) {
                    System.err.println("Couldn't get I/O for the connection to " +
                            host);
                    System.exit(1);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();

    }

}
