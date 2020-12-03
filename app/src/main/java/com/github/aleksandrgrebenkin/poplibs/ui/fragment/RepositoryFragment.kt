package com.github.aleksandrgrebenkin.poplibs.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.github.aleksandrgrebenkin.poplibs.databinding.FragmentRepositoryBinding
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubRepository
import com.github.aleksandrgrebenkin.poplibs.mvp.presenter.RepositoryPresenter
import com.github.aleksandrgrebenkin.poplibs.mvp.view.RepositoryView
import com.github.aleksandrgrebenkin.poplibs.ui.App
import com.github.aleksandrgrebenkin.poplibs.ui.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepositoryFragment : MvpAppCompatFragment(), RepositoryView, BackButtonListener {
    companion object {

        fun newInstance(repository: GithubRepository) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                this.putParcelable(Companion.REPOSITORY_KEY, repository)
            }
        }

        const val REPOSITORY_KEY = "REPOSITORY"
    }

    private var _binding: FragmentRepositoryBinding? = null
    private val binding
        get() = _binding!!

    private val presenter by moxyPresenter {
        val repository: GithubRepository =
            arguments?.getParcelable<GithubRepository>(REPOSITORY_KEY) as GithubRepository
        RepositoryPresenter(repository, App.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onStart() {
        super.onStart()
        (requireActivity() as MvpAppCompatActivity)
            .supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        (requireActivity() as MvpAppCompatActivity)
            .supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showForksCount(count: String) {
        binding.tvForksCount.text = count
    }

    override fun backPressed() = presenter.backPressed()

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> backPressed()
        else -> super.onOptionsItemSelected(item)
    }
}