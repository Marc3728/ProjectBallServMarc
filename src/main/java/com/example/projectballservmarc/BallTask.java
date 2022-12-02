package com.example.projectballservmarc;

import com.example.projectballservmarc.elements.Direccion;
import com.example.projectballservmarc.elements.Position;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class BallTask extends Task<Position> implements Serializable {

        private Position position;
        private Direccion direccion;
        private ImageView ball;

        public BallTask(ImageView balld){
            ball = balld;
            position = new Position(250,270);
            direccion = null;
            valueProperty().addListener(new ChangeListener<Position>() {
                @Override
                public void changed(ObservableValue<? extends Position> observableValue, Position position, Position t1) {
                    ball.setLayoutX(t1.getLayoutX());
                    ball.setLayoutY(t1.getLayoutY());
                }
            });
        }

        @Override
        protected Position call() throws Exception {
            try {
                while (true){

                    position.chanegXY();
                    Thread.sleep(100);
                    updateValue(new Position(position.getLayoutX(),position.getLayoutY()));
                }

            } catch (Exception e){
                e.printStackTrace();
            }

            return position;
        }

        public Position getPosition() {
            return position;
        }
}

