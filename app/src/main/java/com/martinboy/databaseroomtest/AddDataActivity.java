package com.martinboy.databaseroomtest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.martinboy.databaseroomtest.database.DataBaseManager;
import com.martinboy.databaseroomtest.database.UserEntity;
import com.martinboy.databaseroomtest.database.UserRepository;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddDataActivity extends AppCompatActivity {

    EditText editName, editLocation;
    Button btn_add;
    DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        dataBaseManager = DataBaseManager.getManager(this);

        editName = findViewById(R.id.edit_name);
        editLocation = findViewById(R.id.edit_location);
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editName.getText().toString().trim().length() > 0 &&
                editLocation.getText().toString().trim().length() > 0) {
//                    dataBaseManager.insertUser(editName.getText().toString(),
//                            editLocation.getText().toString());
                    UserEntity userEntity = new UserEntity();
                    userEntity.setName(editName.getText().toString());
                    userEntity.setLocation(editLocation.getText().toString());
                    UserRepository userRepository = new UserRepository(getApplication());
                    userRepository.addUsers(userEntity);
                } else {
                    Toast.makeText(AddDataActivity.this, "資料不足", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
