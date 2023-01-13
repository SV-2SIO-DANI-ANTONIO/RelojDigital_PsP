package com.svalero.relojdigitalpsp.task;

import javafx.concurrent.Task;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;

public class TimerTask extends Task<Integer> {
    private Integer delay;
    public StopWatch stopwatch = new StopWatch();
    public long tRemaining;

    public TimerTask(Integer delay) {
        this.delay = delay;
    }

    @Override
    protected Integer call() throws Exception {
        updateProgress(0, 1);
        tRemaining = delay;
        stopwatch.start();
        do {
            Thread.sleep(200);
            tRemaining = (delay * 1000) - stopwatch.getTime(TimeUnit.MILLISECONDS);
            String tRemainingDuration = DurationFormatUtils.formatDuration(tRemaining, "HH:mm:ss.SSS");
            String tRemainingFormatted = tRemainingDuration.substring(3, Math.min(tRemainingDuration.length(), 8));
            updateMessage(tRemainingFormatted);
        } while (tRemaining > 0);


        updateProgress(1, 1);
        updateMessage("00:00");
        return null;
    }
}
