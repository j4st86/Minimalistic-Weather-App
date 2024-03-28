package com.minimalisticweatherapp.presenters

import com.minimalisticweatherapp.R
import com.minimalisticweatherapp.SettingsMain

class SettingsPresenter(
    private val view: SettingsMain.View,
    private val model: SettingsMain.Model
) : SettingsMain.Presenter {
    override fun start() {
        view.showInterface(
            backImage = R.drawable.ic_back,

        )
    }
}