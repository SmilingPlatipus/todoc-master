package com.cleanup.todoc.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.cleanup.todoc.model.Project;

@Dao
public interface ProjectDao
{
    @Query("SELECT * FROM project")
    LiveData<Project> getProjects();

    // FOR TESTS

    @Insert
    long insertProject(Project project);
}
