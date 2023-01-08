package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnSetResult = findViewById(R.id.btnSetResult);
        btnSetResult.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra("Result", "Привет из SecondActivity");
            setResult(RESULT_OK, intent);//  RESULT_OK = хорошый результат
            finish();//  Чтобы вернуться назад, необходимо убить эту активити
        });

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);//  RESULT_CANCELED = отмена/неудачный результат
            finish();//  Чтобы вернуться назад, необходимо убить эту активити
        });
    }

}
