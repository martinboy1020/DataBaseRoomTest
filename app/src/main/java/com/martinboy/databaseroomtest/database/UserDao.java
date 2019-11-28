package com.martinboy.databaseroomtest.database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {

    @Query("SELECT * FROM " + DataBaseManager.TABLE_USER)
    List<UserEntity> getAll();

    @Query("SELECT * FROM " + DataBaseManager.TABLE_USER)
    LiveData<List<UserEntity>> getAllLiveData();

    @Query("SELECT * FROM " + DataBaseManager.TABLE_USER + " WHERE uid IN (:userIds)")
    List<UserEntity> getAllByUids(int[] userIds);

    @Query("SELECT * FROM " + DataBaseManager.TABLE_USER + " WHERE uid=:uid")
    UserEntity getUserByUid(int uid);

//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND "
//            + "last_name LIKE :last LIMIT 1")
//    User findByName(String first, String last);

    @Query("SELECT * FROM " + DataBaseManager.TABLE_USER + " WHERE name LIKE :name LIMIT 1")
    UserEntity findByName(String name);

    @Query("SELECT COUNT(*) FROM " + DataBaseManager.TABLE_USER)
    int getUserCount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntity... userEntities);

    @Update
    void updateUser(UserEntity...userEntities);

    @Delete
    void deleteUser(UserEntity...userEntities);

    @Query("DELETE FROM " + DataBaseManager.TABLE_USER + " WHERE uid=:uid")
    void deleteUser(int uid);

}
