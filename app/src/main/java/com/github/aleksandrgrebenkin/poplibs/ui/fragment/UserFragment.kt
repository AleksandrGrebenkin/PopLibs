package com.github.aleksandrgrebenkin.poplibs.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.github.aleksandrgrebenkin.poplibs.databinding.FragmentUserBinding
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubUser
import com.github.aleksandrgrebenkin.poplibs.mvp.presenter.UserPresenter
import com.github.aleksandrgrebenkin.poplibs.mvp.view.UserView
import com.github.aleksandrgrebenkin.poplibs.ui.App
import com.github.aleksandrgrebenkin.poplibs.ui.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    companion object {

        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                this.putParcelable(Companion.USER_KEY, user)
            }
        }

        const val USER_KEY = "USER"
    }

    private var _binding: FragmentUserBinding? = null
    private val binding
        get() = _binding!!

    private val presenter by moxyPresenter {
        val user: GithubUser = arguments?.getParcelable<GithubUser>(USER_KEY) as GithubUser
        UserPresenter(user).apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
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

    override fun showLogin(login: String) {
        binding.tvUserLogin.text = login
    }

    override fun backPressed() = presenter.backPressed()

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> backPressed()
        else -> super.onOptionsItemSelected(item)
    }
}