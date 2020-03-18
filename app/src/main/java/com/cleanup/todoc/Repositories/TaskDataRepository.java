package com.cleanup.todoc.Repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository
{
    private final TaskDao taskDao;

    public TaskDataRepository(TaskDao taskDao) { this.taskDao = taskDao; }

    // --- GET ---

    public LiveData<List<Task>> getTasks(){ return this.taskDao.getTasks(); }

    // --- CREATE ---

    public long createTask(Task task){ return taskDao.insertTask(task); }

    // --- DELETE ---
    public void deleteTask(long id){ taskDao.deleteTask(id); }

}
