package com.example.myapplication.network


import com.example.myapplication.common.NullResponseException
import com.example.myapplication.common.ResponseIsAFailure
import com.example.myapplication.common.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface OrderRepositoryInterface {
    fun getAllOrders(): Flow<ResponseStatus>
}

class OrderRepository @Inject constructor(
    private val interfaceService: InterfaceService
) : OrderRepositoryInterface {

    override fun getAllOrders(): Flow<ResponseStatus> =
        flow {
            emit(ResponseStatus.LOADING())

            try {
                val response = interfaceService.getOrders()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ResponseStatus.SUCCESS(it))
                    } ?: throw NullResponseException()
                } else {
                    throw ResponseIsAFailure()
                }
            } catch (e: Exception) {
                emit(ResponseStatus.ERROR(e))
            }
        }
}