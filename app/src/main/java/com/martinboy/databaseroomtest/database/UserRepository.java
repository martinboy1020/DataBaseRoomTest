package com.martinboy.databaseroomtest.database;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;

public class UserRepository {
    private UserDao mUserDao;
    private LiveData<List<UserEntity>> mLiveUsers;
    private List<UserEntity> mUsers;

    public UserRepository(Application application){
        mUserDao = AppDatabase.getInstance(application).getUserDao();
        mLiveUsers = mUserDao.getAllLiveData();
        mUsers = mUserDao.getAll();
    }

    public LiveData<List<UserEntity>> getLiveUsers() {
        Log.d("tag1", "mLiveUsers.getValue(): " + mLiveUsers.getValue());
        return mLiveUsers;
    }

    public List<UserEntity> getUsers() {
        mUsers = mUserDao.getAll();
        return mUsers;
    }

    public int getUserCount(){
        return mUserDao.getUserCount();
    }

    public UserEntity getUserByUid(int uid){
        return mUserDao.getUserByUid(uid);
    }

    public void addUsers(UserEntity... userEntities){
        new AddUserAsyncTask(mUserDao).execute(userEntities);
    }

    public void deleteUsers(UserEntity... userEntities){
        new DeleteUserAsyncTask(mUserDao).execute(userEntities);
    }

    public void deleteUserById(int uID){
        new DeleteUserByUidAsyncTask(mUserDao).execute(uID);
    }

    public void deleteUserByUserEntity(UserEntity... userEntities){
        new DeleteUserByUserEntityAsyncTask(mUserDao).execute(userEntities);
    }

    public void updateUsers(UserEntity... userEntities){
        new UpdateUserAsyncTask(mUserDao).execute(userEntities);
    }

    private static class AddUserAsyncTask extends AsyncTask<UserEntity[], Void, Void>{
        private UserDao mAsyncUserDao;

        AddUserAsyncTask(UserDao dao){
            mAsyncUserDao = dao;
        }

        @Override
        protected Void doInBackground(UserEntity[]... userEntities) {
            mAsyncUserDao.insertUser(userEntities[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<UserEntity[], Void, Void>{
        private UserDao mAsyncUserDao;

        DeleteUserAsyncTask(UserDao dao){
            mAsyncUserDao = dao;
        }

        @Override
        protected Void doInBackground(UserEntity[]... userEntities) {
            mAsyncUserDao.deleteUser(userEntities[0]);
            return null;
        }
    }

    private static class DeleteUserByUidAsyncTask  extends AsyncTask<Integer, Void, Void> {
        private UserDao mAsyncUserDao;

        DeleteUserByUidAsyncTask(UserDao dao){
            mAsyncUserDao = dao;
        }

        @Override
        protected Void doInBackground(Integer... memberId) {
            mAsyncUserDao.deleteUser(memberId[0]);
            return null;
        }
    }

    private static class DeleteUserByUserEntityAsyncTask  extends AsyncTask<UserEntity, Void, Void> {
        private UserDao mAsyncUserDao;

        DeleteUserByUserEntityAsyncTask(UserDao dao){
            mAsyncUserDao = dao;
        }

        @Override
        protected Void doInBackground(UserEntity... userEntities) {
            mAsyncUserDao.deleteUser(userEntities);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<UserEntity[], Void, Void>{
        private UserDao mAsyncUserDao;

        UpdateUserAsyncTask(UserDao dao){
            mAsyncUserDao = dao;
        }

        @Override
        protected Void doInBackground(UserEntity[]... userEntities) {
            mAsyncUserDao.updateUser(userEntities[0]);
            return null;
        }
    }
}