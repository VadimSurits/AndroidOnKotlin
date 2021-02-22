package com.example.androidonkotlin

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text_view)
        val button = findViewById<MaterialButton>(R.id.button)
        button.setOnClickListener {
            if (textView.text == getString(R.string.textview_text)) {
                textView.text = getString(R.string.textview_kotlin_text)
                textView.setTextColor(Color.WHITE)
                textView.setBackgroundColor(Color.BLACK)
            } else {
                textView.setText(R.string.textview_text)
                textView.setTextColor(Color.BLACK)
                textView.setBackgroundColor(Color.WHITE)
            }
        }
    }
}