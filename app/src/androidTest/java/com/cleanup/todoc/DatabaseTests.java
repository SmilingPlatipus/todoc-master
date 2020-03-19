package com.cleanup.todoc;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.content.ContentValues;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.db.TodocDb;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DatabaseTests
{
    private TodocDb database;
    private static long PROJECT_ID = 1L;
    private static Task demoTask = new Task(PROJECT_ID, "Demo Task", new Date().getTime());
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                                                     TodocDb.class)
                .allowMainThreadQueries()
                .build();

        // Create Projet Tartampion for testing purposes

        this.database.projectDao().insertProject(new Project(1L,"Projet Tartampion",0xFFEADAD1));
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void insertAndGetUser() throws InterruptedException {
        // BEFORE : Adding a new user and keeping its ID to retrieve it later
        long taskId = this.database.taskDao().insertTask(demoTask);
        // TEST
        Task task = this.database.taskDao().getTask(taskId);
        assertTrue(task.getName().equals(demoTask.getName()) && task.getId() == PROJECT_ID);
    }
}
