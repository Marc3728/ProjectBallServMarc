package com.example.projectballservmarc;

import com.example.projectballservmarc.elements.Position;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientConection extends Thread{
    private ServerSocket socket;
    private BallTask balltask;
    private boolean conected;
    private boolean repetido;


    public ClientConection(ServerSocket socket,BallTask pelota){
        this.socket = socket;
        this.balltask = pelota;
    }

    @Override
    public void run() {
        repetido = true;
        try (Socket socketComunicacion = socket.accept()) {
            System.out.printf("Cliente conectado desde %s:%d.\n",
                    socketComunicacion.getInetAddress().getHostAddress(),
                    socketComunicacion.getPort());
            try (OutputStream os = socketComunicacion.getOutputStream();
                 ObjectOutputStream oos = new ObjectOutputStream(os)) {
                conected = true;
                while (true){
                    try {
                        Thread.sleep(100);
                        Position pos = new Position(balltask.getPosition().getLayoutX(),balltask.getPosition().getLayoutY());
                        System.out.println(pos);
                        oos.writeObject(pos);

                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public boolean getConection(){
        return conected;
    }

    public boolean getRepetido(){
        return repetido;
    }
}
