package com.martinboy.databaseroomtest.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//@Database(entities = {HealthRecordEntity.class, ConfigObject.class}, version = 2, exportSchema = true)
@Database(entities = {UserEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "database.db";
    private static AppDatabase INSTANCE;
    private static final Object sLock = new Object();
//    public abstract UserDao userDao();

    public static AppDatabase getInstance(Context context) {
        synchronized (sLock) {

//            if (instance == null) {
//                synchronized (HealthCenterDB.class) {
//                    instance = Room.databaseBuilder(context.getApplicationContext(), HealthCenterDB.class, DATABASE_NAME)
//                            .addMigrations(migration_1_to_2)
//                            .allowMainThreadQueries().build();
//                }
//            }

            if (INSTANCE == null) {
                INSTANCE =
                        Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                                .allowMainThreadQueries().build();
            }
            return INSTANCE;
        }
    }

    public static void destoryInstance() {
        INSTANCE = null;
    }

    public abstract UserDao getUserDao();

    // for update
//    public static final Migration migration_1_to_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE " + SettingManager.TABLE_USER
//                    + " ADD COLUMN userId TEXT");
//            database.execSQL("ALTER TABLE " + SettingManager.TABLE_USER
//                    + " ADD COLUMN visibleStatus INTEGER NOT NULL DEFAULT 1");
//        }
//    };

}
