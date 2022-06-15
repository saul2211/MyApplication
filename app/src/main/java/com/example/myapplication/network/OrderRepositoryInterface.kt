package com.example.myapplication.network


import com.example.myapplication.common.NullResponseException
import com.example.myapplication.common.ResponseIsAFailure
import com.example.myapplication.common.ResponseStatus
import com.example.myapplication.data.Order
import com.example.myapplication.data.OrderX
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
                        emit(
                            ResponseStatus.SUCCESS(
                                Order(
                                    parsePizzaResponse(it.order)
                                )
                            )
                        )
                    } ?: throw NullResponseException()
                } else {
                    throw ResponseIsAFailure()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ResponseStatus.ERROR(e))
            }
        }

    private fun parsePizzaResponse(response: List<OrderX>): List<OrderX> {
        val result = mutableListOf<OrderX>()
        response.forEach {
            when (it.size) {
                "large" -> {
                    result.add(OrderX(it.sauce, it.size, it.toppings, it.type, "15"))
                }
                "medium" -> {
                    result.add(OrderX(it.sauce, it.size, it.toppings, it.type, "8"))
                }
                "small" -> {
                    result.add(OrderX(it.sauce, it.size, it.toppings, it.type, "4"))
                }
            }
        }
        return result
    }
}