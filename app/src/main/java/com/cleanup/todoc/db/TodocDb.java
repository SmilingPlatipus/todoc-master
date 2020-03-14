package com.cleanup.todoc.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

@Database(entities = {Project.class, Task.class}, version = 1, exportSchema = false)
public abstract class TodocDb extends RoomDatabase
{

    // --- SINGLETON ---
    private static volatile TodocDb INSTANCE;

    // --- DAO ---
    public abstract ProjectDao projectDao();
    public abstract TaskDao taskDao();

    // --- INSTANCE ---
    public static TodocDb getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TodocDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                                    TodocDb.class, "Todoc.db")
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // ---

    private static Callback prepopulateDatabase(){
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                ContentValues contentValues = new ContentValues();
                contentValues.put("id", 1L);
                contentValues.put("name", "Projet Tartampion");
                contentValues.put("color", 0xFFEADAD1);

                db.insert("project", OnConflictStrategy.IGNORE, contentValues);

                contentValues.put("id", 2L);
                contentValues.put("name", "Projet Lucidia");
                contentValues.put("color", 0xFFB4CDBA);

                db.insert("project", OnConflictStrategy.IGNORE, contentValues);

                contentValues.put("id", 3L);
                contentValues.put("name", "Projet Circus");
                contentValues.put("color", 0xFFA3CED2);

                db.insert("project", OnConflictStrategy.IGNORE, contentValues);

            }
        };
    }
}