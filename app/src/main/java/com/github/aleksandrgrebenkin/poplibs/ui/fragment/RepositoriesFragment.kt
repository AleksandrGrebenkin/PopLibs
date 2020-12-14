package com.github.aleksandrgrebenkin.poplibs.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.aleksandrgrebenkin.poplibs.databinding.FragmentRepositoriesBinding
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubUser
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.room.cache.RoomRepositoriesCache
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.room.database.Database
import com.github.aleksandrgrebenkin.poplibs.mvp.model.repo.RetrofitGithubRepositoriesRepo
import com.github.aleksandrgrebenkin.poplibs.mvp.presenter.RepositoriesPresenter
import com.github.aleksandrgrebenkin.poplibs.mvp.view.RepositoriesView
import com.github.aleksandrgrebenkin.poplibs.ui.App
import com.github.aleksandrgrebenkin.poplibs.ui.BackButtonListener
import com.github.aleksandrgrebenkin.poplibs.ui.adapter.RepositoriesRVAdapter
import com.github.aleksandrgrebenkin.poplibs.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatActivity
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepositoriesFragment : MvpAppCompatFragment(), RepositoriesView, BackButtonListener {

    companion object {
        fun newInstance(user: GithubUser) = RepositoriesFragment().apply {
            arguments = Bundle().apply {
                this.putParcelable(RepositoriesFragment.USER_KEY, user)
            }
        }

        private const val USER_KEY = "USER"
    }

    private var _binding: FragmentRepositoriesBinding? = null
    private val binding
        get() = _binding!!

    private val presenter by moxyPresenter {
        val user: GithubUser =
            arguments?.getParcelable<GithubUser>(UserFragment.USER_KEY) as GithubUser
        RepositoriesPresenter(user).apply {
            App.instance.appComponent.inject(this)
        }
    }
    private var adapter: RepositoriesRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoriesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onStart() {
        super.onStart()
        (requireActivity() as MvpAppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        (requireActivity() as MvpAppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(
            false
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.rvRepositories.layoutManager = LinearLayoutManager(context)
        adapter = RepositoriesRVAdapter(presenter.repositoriesListPresenter)
        binding.rvRepositories.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showError(message: Throwable) {
        Toast.makeText(context, message.message, Toast.LENGTH_SHORT).show()
        Log.e("MY_ERROR", message.message ?: message.stackTraceToString())
    }

    override fun backPressed() = presenter.backPressed()

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> backPressed()
        else -> super.onOptionsItemSelected(item)
    }
}