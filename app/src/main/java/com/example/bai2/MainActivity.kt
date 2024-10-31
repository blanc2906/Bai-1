package com.example.bai2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var etNumber: EditText
    lateinit var rbEven: RadioButton
    lateinit var rbOdd: RadioButton
    lateinit var rbSquare: RadioButton
    lateinit var btnShow: Button
    lateinit var listView: ListView
    lateinit var tvError: TextView
    lateinit var adapter: ArrayAdapter<Int>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etNumber = findViewById(R.id.etNumber)
        rbEven = findViewById(R.id.rbEven)
        rbOdd = findViewById(R.id.rbOdd)
        rbSquare = findViewById(R.id.rbSquare)
        btnShow = findViewById(R.id.btnShow)
        listView = findViewById(R.id.listView)
        tvError = findViewById(R.id.tvError)

        btnShow.setOnClickListener{
            tvError.visibility = View.GONE;
            val input = etNumber.text.toString();

            if(input.isEmpty() || input.toIntOrNull() == null || input.toInt() <= 0){
                tvError.text = "Vui lòng nhập số nguyên dương!";
                tvError.visibility = View.VISIBLE;
                return@setOnClickListener;
            }

            val n = input.toInt();
            val result = ArrayList<Int>();
            when {
                rbEven.isChecked -> {
                    for(i in 0 .. n step 2) {
                        result.add(i);
                    }
                }
                rbOdd.isChecked -> {
                    for(i in 1 .. n step 2) {
                        result.add(i)
                    }
                }

                rbSquare.isChecked -> {
                    for(i in 0..n step 1) {
                        if(i*i <= n){
                            result.add(i*i);
                        } else {
                            break;
                        }

                    }
                }
                else -> {
                    tvError.text = "Vui lòng chọn loại số!"
                    tvError.visibility = View.VISIBLE
                    return@setOnClickListener
                }
            }
            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, result)
            listView.adapter = adapter
        }
    }
}