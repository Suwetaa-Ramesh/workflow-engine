package com.celo.workflowengine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private String id;
    private String name;
    private String type;
    private State state;
    private String data;
    private int retryCount;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
