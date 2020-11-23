package com.github.aleksandrgrebenkin.poplibs.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.github.aleksandrgrebenkin.poplibs.databinding.FragmentUserBinding
import com.github.aleksandrgrebenkin.poplibs.databinding.FragmentUsersBinding
import com.github.aleksandrgrebenkin.poplibs.mvp.model.entity.GithubUser
import com.github.aleksandrgrebenkin.poplibs.mvp.presenter.UserPresenter
import com.github.aleksandrgrebenkin.poplibs.mvp.view.UserView
import com.github.aleksandrgrebenkin.poplibs.ui.App
import com.github.aleksandrgrebenkin.poplibs.ui.BackButtonListener
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
        val user: GithubUser = arguments?.getParcelable<GithubUser>(Companion.USER_KEY)!!
        UserPresenter(user, App.instance.router)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showUser(user: GithubUser) {
        binding.tvUserLogin.text = user.login
    }

    override fun backPressed() = presenter.backPressed()

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> backPressed()
        else -> backPressed()
    }
}