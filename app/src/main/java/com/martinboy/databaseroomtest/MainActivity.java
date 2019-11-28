package com.martinboy.databaseroomtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.martinboy.databaseroomtest.database.UserRepository;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    UserRepository userRepository;
    Button btn_addData, btn_showData;
    TextView data_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userRepository = new UserRepository(getApplication());
        data_count = findViewById(R.id.data_count);
        btn_addData = findViewById(R.id.btn_addData);
        btn_showData = findViewById(R.id.btn_showData);
        btn_addData.setOnClickListener(onClickListener);
        btn_showData.setOnClickListener(onClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        data_count.setText("Now DataBase Has " + userRepository.getUserCount() + " Counts");
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.btn_addData) {
                startActivity(new Intent(MainActivity.this, AddDataActivity.class));
            } else {
                startActivity(new Intent(MainActivity.this, ShowDataActivity.class));
            }
        }
    };

}
