package com.martinboy.databaseroomtest.database;

import android.content.Context;

public class DataBaseManager {

    private final String TAG = DataBaseManager.class.getSimpleName();

    public static final String TABLE_USER = "user_table";

    private UserDao getUserDao;
    private Context context;

    private static DataBaseManager INSTANCE;

    private DataBaseManager(Context context){
        this.context = context;
        this.getUserDao = AppDatabase.getInstance(context).getUserDao();
    }


    public static DataBaseManager getManager(Context context){

        if(INSTANCE == null){
            INSTANCE = new DataBaseManager(context);
        }

        return INSTANCE;
    }

    public void insertUser(String name, String location) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setLocation(location);
        getUserDao.insertUser(userEntity);
    }



}
