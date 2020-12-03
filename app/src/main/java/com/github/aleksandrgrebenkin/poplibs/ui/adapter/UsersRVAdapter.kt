package com.github.aleksandrgrebenkin.poplibs.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.github.aleksandrgrebenkin.poplibs.databinding.ItemUserBinding
import com.github.aleksandrgrebenkin.poplibs.mvp.model.image.IImageLoader
import com.github.aleksandrgrebenkin.poplibs.mvp.presenter.list.IUserListPresenter
import com.github.aleksandrgrebenkin.poplibs.mvp.view.list.UserItemView
import kotlinx.android.extensions.LayoutContainer

class UsersRVAdapter(
    val presenter: IUserListPresenter, val imageLoader: IImageLoader<ImageView>
) : RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemUserBinding = ItemUserBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemUserBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener {
            presenter.itemClickListener?.invoke(holder)
        }
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root), LayoutContainer, UserItemView {

        override val containerView = binding.root
        override var pos = -1

        override fun setLogin(text: String) {
            binding.tvLogin.text = text
        }

        override fun loadImage(url: String) {
            imageLoader.loadInto(url, binding.ivImage)
        }
    }
}