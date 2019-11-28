package com.martinboy.databaseroomtest.database;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UserProfileViewModel extends AndroidViewModel {

    private static final String TAG = UserProfileViewModel.class.getSimpleName();
    private LiveData<List<UserEntity>> userEntityLiveData;
    private UserRepository userRepository;

    public UserProfileViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        userEntityLiveData = userRepository.getLiveUsers();
    }

    public LiveData<List<UserEntity>> getUserEntityLiveData() {
        return userEntityLiveData;
    }
}
