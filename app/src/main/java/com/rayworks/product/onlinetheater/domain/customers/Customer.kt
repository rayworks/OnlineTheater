package com.rayworks.product.onlinetheater.domain.customers

import com.rayworks.daysFromNow
import com.rayworks.product.onlinetheater.domain.common.Entity
import com.rayworks.product.onlinetheater.domain.movies.Movie

class Customer constructor(var purchasedMovies: ArrayList<PurchasedMovie> = arrayListOf()) :
    Entity() {

    var customName: CustomerName? = null

    var status: CustomerStatus = CustomerStatus.Regular

    var moneySpent: Float = 0f

    constructor(name: CustomerName, mail: Email) : this()

    fun hasPurchasedMovie(movie: Movie) =
        purchasedMovies.any { x -> x.movie == movie && !x.expirationDate.isExpired() }

    fun purchaseMovie(movie: Movie) {
        check(!hasPurchasedMovie(movie)) { "Movie already purchased!" }

        val date = movie.getExpirationDate()
        val price = movie.calculatePrice(status)

        var purchased = PurchasedMovie(movie, this, price, date)
        purchasedMovies.add(purchased)

        moneySpent += price
    }

    fun canPromote(): Boolean {
        if (status.isAdvance()) {
            print("The customer already has the Advanced status"); return false
        }

        if (purchasedMovies.count { x ->
                x.expirationDate == ExpirationDate.Infinite || x.expirationDate.date!!.after(
                    daysFromNow(-30)
                )
            } < 2) {
            print("The customer has to have at least 2 active movies during the last 30 days")
            return false
        }

        if (purchasedMovies.filter { x -> x.purchasedDate.after(daysFromNow(-365)) }.sumByDouble { x -> x.price.toDouble() }.toFloat() < 100F) {
            print("The customer has to have at least 100 dollars spent during the last year")
            return false
        }

        return true
    }

    fun promote() {
        if (!canPromote()) {
            throw Exception("Promotion failure")
        }

        status = status.premote()
    }
}