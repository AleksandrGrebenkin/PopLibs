package com.github.aleksandrgrebenkin.poplibs.ui.activity

import android.os.Bundle
import com.github.aleksandrgrebenkin.poplibs.databinding.ActivityMainBinding
import com.github.aleksandrgrebenkin.poplibs.mvp.model.repo.GithubUserRepo
import com.github.aleksandrgrebenkin.poplibs.mvp.presenter.MainPresenter
import com.github.aleksandrgrebenkin.poplibs.mvp.presenter.UsersPresenter
import com.github.aleksandrgrebenkin.poplibs.mvp.view.MainView
import com.github.aleksandrgrebenkin.poplibs.ui.App
import com.github.aleksandrgrebenkin.poplibs.ui.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding

    private val navigatorHolder = App.instance.navigatorHolder
    private val navigator by lazy {
        SupportAppNavigator(
            this,
            supportFragmentManager,
            binding.container.id
        )
    }

    private val presenter by moxyPresenter { MainPresenter(App.instance.router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backPressed()
    }
}