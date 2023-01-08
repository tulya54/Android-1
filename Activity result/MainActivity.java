
package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1234;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);

        Button btnNextActivity = findViewById(R.id.btnActivityResultOld);
        btnNextActivity.setOnClickListener(view -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        });

        Button btnActivityResultNew = findViewById(R.id.btnActivityResultNew);
        btnActivityResultNew.setOnClickListener(view -> {
            Intent intent = new Intent(this, SecondActivity.class);
            launchActivityResult.launch(intent);
        });
    }

    /*
    Этот метод является устаревшим!
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) { //  Сравнение кода запросов
            if (resultCode == Activity.RESULT_OK) { //  Когда удачный результат исходя из логики
                String result = data.getStringExtra("Result");
                tvResult.setText(result + "\n(Вы использовали старый метод)");
            } else if (resultCode == Activity.RESULT_CANCELED) { //  Когда нажал кнопку назад или неудачный результат
                tvResult.setText("Неудачный результат\n(Вы использовали старый метод)");
            }
        }
    }

    /*
   Этот метод является новой альтернативой, старому!
    */
    ActivityResultLauncher<Intent> launchActivityResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult activityResult) {
                    if (activityResult.getResultCode() == Activity.RESULT_OK) { //  Когда удачный результат исходя из логики
                        Intent intent = activityResult.getData();
                        String result = intent.getStringExtra("Result");
                        tvResult.setText(result + "\n(Вы использовали новый метод)");
                    } else if (activityResult.getResultCode() == Activity.RESULT_CANCELED) { //  Когда нажал кнопку назад или неудачный результат
                        tvResult.setText("Неудачный результат\n(Вы использовали новый метод)");
                    }
                }
            });
}
