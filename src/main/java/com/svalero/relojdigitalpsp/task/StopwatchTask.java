package com.svalero.relojdigitalpsp.task;

import com.svalero.relojdigitalpsp.controller.StopwatchController;
import javafx.concurrent.Task;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class StopwatchTask extends Task<Integer> {
    private static final Logger log = LogManager.getLogger(StopwatchController.class);
    public StopWatch stopwatch = new StopWatch();

    @Override
    protected Integer call() throws Exception {
        System.out.println("stopwatch on");
        stopwatch.start();
        while (true) {

            String elapsed = DurationFormatUtils.formatDuration(stopwatch.getTime(), "HH:MM:SS.SSS");
            String elapsedFormatted = elapsed.substring(3, Math.min(elapsed.length(), 8));
            System.out.println(elapsedFormatted);
            updateMessage(elapsedFormatted);

        }

    }
}



