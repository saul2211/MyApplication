package com.example.myapplication.common


import com.example.myapplication.data.OrderX

sealed interface ResponseStatus {
    class LOADING(val isLoading: Boolean = true) : ResponseStatus
    class SUCCESS(val orders: List<OrderX>) : ResponseStatus
    class ERROR(val error: Throwable) : ResponseStatus
}