package com.celo.workflowengine.service;

import com.celo.workflowengine.model.State;
import com.celo.workflowengine.model.Task;
import com.celo.workflowengine.model.Workflow;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class WorkflowService {
    private final ObjectMapper objectMapper;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);

    public WorkflowService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Workflow loadWorkflow(String workflowFile) throws IOException {
        return objectMapper.readValue(new File(workflowFile), Workflow.class);
    }

    public void executeWorkflow(Workflow workflow) {
        workflow.getTasks().forEach(this::handleTaskState);
    }

    private void handleTaskState(Task task) {
        switch (task.getState()) {
            case SCHEDULED:
                scheduleTaskAt9am(task);
                task.setState(State.RUNNING);
                executeTask(task);
                break;
            case RUNNING:
                // Logic for running tasks
                break;
            case RETRY:
                retryTask(task);
                break;
            case PENDING:
                // Logic for pending tasks
                break;
            case AWAITING_RETRY:
                // Logic for awaiting retry tasks
                break;
            case CRASHED:
                // Logic for crashed tasks
                break;
            case RETRIEVED_CACHE:
                // Logic for retrieved cache tasks
                break;
            case COMPLETED:
                // Logic for completed tasks
                break;
            case CANCELED:
                // Logic for canceled tasks
                break;
            case FAILED:
                // Logic for failed tasks
                break;
        }
    }

    private void executeTask(Task task) {
        TaskExecutor executor = TaskExecutorFactory.getExecutor(task.getType());
        try {
            executor.execute(task);
            task.setState(State.COMPLETED);
        } catch (Exception e) {
            if (task.getRetryCount() < 3 && task.getState() != State.CRASHED) {
                task.setRetryCount(task.getRetryCount() + 1);
                task.setState(State.RETRY);
            } else if (task.getState() == State.PENDING){
                    System.out.println("skndfbdsb");
            } else {
                task.setState(State.FAILED);
            }
        }
    }

    private void scheduleTaskAt9am(Task task) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next9am = now.withHour(9).withMinute(0).withSecond(0).withNano(0);
        if (now.isAfter(next9am)) {
            next9am = next9am.plusDays(1);
        }
        long delay = now.until(next9am, ChronoUnit.SECONDS);
        scheduler.schedule(() -> {
            try {
                executeTask(task);
                task.setState(State.COMPLETED);
            } catch (Exception e) {
                task.setState(State.FAILED);
            }
        }, delay, TimeUnit.SECONDS);
    }

    private void retryTask(Task task) {
        scheduler.schedule(() -> executeTask(task), 1, TimeUnit.MINUTES);
    }
}
