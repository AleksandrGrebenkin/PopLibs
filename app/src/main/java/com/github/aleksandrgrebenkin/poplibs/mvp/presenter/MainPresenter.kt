package com.github.aleksandrgrebenkin.poplibs.mvp.presenter

import com.github.aleksandrgrebenkin.poplibs.mvp.model.Model
import com.github.aleksandrgrebenkin.poplibs.mvp.view.MainView

class MainPresenter(private val model: Model, private val view: MainView) {
        
    fun counter1Click() {
        val nextValue = model.next(0)
        view.setButton1Text(nextValue.toString())
    }

    fun counter2Click() {
        val nextValue = model.next(1)
        view.setButton2Text(nextValue.toString())
    }

    fun counter3Click() {
        val nextValue = model.next(2)
        view.setButton3Text(nextValue.toString())
    }

    fun viewCreated() {
        view.setButton1Text(model.getCurrent(0).toString())
        view.setButton2Text(model.getCurrent(1).toString())
        view.setButton3Text(model.getCurrent(2).toString())
    }

}