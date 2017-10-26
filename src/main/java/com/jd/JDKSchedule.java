package com.jd;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by liushilang on 2017/9/6.
 */
public class JDKSchedule {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        final ScheduledFuture<?> schedule = executorService.schedule(new Runnable() {
            public void run() {
                System.out.println("60 seconds later");
            }
        }, 60, TimeUnit.SECONDS);
        executorService.shutdown();
    }
}
