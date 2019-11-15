package com.rayworks.product.onlinetheater.domain.customers

class Email private constructor(value: String) {
    companion object {

        fun create(email: String): Email {
            val address = if (email.isEmpty()) email else email.trim()

            require(address.isNotEmpty()) { "Email should not be empty" }

            require(address.matches(Regex.fromLiteral("^(.+)@(.+)$"))) { "Email is invalid" }

            return Email(address)
        }
    }
}