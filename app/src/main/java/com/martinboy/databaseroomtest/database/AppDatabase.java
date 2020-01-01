package com.martinboy.databaseroomtest.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import static com.martinboy.databaseroomtest.database.DataBaseManager.TABLE_USER;

@Database(entities = {UserEntity.class}, version = 2, exportSchema = true)

// exportSchema = true
// In the build.gradle file for your app module,
// add this to the defaultConfig section (under the android section).
// This will write out the schema to a schemas subfolder of your project folder.

//javaCompileOptions {
//        annotationProcessorOptions {
//        arguments = ["room.schemaLocation": "$projectDir/schemas".toString()]
//        }
//        }

public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "database.db";
    private static AppDatabase INSTANCE;
    private static final Object sLock = new Object();

    static AppDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE =
                        Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                                .allowMainThreadQueries().addMigrations(MIGRATION_1_2).build();
            }
            return INSTANCE;
        }
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(AddColumnIntInDb(TABLE_USER, "age", true, 0));
        }
    };

    private static String AddColumnTextInDb(String tableName, String columnName) {
        return "ALTER TABLE " + TABLE_USER
                + " ADD COLUMN " + columnName + " TEXT";
    }

    private static String AddColumnIntInDb(String tableName, String columnName, boolean isNotNull, int defaultInt) {
        if(isNotNull) {
            return "ALTER TABLE " + TABLE_USER
                    + " ADD COLUMN " + columnName + " INTEGER NOT NULL DEFAULT " + defaultInt;
        } else {
            return "ALTER TABLE " + TABLE_USER
                    + " ADD COLUMN " + columnName + " INTEGER";
        }
    }

    public static void destoryInstance() {
        INSTANCE = null;
    }

    public abstract UserDao getUserDao();

}
