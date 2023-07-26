package com.www.nkswta;

public class ModalClass {
    private String taskID, taskTitle, priority, taskType;
    private int progress;


    public ModalClass(String taskID, String taskTitle, String priority, String taskType, int progress) {
        this.taskID = taskID;
        this.taskTitle = taskTitle;
        this.priority = priority;
        this.taskType = taskType;
        this.progress = progress;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}




