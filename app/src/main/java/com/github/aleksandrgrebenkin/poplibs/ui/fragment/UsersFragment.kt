package com.github.aleksandrgrebenkin.poplibs.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.aleksandrgrebenkin.poplibs.databinding.FragmentUsersBinding
import com.github.aleksandrgrebenkin.poplibs.mvp.model.api.ApiHolder
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.room.cache.RoomUsersCache
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.room.database.Database
import com.github.aleksandrgrebenkin.poplibs.mvp.model.repo.RetrofitGithubUsersRepo
import com.github.aleksandrgrebenkin.poplibs.mvp.presenter.UsersPresenter
import com.github.aleksandrgrebenkin.poplibs.mvp.view.UsersView
import com.github.aleksandrgrebenkin.poplibs.ui.App
import com.github.aleksandrgrebenkin.poplibs.ui.BackButtonListener
import com.github.aleksandrgrebenkin.poplibs.ui.adapter.UsersRVAdapter
import com.github.aleksandrgrebenkin.poplibs.ui.image.GlideImageLoader
import com.github.aleksandrgrebenkin.poplibs.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    private var _binding: FragmentUsersBinding? = null
    private val binding
        get() = _binding!!

    private val presenter by moxyPresenter {
        UsersPresenter(
            RetrofitGithubUsersRepo(
                ApiHolder.api,
                AndroidNetworkStatus(requireContext()),
                RoomUsersCache()
            ),
            App.instance.router,
            AndroidSchedulers.mainThread()
        )
    }
    private var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        binding.rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showError(message: Throwable) {
        Toast.makeText(context, message.message, Toast.LENGTH_SHORT).show()
        Log.e("MY_ERROR", message.message ?: message.stackTraceToString())
    }

    override fun backPressed() = presenter.backPressed()
}