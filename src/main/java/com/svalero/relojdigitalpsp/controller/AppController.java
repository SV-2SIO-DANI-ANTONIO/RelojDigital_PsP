package com.svalero.relojdigitalpsp.controller;

import com.svalero.relojdigitalpsp.util.R;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AppController {
    public Label lbClock;
    public TextField tfSchedule;
    private Map<Integer, StopwatchController> allStopwatches;
    private Map<Integer, TimerController> allTimers;
    public TextArea logArea;
    public TabPane tabPane;
    public Integer posStopwatch = 0;
    public Integer posTimer = 0;

    public AppController() {
        allStopwatches = new HashMap<>();
        allTimers = new HashMap<>();
    }

    @FXML
    public void initialize() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e ->
                lbClock.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        ),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @FXML
    public void startChrono(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI("chronoTab.fxml"));
            String timeout = tfSchedule.getText();
            if (timeout.length() == 0)
                timeout = "0";

            StopwatchController stopwatchController = new StopwatchController(Integer.parseInt(timeout));
            loader.setController(stopwatchController);
            VBox stopwatchBox = loader.load();
            posStopwatch++;
            allStopwatches.put(posStopwatch, stopwatchController);
            tabPane.getTabs().add(new Tab("Chrono nº " + posStopwatch, stopwatchBox));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void stopAllChronos(ActionEvent event) {
        for (StopwatchController stopwatchController : allStopwatches.values())
            stopwatchController.stopChrono();
    }


    //TODO TIMERS
    public void startTimer(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI("timerTab.fxml"));
            String timeout = tfSchedule.getText();
            if (timeout.length() == 0)
                timeout = "0";

            TimerController timerController = new TimerController(Integer.parseInt(timeout));
            loader.setController(timerController);
            VBox timerBox = loader.load();
            posTimer++;
            allTimers.put(posTimer, timerController);
            tabPane.getTabs().add(new Tab("Timer nº " + posTimer, timerBox));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void stopAllTimers(ActionEvent event) {
        for (StopwatchController stopwatchController : allStopwatches.values())
            stopwatchController.stopChrono();
    }


    //TODO LOG
    @FXML
    public void showLog(ActionEvent event) {
        try {
            // Quitar comentario para descarga desde fichero "dlc.txt".
            File logFile = new File("multidescargas.log");

            // Quitar comentario para descarga desde el fichero que el usuario seleccione.
            //FileChooser fileChooser = new FileChooser();
            //File dlcFile = fileChooser.showOpenDialog(tfUrl.getScene().getWindow());
            if (logFile == null)
                return;

            // Leer fichero
            Scanner reader = new Scanner(logFile);
            String allText = "";
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
                allText += data + "\n";
                // Lanzar controlador y descarga
            }
            logArea.setText(allText);
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Se ha producido un error");
            e.printStackTrace();
        }
    }
}
