package com.svalero.relojdigitalpsp.task;

import javafx.concurrent.Task;

public class TimerTask extends Task<Integer> {
    private Integer delay;

    public TimerTask(Integer delay) {
        this.delay = delay;
    }

    @Override
    protected Integer call() throws Exception {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                    //TODO FIN DEL TIMER

                    }
                },
                1000L * this.delay);
        return null;
    }
}
