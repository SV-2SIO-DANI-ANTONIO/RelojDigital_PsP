package com.svalero.relojdigitalpsp.controller;

import com.svalero.relojdigitalpsp.task.TimerTask;
import javafx.concurrent.Worker;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class TimerController implements Initializable {

    private static final Logger log = LogManager.getLogger(TimerController.class);
    public Label lbTimerTime;
    public Label lbInitialTime;
    private Integer delay = 0;
    private Integer timeout;
    Integer timerNumber;
    private TimerTask timerTask;

    public TimerController(Integer delay, Integer timeout, Integer timerNumber) {
        this.delay = delay;
        this.timeout = timeout;
        this.timerNumber = timerNumber;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        timerTask = new TimerTask(delay);
        lbTimerTime.setText(String.valueOf(delay));
        lbInitialTime.setText(String.valueOf(delay));
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        new Thread(timerTask).start();
                    }
                },
                1000 * this.timeout);
        timerTask.stateProperty().addListener((observableValue, oldState, newState) -> {
            System.out.println(observableValue.toString());
            if (newState == Worker.State.SUCCEEDED) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("El timer " + timerNumber + " ha finalizado");
                lbTimerTime.setStyle("-fx-text-fill: red");
                alert.show();
            }
        });
        timerTask.messageProperty()
                .addListener((observableValue, oldValue, newValue) -> lbTimerTime.setText(newValue));
    }

    public void startTimer() {
        if ((timerTask != null)) {
            if ((timerTask.stopwatch.isStarted()) && (timerTask.stopwatch.isSuspended())) {
                timerTask.stopwatch.resume();
            } else if (!(timerTask.stopwatch.isStarted())) {
                timerTask.stopwatch.start();

            }
        }
    }

    public void stopTimer() {
        if ((timerTask != null) && (timerTask.stopwatch.isStarted()))
            timerTask.stopwatch.suspend();
    }

    public void resetTimer() {
        if (timerTask != null)
            timerTask.stopwatch.reset();
    }
}