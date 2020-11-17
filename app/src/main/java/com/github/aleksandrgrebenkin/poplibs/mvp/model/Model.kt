package com.github.aleksandrgrebenkin.poplibs.mvp.model

class Model {

    private val counters = mutableListOf(0, 0, 0)

    fun getCurrent(index: Int) = counters[index]

    fun next(index: Int): Int {
        counters[index]++
        return getCurrent(index)
    }
}