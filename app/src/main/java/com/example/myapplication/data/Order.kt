package com.example.myapplication.data

import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("order")
    val order: List<OrderX>
)