package com.celo.workflowengine.service;

import com.celo.workflowengine.model.State;
import com.celo.workflowengine.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskTypeBExecutor implements TaskExecutor {
    @Override
    public void execute(Task task) {
        System.out.println("Executing TaskTypeB: " + task.getName());
        // Implementation for TaskTypeB
        task.setState(State.COMPLETED);
    }
}