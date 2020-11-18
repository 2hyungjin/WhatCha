package com.example.watcha.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.watcha.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        init()
    }
    fun init(){
        val intent=intent
        Glide.with(this)
            .load(intent.getStringExtra("img"))
            .centerCrop()
            .into(detail_img)
        detail_tv_title.text=intent.getStringExtra("title")
    }
}