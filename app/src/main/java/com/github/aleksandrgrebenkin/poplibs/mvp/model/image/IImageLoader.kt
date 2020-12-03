package com.github.aleksandrgrebenkin.poplibs.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}