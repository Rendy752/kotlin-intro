package com.example.introduction

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoggingAndDebugActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnSetValue: Button
    private lateinit var tvText: TextView

    private var names = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging_and_debug)
        tvText = findViewById(R.id.tv_text)

        btnSetValue = findViewById(R.id.btn_set_value)
        btnSetValue.setOnClickListener(this)

        names.add("Riki")
        names.add("Eko")
        names.add("Budi")

    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_set_value) {
            val name = StringBuilder()
            for (i in 0..2){
                name.append(names[i]).append("\n")
            }
            tvText.text = name.toString()
        }
    }
}