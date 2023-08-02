package com.www.nkswta.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.android.gms.tasks.Task;
import com.www.nkswta.db.entity.TaskInfo;

import java.util.List;

@Dao
public interface TaskInfoDAO {

    @Insert
    public long addTaskInfo(TaskInfo taskInfo);

    @Update
    public void updateTaskInfo(TaskInfo taskInfo);

    @Delete
    public void deleteTaskInfo(TaskInfo taskInfo);

    @Query("Select * from taskInformation")
    public List<TaskInfo> getAllTasksInfo();

    @Query("Select * from taskInformation where taskInfo_taskID ==:taskID")
    public TaskInfo getTaskInfoByID(String taskID);
}
