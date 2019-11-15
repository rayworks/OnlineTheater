package com.rayworks.product.onlinetheater.domain.customers

import com.rayworks.daysFromNow

class CustomerStatus(val type: CustomerStatusType, val date: ExpirationDate) {
    companion object {
        var Regular = CustomerStatus(CustomerStatusType.Regular, ExpirationDate.Infinite)
    }

    fun isAdvance(): Boolean = type == CustomerStatusType.Advanced && !date.isExpired()

    fun getDiscount(): Float = if (isAdvance()) 0.25F else 0F

    fun premote(): CustomerStatus = CustomerStatus(
        CustomerStatusType.Advanced,
        ExpirationDate(daysFromNow(365))
    )

}