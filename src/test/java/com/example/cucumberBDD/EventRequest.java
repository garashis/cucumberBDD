package com.example.cucumberBDD;

import java.util.List;

public class EventRequest {
    private String event;
    private List<WorkflowField> workflowFields;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public List<WorkflowField> getWorkflowFields() {
        return workflowFields;
    }

    public void setWorkflowFields(List<WorkflowField> workflowFields) {
        this.workflowFields = workflowFields;
    }
}
