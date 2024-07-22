package com.celo.workflowengine.service;

import com.celo.workflowengine.model.State;
import com.celo.workflowengine.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskTypeAExecutor implements TaskExecutor{
    @Override
    public void execute(Task task) {
        System.out.println("Executing TaskTypeA: " + task.getName());
        // Implementation for TaskTypeA
        task.setState(State.COMPLETED);
    }
}
