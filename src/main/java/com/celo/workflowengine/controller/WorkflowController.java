package com.celo.workflowengine.controller;


import com.celo.workflowengine.model.Workflow;
import com.celo.workflowengine.service.WorkflowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WorkflowController {
    private final WorkflowService workflowService;

    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @GetMapping("/execute-workflow")
    public String executeWorkflow(@RequestParam String workflowFile) {
        try {
            Workflow workflow = workflowService.loadWorkflow(workflowFile);
            workflowService.executeWorkflow(workflow);
            return "Workflow executed successfully";
        } catch (IOException e) {
            return "Failed to load workflow: " + e.getMessage();
        }
    }
}
