package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val btnSetResult = findViewById<Button>(R.id.btnSetResult)
        btnSetResult.setOnClickListener {
            val intent = Intent()
            intent.putExtra("Result", "Привет из SecondActivity")
            setResult(RESULT_OK, intent) //  RESULT_OK = хорошый результат
            finish() //  Чтобы вернуться назад, необходимо убить эту активити
        }

        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            setResult(RESULT_CANCELED) //  RESULT_CANCELED = отмена/неудачный результат
            finish() //  Чтобы вернуться назад, необходимо убить эту активити
        }
    }
}
