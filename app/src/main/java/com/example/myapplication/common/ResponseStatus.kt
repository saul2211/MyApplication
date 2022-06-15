package com.example.myapplication.common

import com.example.myapplication.data.Order

sealed interface ResponseStatus {
    class LOADING(val isLoading: Boolean = true) : ResponseStatus
    class SUCCESS(val orders: Order) : ResponseStatus
    class ERROR(val error: Throwable) : ResponseStatus
}