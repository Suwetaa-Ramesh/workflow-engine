package com.celo.workflowengine.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TaskExecutorFactory {
    private static final Map<String, TaskExecutor> executors = new HashMap<>();

    static {
        executors.put("taskTypeA", new TaskTypeAExecutor());
        executors.put("taskTypeB", new TaskTypeBExecutor());
        // add more task types as needed
    }

    public static TaskExecutor getExecutor(String type) {
        return executors.getOrDefault(type, new DefaultTaskExecutor());
    }
}
