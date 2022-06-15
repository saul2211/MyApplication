package com.example.myapplication.data

import com.google.gson.annotations.SerializedName

data class OrderX(
    @SerializedName("sauce")
    val sauce: List<String>?,
    @SerializedName("size")
    val size: String?,
    @SerializedName("toppings")
    val toppings: List<String>?,
    @SerializedName("type")
    val type: String?,
    val pizzaPrice: String
)