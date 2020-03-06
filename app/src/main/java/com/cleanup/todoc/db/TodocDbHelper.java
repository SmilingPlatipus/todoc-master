package com.cleanup.todoc.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class TodocDbHelper extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Todoc.db";

    public static final String CREATE_PROJECTS_TABLE = "CREATE TABLE " + TodocTableContract.ProjectsTable.TABLE_NAME + " (" +
            TodocTableContract.ProjectsTable.COLUMN_NAME_ID +" BIGINT PRIMARY KEY," +
            TodocTableContract.ProjectsTable.COLUMN_NAME_NAME +" LONGVARCHAR NOT NULL," +
            TodocTableContract.ProjectsTable.COLUMN_NAME_COLOR +" INTEGER NOT NULL," +
                                                                                "UNIQUE (" +
            TodocTableContract.ProjectsTable.COLUMN_NAME_COLOR +","+
            TodocTableContract.ProjectsTable.COLUMN_NAME_NAME + "));";

    public static final String CREATE_TASKS_TABLE = "CREATE TABLE " + TodocTableContract.TasksTable.TABLE_NAME + " (" +
            TodocTableContract.TasksTable.COLUMN_NAME_ID + " BIGINT PRIMARY KEY," +
            TodocTableContract.TasksTable.COLUMN_NAME_PROJECT_ID + " BIGINT NOT NULL," +
            TodocTableContract.TasksTable.COLUMN_NAME_NAME + " LONGVARCHAR NOT NULL," +
            TodocTableContract.TasksTable.COLUMN_NAME_CREATION_TIME_STAMP +" TIMESTAMP NOT NULL," +
                                                                        "FOREIGN KEY (" +
            TodocTableContract.ProjectsTable.COLUMN_NAME_ID + ")" +
                                                                        "REFERENCES " +
            TodocTableContract.ProjectsTable.TABLE_NAME + " (" +
            TodocTableContract.ProjectsTable.COLUMN_NAME_ID + ")" +
                                                                        "ON UPDATE CASCADE " +
                                                                        "ON DELETE CASCADE);";

    public static final String DELETE_PROJECTS_TABLE = "DROP TABLE IF EXISTS " +  TodocTableContract.ProjectsTable.TABLE_NAME;
    public static final String DELETE_TASKS_TABLE = "DROP TABLE IF EXISTS " +  TodocTableContract.TasksTable.TABLE_NAME;

    public TodocDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PROJECTS_TABLE);
        sqLiteDatabase.execSQL(CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DELETE_PROJECTS_TABLE);
        sqLiteDatabase.execSQL(DELETE_TASKS_TABLE);
        onCreate(sqLiteDatabase);
    }


    public final class TodocTableContract {

        private TodocTableContract() {}


        public class ProjectsTable
        {
            public static final String TABLE_NAME = "projects";
            public static final String COLUMN_NAME_ID = "project_id";
            public static final String COLUMN_NAME_NAME = "name";
            public static final String COLUMN_NAME_COLOR = "color";
        }

        public class TasksTable
        {
            public static final String TABLE_NAME = "tasks";
            public static final String COLUMN_NAME_ID = "id";
            public static final String COLUMN_NAME_PROJECT_ID = "project_id";
            public static final String COLUMN_NAME_NAME = "name";
            public static final String COLUMN_NAME_CREATION_TIME_STAMP = "creation_time_stamp";
        }
    }


}
