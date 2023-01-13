package com.svalero.relojdigitalpsp.controller;

import com.svalero.relojdigitalpsp.task.StopwatchTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class StopwatchController implements Initializable {

    private Integer delay;
    public TextField tfDelay;
    private StopwatchTask stopwatchTask;
    public Label lbChronoElapsed;
    public StopwatchController(Integer delay) {
        this.delay = delay;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        stopwatchTask = new StopwatchTask();

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        new Thread(stopwatchTask).start();
                    }
                },
                1000L * this.delay);
    }

    public void startChrono() {
        if (stopwatchTask != null)
            stopwatchTask.cancel();
    }

    public void stopChrono() {
        if (stopwatchTask != null)
            stopwatchTask.cancel();
    }

    public void resetChrono() {
        if (stopwatchTask != null)
            stopwatchTask.cancel();
    }
}