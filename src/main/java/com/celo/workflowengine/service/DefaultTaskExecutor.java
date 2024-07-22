package com.celo.workflowengine.service;

import com.celo.workflowengine.model.State;
import com.celo.workflowengine.model.Task;
import org.springframework.stereotype.Component;

@Component
public class DefaultTaskExecutor implements TaskExecutor{
    @Override
    public void execute(Task task) {

        System.out.println("Executing default task: " + task.getName());
        task.setState(State.COMPLETED);
    }
}
