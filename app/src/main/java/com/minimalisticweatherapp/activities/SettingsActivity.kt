package com.minimalisticweatherapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.minimalisticweatherapp.R
import com.minimalisticweatherapp.SettingsMain
import com.minimalisticweatherapp.models.SettingsModel
import com.minimalisticweatherapp.presenters.SettingsPresenter

class SettingsActivity : AppCompatActivity(), SettingsMain.View {
    private val backImageView: AppCompatImageView by lazy { findViewById(R.id.back_iv) }
    private val activityTextView: AppCompatTextView by lazy { findViewById(R.id.settings_tv) }

    private lateinit var presenter: SettingsMain.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val model = SettingsModel()
        presenter = SettingsPresenter(this, model)

        presenter.start()

        backImageView.setOnClickListener {
            onBackPressed()
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