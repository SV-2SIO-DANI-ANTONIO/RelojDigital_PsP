package com.svalero.relojdigitalpsp.controller;

import com.svalero.relojdigitalpsp.task.StopwatchTask;
import com.svalero.relojdigitalpsp.task.TimerTask;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TimerController implements Initializable {

    private Integer delay;

    public TextField tfTimerTime;
    private TimerTask timerTask;
    public Label lbChronoElapsed;

    public TimerController(Integer delay) {
        this.delay = delay;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        timerTask = new TimerTask(0);

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        new Thread(timerTask).start();
                    }
                },
                1000L * this.delay);
    }

    public void startChrono() {
        if (timerTask != null)
            timerTask.cancel();
    }

    public void stopChrono() {
        if (timerTask != null)
            timerTask.cancel();
    }

    public void resetChrono() {
        if (timerTask != null)
            timerTask.cancel();
    }
}