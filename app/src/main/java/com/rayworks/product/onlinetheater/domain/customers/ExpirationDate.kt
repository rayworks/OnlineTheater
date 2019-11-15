package com.rayworks.product.onlinetheater.domain.customers

import java.util.*

class ExpirationDate(val date: Date?) {
    companion object {
        val Infinite: ExpirationDate = ExpirationDate(null)

        fun create(date: Date?) = ExpirationDate(date)
    }

    fun isExpired(): Boolean = date != null && date.before(Calendar.getInstance().time)

}