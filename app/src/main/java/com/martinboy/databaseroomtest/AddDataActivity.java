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
    UserEntity userEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getParcelable("UserEntity") != null) {
            userEntity = bundle.getParcelable("UserEntity");
        }

        dataBaseManager = DataBaseManager.getManager(this);

        editName = findViewById(R.id.edit_name);
        editLocation = findViewById(R.id.edit_location);

        if (userEntity != null) {
            editName.setText(userEntity.getName());
            editLocation.setText(userEntity.getLocation());
        }

        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserRepository userRepository = new UserRepository(getApplication());

                if (editName.getText().toString().trim().length() > 0 &&
                        editLocation.getText().toString().trim().length() > 0) {

                    if (userEntity != null) {
                        userEntity.setName(editName.getText().toString());
                        userEntity.setLocation(editLocation.getText().toString());
                        userRepository.updateUsers(userEntity);
                    } else {

//                    dataBaseManager.insertUser(editName.getText().toString(),
//                            editLocation.getText().toString());
                        UserEntity userEntity = new UserEntity();
                        userEntity.setName(editName.getText().toString());
                        userEntity.setLocation(editLocation.getText().toString());
                        userRepository.addUsers(userEntity);

                    }

                    onBackPressed();

                } else {
                    Toast.makeText(AddDataActivity.this, "資料不足", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
