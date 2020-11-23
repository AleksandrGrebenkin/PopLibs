package com.github.aleksandrgrebenkin.poplibs.mvp.presenter.list

import com.github.aleksandrgrebenkin.poplibs.mvp.view.list.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}