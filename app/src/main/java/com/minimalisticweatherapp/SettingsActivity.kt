package com.minimalisticweatherapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView

class SettingsActivity : AppCompatActivity(), SettingsMain.View {
    private val backImageView: AppCompatImageView by lazy { findViewById(R.id.back_iv) }
    private val activityTextView: AppCompatTextView by lazy { findViewById(R.id.settings_tv) }

    private lateinit var presenter: SettingsMain.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val model =
        presenter = SettingsPresenter(this, model)

        presenter.start()

        backImageView.setOnClickListener {
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun showInterface(backImage: Int, activityName: String) {
        backImageView.setImageDrawable(
            AppCompatResources.getDrawable(
                this@SettingsActivity, backImage
            )
        )
        activityTextView.text = activityName
    }
}