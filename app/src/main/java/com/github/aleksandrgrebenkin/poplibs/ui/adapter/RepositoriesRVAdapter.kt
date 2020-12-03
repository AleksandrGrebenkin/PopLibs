package com.github.aleksandrgrebenkin.poplibs.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.aleksandrgrebenkin.poplibs.databinding.ItemRepositoryBinding
import com.github.aleksandrgrebenkin.poplibs.mvp.presenter.list.IRepositoryListPresenter
import com.github.aleksandrgrebenkin.poplibs.mvp.view.list.RepositoryItemView
import kotlinx.android.extensions.LayoutContainer

class RepositoriesRVAdapter(val presenter: IRepositoryListPresenter) :
    RecyclerView.Adapter<RepositoriesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemRepositoryBinding = ItemRepositoryBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemRepositoryBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener {
            presenter.itemClickListener?.invoke(holder)
        }
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root), LayoutContainer, RepositoryItemView {

        override val containerView = binding.root
        override var pos = -1

        override fun setName(text: String) {
            binding.tvName.text = text
        }
    }
}