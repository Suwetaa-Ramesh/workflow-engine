package com.celo.workflowengine.service;

import com.celo.workflowengine.model.State;
import com.celo.workflowengine.model.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class SchedulerService {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);

    public void scheduleTask(Task task, LocalDateTime startTime) {
        long delay = LocalDateTime.now().until(startTime, ChronoUnit.SECONDS);
        scheduler.schedule(() -> {
            try {
                TaskExecutor executor = TaskExecutorFactory.getExecutor(task.getType());
                executor.execute(task);
                task.setState(State.COMPLETED);
            } catch (Exception e) {
                task.setState(State.FAILED);
            }
        }, delay, TimeUnit.SECONDS);
    }
}
