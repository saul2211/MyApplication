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


    private val TAG = "PIZZA PRICE "

    //private val viewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

    private lateinit var viewModel: OrderViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // viewModel = ViewModelProvider

    }


    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {

        viewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        viewModel.getAllOrders()

        viewModel.orders.observe(this) { state ->

            when (state) {

                is ResponseStatus.SUCCESS -> {
                    state.orders.forEach { orderX ->
                        when (orderX.size) {
                            "large" -> {
                                Log.i(TAG, "15€")
                            }
                            "medium" -> {
                                Log.i(TAG, "8€")
                            }
                            "small" -> {
                                Log.i(TAG, "4€")
                            }

                        }

                    }
                }

                is ResponseStatus.ERROR -> {
                    Log.i("ERROR", "API ERROR")
                }

                is ResponseStatus.LOADING -> {

                }

            }

        }



        return super.onCreateView(name, context, attrs)
    }


}