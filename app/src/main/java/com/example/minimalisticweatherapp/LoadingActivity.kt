package com.example.minimalisticweatherapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView

class LoadingActivity : AppCompatActivity() {

    private val loadingImageView: AppCompatImageView by lazy { findViewById(R.id.loading_iv) }
    private val loadingTextView: AppCompatTextView by lazy { findViewById(R.id.loading_tv) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        loadingTextView.text = "Погода"
        loadingImageView.setImageDrawable(
            AppCompatResources.getDrawable(
                this@LoadingActivity, R.drawable.ic_loading
            )
        )

        val handler = Handler() // TODO rework and add asking permission
        handler.postDelayed(
            {
                val intent = Intent(this@LoadingActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 2000
        )
    }
}