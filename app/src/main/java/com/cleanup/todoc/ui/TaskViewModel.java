package com.cleanup.todoc.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.cleanup.todoc.Repositories.ProjectDataRepository;
import com.cleanup.todoc.Repositories.TaskDataRepository;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel
{

    // REPOSITORIES
    private final TaskDataRepository taskDataSource;
    private final ProjectDataRepository projectDataSource;
    private final Executor executor;

    // DATA
    @Nullable
    private LiveData<Project> currentProjects;

    public TaskViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {
        this.taskDataSource = taskDataSource;
        this.projectDataSource = projectDataSource;
        this.executor = executor;
    }

    public void init() {
        if (this.currentProjects != null) {
            return;
        }
        currentProjects = projectDataSource.getProjects();
    }

    // -------------
    // FOR PROJECT
    // -------------

    public LiveData<Project> getProjects() { return this.currentProjects;  }

    // -------------
    // FOR TASK
    // -------------

    public LiveData<List<Task>> getTasks() {
        return taskDataSource.getTasks();
    }

    public long createTask(Task task) {
        executor.execute(() -> taskDataSource.createTask(task));
        return task.getId();
    }

    public void deleteTask(long id) {
        executor.execute(() -> taskDataSource.deleteTask(id));
    }
}