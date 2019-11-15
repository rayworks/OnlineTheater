package com.rayworks.product.onlinetheater.domain.customs

class CustomerName private constructor(name: String) {
    companion object {
        fun create(customerName: String): CustomerName {
            val name = if (customerName.isEmpty()) customerName else customerName.trim()
            require(name.isNotEmpty()) { "Customer name should not be empty" }
            require(customerName.length <= 100) { "Customer name is too long" }

            return CustomerName(name)
        }
    }
}