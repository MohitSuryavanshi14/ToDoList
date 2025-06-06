package com.example.todo_list;

public class Task {
    private int id;
    private String taskDescription;
    private boolean isCompleted;

    public Task(int id, String taskDescription, boolean isCompleted) {
        this.id = id;
        this.taskDescription = taskDescription;
        this.isCompleted = isCompleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
