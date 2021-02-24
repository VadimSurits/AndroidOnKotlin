package com.example.androidonkotlin

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

const val TAG_A = "ForEachCycleTag"
const val TAG_B = "ClosedRangeCycleTag"
const val TAG_C = "CRCReversiveTag"
const val TAG_D = "CRCReversiveStep5Tag"

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
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
                forEachCycle()
            } else {
                textView.setText(R.string.textview_text)
                textView.setTextColor(Color.BLACK)
                textView.setBackgroundColor(Color.WHITE)
                closedRangeCycle()
            }
        }

        val testDataClass = TestDataClass("TestDataClass", 2021)
        val testObject = testDataClass.copy("TestDataClassCopy", 3021)

        button.setOnLongClickListener {
            if (textView.text != "${testDataClass.string} ${testDataClass.number}") {
                textView.setText("${testDataClass.string} ${testDataClass.number}")
                closedRangeCycleReversive()
            } else {
                textView.setText("${testObject.string} ${testObject.number}")
                closedRangeCycleReversiveWithStep()
            }
            true
        }
    }

    fun forEachCycle() {
        val arrayList = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        for (i in arrayList) {
            Log.d(TAG_A, i.toString())
        }
    }

    fun closedRangeCycle() {
        for (i in 1..50) {
            Log.d(TAG_B, i.toString())
        }
    }

    fun closedRangeCycleReversive() {
        for (i in 100 downTo 50) {
            Log.d(TAG_C, i.toString())
        }
    }

    fun closedRangeCycleReversiveWithStep() {
        for (i in 50 downTo 0 step 5) {
            Log.d(TAG_D, i.toString())
        }
    }
}