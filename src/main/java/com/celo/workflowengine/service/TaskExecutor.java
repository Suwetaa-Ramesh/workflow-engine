package com.celo.workflowengine.service;

import com.celo.workflowengine.model.Task;

public interface TaskExecutor {
    void execute(Task task);
}
