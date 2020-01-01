package com.martinboy.databaseroomtest;

import android.content.Intent;
import android.os.Bundle;

import com.martinboy.databaseroomtest.database.UserEntity;
import com.martinboy.databaseroomtest.database.UserRepository;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShowDataActivity  extends AppCompatActivity {

    RecyclerView recyclerView;
    ShowDataAdapter adapter;
    UserRepository userRepository;
    LiveData<List<UserEntity>> liveData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShowDataAdapter();
        adapter.setShowDataActivity(this);
        userRepository = new UserRepository(getApplication());
        liveData = userRepository.getLiveUsers();
        recyclerView.setAdapter(adapter);
        liveData.observe(this, new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(@Nullable List<UserEntity> list){
                adapter.setUserEntityLiveData(list);
            }
        });
    }

    public void editData(UserEntity userEntity) {
        Intent intent = new Intent().setClass(this, AddDataActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("UserEntity", userEntity);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
