package com.github.aleksandrgrebenkin.poplibs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.aleksandrgrebenkin.poplibs.databinding.ActivityMainBinding
import com.github.aleksandrgrebenkin.poplibs.mvp.model.Model
import com.github.aleksandrgrebenkin.poplibs.mvp.presenter.MainPresenter
import com.github.aleksandrgrebenkin.poplibs.mvp.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private val presenter = MainPresenter(Model(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btn1.setOnClickListener {
            presenter.counterClick(1)
        }
        binding.btn2.setOnClickListener {
            presenter.counterClick(2)
        }
        binding.btn3.setOnClickListener {
            presenter.counterClick(3)
        }

        presenter.viewCreated()
    }

    override fun setButton1Text(text: String) {
        binding.btn1.text = text
    }

    override fun setButton2Text(text: String) {
        binding.btn2.text = text
    }

    override fun setButton3Text(text: String) {
        binding.btn3.text = text
    }
}