package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.common.ResponseStatus
import com.example.myapplication.viewmodel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        viewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        viewModel.getAllOrders()

        viewModel.orders.observe(this) { state ->

            when (state) {

                is ResponseStatus.SUCCESS -> {
                    println(state.orders)
                }

                is ResponseStatus.ERROR -> {
                    Log.i("ERROR", "API ERROR")
                }

                is ResponseStatus.LOADING -> {

                }

            }

        }


    }

}