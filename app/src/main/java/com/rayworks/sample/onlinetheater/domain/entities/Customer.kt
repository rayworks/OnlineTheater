package com.rayworks.sample.onlinetheater.domain.entities

import java.util.*
import kotlin.collections.ArrayList

public open class Customer : Entity() {
    lateinit var name: String
    lateinit var email: String

    lateinit var status: CustomerStatus
    var statusExpirationDate: Date? = null
    var moneySpent: Float = 0F
    var purchasedMovies: ArrayList<PurchasedMovie> = ArrayList<PurchasedMovie>()
}