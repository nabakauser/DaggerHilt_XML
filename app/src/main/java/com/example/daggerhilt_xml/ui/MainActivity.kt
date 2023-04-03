package com.example.daggerhilt_xml.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.daggerhilt_xml.databinding.ActivityMainBinding
import com.example.daggerhilt_xml.model.User
import com.example.daggerhilt_xml.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val homeViewModel: HomeViewModel by viewModels()
    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter(
            users = arrayListOf()
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setUpUi()
        setupObserver()
    }

    private fun setUpUi() {
        binding?.uiRvUserDetails?.apply {
            adapter = homeAdapter
        }
    }

    private fun setupObserver() {
        homeViewModel.users.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding?.uiProgressBar?.visibility = View.GONE
                    it.data?.let { users -> setListToUi(users) }
                }
                Status.ERROR -> {
                    binding?.uiProgressBar?.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setListToUi(users: List<User>) {
        homeAdapter.addData(users)
        homeAdapter.notifyDataSetChanged()
    }
}