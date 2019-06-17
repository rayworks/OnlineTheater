package com.rayworks.sample.onlinetheater.domain.entities

import java.util.*

class PurchasedMovie : Entity() {
    var movieId: Long = 0
    lateinit var movie: Movie

    var customerId: Long = 0
    var price: Float = 0F
    lateinit var purchaseDate: Date
    var expirationDate: Date? = null
}