package com.rayworks.product.onlinetheater.domain.customs

import com.rayworks.daysFromNow
import com.rayworks.product.onlinetheater.domain.common.Entity
import com.rayworks.product.onlinetheater.domain.movies.Movie
import java.util.*

class PurchasedMovie(
    var movie: Movie,
    var customer: Customer,
    var price: Float = 0F,
    var expirationDate: ExpirationDate,
    var purchasedDate: Date = daysFromNow(0)
) : Entity() {

    init {
        require(price.compareTo(0) != 0) { price.toString() }
        require(!expirationDate.isExpired()) { expirationDate.toString() }

    }
}