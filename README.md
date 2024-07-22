# Workflow Engine in Java Spring Boot

## Overview

This project is a Workflow Engine built with Java and Spring Boot. It allows for the definition, execution, and management of workflows with various states and conditions. The engine is designed to be flexible and extensible, providing functionality for scheduling tasks, handling retries, and managing task states.

## Features

- Load workflows from JSON files.
- Execute tasks based on their current state.
- Schedule tasks to run at specific times (e.g., 9 AM).
- Retry tasks automatically with configurable retry limits.
- Handle various states including SCHEDULED, RUNNING, RETRY, FAILED, and more.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven (for dependency management and build)

### Installation

1. **Clone the repository:**

   ```bash
    git clone https://github.com/Suwetaa-Ramesh/workflow-engine.git
   ```

2. **Navigate to project folder and build the project:**

```bash
  mvn clean install
```

3. **Run the application:**

```bash
mvn spring-boot:run
```

### Load and Execute a Workflow

- Open Postman and make a GET request to the following URL:

```bash
http://localhost:8080/execute-workflow?workflowFile=src/main/resources/workflow2.json

```

Replace workflow2.json with the path to your workflow JSON file.

Expected Result: The workflow nodes should execute according to the JSON file provided. Check the console or logs for execution details.
