package com.rayworks.product.onlinetheater.domain.movies

import com.rayworks.daysFromNow
import com.rayworks.product.onlinetheater.domain.common.Entity
import com.rayworks.product.onlinetheater.domain.customs.CustomerStatus
import com.rayworks.product.onlinetheater.domain.customs.ExpirationDate
import com.rayworks.product.onlinetheater.domain.customs.LicensingModel

abstract class Movie : Entity() {
    var name = ""
    protected var licensingModel: LicensingModel? = null

    abstract fun getExpirationDate(): ExpirationDate

    fun calculatePrice(status: CustomerStatus): Float {
        val modifier = 1F - status.getDiscount()
        return getBasePrice() * modifier
    }

    protected abstract fun getBasePrice(): Float

    companion object {
        class TwoDaysMovie : Movie() {
            override fun getExpirationDate(): ExpirationDate = ExpirationDate(daysFromNow(2))

            override fun getBasePrice(): Float = 4F
        }

        class LifeLongMovie : Movie() {
            override fun getExpirationDate(): ExpirationDate = ExpirationDate.Infinite

            override fun getBasePrice(): Float = 8F
        }
    }
}

