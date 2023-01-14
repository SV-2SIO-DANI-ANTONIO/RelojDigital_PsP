package com.svalero.relojdigitalpsp.controller;

import com.svalero.relojdigitalpsp.util.R;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.awt.*;
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
    public TextField tfStartTimerTime;
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
            posStopwatch++;
            StopwatchController stopwatchController = new StopwatchController(Integer.parseInt(timeout), posStopwatch);
            loader.setController(stopwatchController);
            VBox stopwatchBox = loader.load();
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

    public void startTimer(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI("timerTab.fxml"));
            String timeout = tfSchedule.getText();
            if (timeout.length() == 0)
                timeout = "0";
            String delay = tfStartTimerTime.getText();
            if (delay.length() == 0)
                delay = "0";
            posTimer++;
            TimerController timerController = new TimerController(Integer.parseInt(delay), Integer.parseInt(timeout), posTimer);
            allTimers.put(posTimer, timerController);
            loader.setController(timerController);
            VBox timerBox = loader.load();
            tabPane.getTabs().add(new Tab("Timer nº " + posTimer, timerBox));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void stopAllTimers(ActionEvent event) {
        for (TimerController timerController : allTimers.values())
            timerController.stopTimer();
    }


    //TODO LOG
    @FXML
    public void showLog(ActionEvent actionEvent) throws IOException, IllegalArgumentException {
        if (Desktop.isDesktopSupported()) {
            try {
                File file = new File("log" + File.separator + "RelojDigital.log");
                Desktop.getDesktop().open(file);
            } catch (IOException ioe) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Ha habido un error.");
                alert.show();
            } catch (IllegalArgumentException iae) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Ha habido un error al abrir el log. Es posible que no exista.");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("No soportado.");
            alert.show();
        }

    }
}
