package com.minimalisticweatherapp

class SettingsPresenter(
    private val view: SettingsMain.View,
    private val model: SettingsMain.Model
) : SettingsMain.Presenter {
    override fun start() {
        view.showInterface(
            backImage = R.drawable.ic_back,
            activityName = "Настройки"
        )
    }
}