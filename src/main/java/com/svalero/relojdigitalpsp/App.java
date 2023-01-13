package com.svalero.relojdigitalpsp;

import com.svalero.relojdigitalpsp.controller.AppController;
import com.svalero.relojdigitalpsp.util.R;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("digitalClock.fxml"));
        loader.setController(new AppController());
        ScrollPane vbox = loader.load();

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("Digital Clock");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}

