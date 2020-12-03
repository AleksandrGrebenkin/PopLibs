package com.github.aleksandrgrebenkin.poplibs.mvp.view.list

interface UserItemView : IItemView {
    fun setLogin(text: String)
    fun loadImage(url: String)
}