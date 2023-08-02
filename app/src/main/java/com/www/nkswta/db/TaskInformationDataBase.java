package com.www.nkswta.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.wear.tiles.TileBuilders;

import com.www.nkswta.db.entity.TaskInfo;

@Database(entities = {TaskInfo.class}, version = 1)
public abstract class TaskInformationDataBase extends RoomDatabase {

    public abstract TaskInfoDAO getTaskInfoDAO();
}
