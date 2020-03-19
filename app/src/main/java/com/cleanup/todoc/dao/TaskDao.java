package com.cleanup.todoc.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao
{
    @Query("SELECT * FROM task")
    LiveData<List<Task>> getTasks();

    @Query("SELECT * FROM task WHERE id = :id")
    Task getTask(long id);

    @Insert
    long insertTask(Task task);

    @Query("DELETE FROM task WHERE id = :id")
    int deleteTask(long id);
}





