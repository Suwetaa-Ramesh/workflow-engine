package com.celo.workflowengine.model;

public enum State {
    SCHEDULED,
    RUNNING,
    RETRY,
    PENDING,
    AWAITING_RETRY,
    CRASHED,
    RETRIEVED_CACHE,
    COMPLETED,
    CANCELED,
    FAILED;
}
