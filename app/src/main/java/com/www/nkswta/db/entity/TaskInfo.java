package com.www.nkswta.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "taskInformation")
public class TaskInfo {

    @ColumnInfo(name = "taskInfo_taskID")
    @PrimaryKey
    @NonNull
    private String taskID;

    @ColumnInfo(name = "taskInfo_taskTitle")
    private String taskTitle;

    @ColumnInfo(name = "taskInfo_priority")
    private String priority;

    @ColumnInfo(name = "taskInfo_taskType")
    private String taskType;

    @ColumnInfo(name = "taskInfo_progress")
    private int progress;

    @Ignore
    public TaskInfo() {

    }

    public TaskInfo(String taskID, String taskTitle, String priority, String taskType, int progress) {
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
