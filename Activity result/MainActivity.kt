package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CODE = 1234
    }

    private var tvResult: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)

        val btnNextActivity = findViewById<Button>(R.id.btnActivityResultOld)
        btnNextActivity.setOnClickListener { view: View? ->
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }

        val btnActivityResultNew = findViewById<Button>(R.id.btnActivityResultNew)
        btnActivityResultNew.setOnClickListener { view: View? ->
            val intent = Intent(this, SecondActivity::class.java)
            launchActivityResult.launch(intent)
        }
    }

    /*
    Этот метод является устаревшим!
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) { //  Сравнение кода запросов
            if (resultCode == RESULT_OK) { //  Когда удачный результат исходя из логики
                val result = data!!.getStringExtra("Result")
                tvResult!!.text = "$result\n(Вы использовали старый метод)"
            } else if (resultCode == RESULT_CANCELED) { //  Когда нажал кнопку назад или неудачный результат
                tvResult!!.text = "Неудачный результат\n(Вы использовали старый метод)"
            }
        }
    }

    /*
   Этот метод является новой альтернативой, старому!
    */
    private var launchActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
        if (activityResult.resultCode == RESULT_OK) { //  Когда удачный результат исходя из логики
            val intent = activityResult.data
            val result = intent!!.getStringExtra("Result")
            tvResult!!.text = "$result\n(Вы использовали новый метод)"
        } else if (activityResult.resultCode == RESULT_CANCELED) { //  Когда нажал кнопку назад или неудачный результат
            tvResult!!.text = "Неудачный результат\n(Вы использовали новый метод)"
        }
    }
}
