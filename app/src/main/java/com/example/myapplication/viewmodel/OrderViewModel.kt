package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.common.ResponseStatus
import com.example.myapplication.data.OrderX
import com.example.myapplication.network.OrderRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRepository: OrderRepositoryInterface,
    private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _orders: MutableLiveData<ResponseStatus> = MutableLiveData(ResponseStatus.LOADING())
    val orders: LiveData<ResponseStatus> get() = _orders

    fun getAllOrders() {
        viewModelSafeScope.launch(ioDispatcher) {
            orderRepository.getAllOrders().collect {
                _orders.postValue(it)
            }
        }
    }
}