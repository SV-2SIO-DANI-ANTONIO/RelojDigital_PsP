package com.svalero.relojdigitalpsp.controller;

import com.svalero.relojdigitalpsp.task.StopwatchTask;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class StopwatchController implements Initializable {

    private static final Logger log = LogManager.getLogger(StopwatchController.class);

    private Integer delay;
    private StopwatchTask stopwatchTask;
    public Label lbChronoElapsed;
    Integer chronoNumber;

    public StopwatchController(Integer delay, Integer chronoNumber) {
        this.delay = delay;
        this.chronoNumber=chronoNumber;
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
                1000 * this.delay);
        stopwatchTask.messageProperty()
                .addListener((observableValue, oldValue, newValue) -> lbChronoElapsed.setText(newValue));

    }

    public void startChrono() {
        if ((stopwatchTask != null)) {
            if ((stopwatchTask.stopwatch.isStarted()) && (stopwatchTask.stopwatch.isSuspended())) {
                stopwatchTask.stopwatch.resume();
            } else if (!(stopwatchTask.stopwatch.isStarted())) {
                stopwatchTask.stopwatch.start();

            }
        }
    }

    public void stopChrono() {
        if ((stopwatchTask != null) && (stopwatchTask.stopwatch.isStarted())) {
            log.info("El cronometro nº" + chronoNumber+ " se ha detenido con " + stopwatchTask.elapsedFormatted + " acumulado");
            stopwatchTask.stopwatch.suspend();
        }
    }

    public void resetChrono() {
        if (stopwatchTask != null)
            stopwatchTask.stopwatch.reset();
    }
}