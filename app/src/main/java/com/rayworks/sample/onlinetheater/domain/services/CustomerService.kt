package com.rayworks.sample.onlinetheater.domain.services

import com.rayworks.sample.onlinetheater.domain.entities.*
import java.util.*

class CustomerService(val movieService: MovieService) {

    fun calculatePrice(status: CustomerStatus, expirationDate: Date?, licensingModel: LicensingModel): Float {
        var price: Float = when (licensingModel) {
            LicensingModel.TwoDays -> 4F
            LicensingModel.LifeLong -> 8F
        }

        if (status == CustomerStatus.Advanced && (expirationDate == null || expirationDate.after(Calendar.getInstance().time))) {
            price *= 0.75F
        }

        return price
    }

    fun purchaseMovie(customer: Customer, movie: Movie) {
        val expirationDate = movieService.getExpirationDate(movie.licensingModel)
        val price = calculatePrice(customer.status, customer.statusExpirationDate, movie.licensingModel)

        val purchasedMovie: PurchasedMovie = PurchasedMovie().apply {
            movieId = movie.id
            customerId = customer.id
            this.expirationDate = expirationDate
            this.price = price
        }

        customer.purchasedMovies.add(purchasedMovie)
        customer.moneySpent += price
    }


    fun promoteCustomer(customer: Customer): Boolean {
        // at least 2 active movies during the last 30 days
        if (customer.purchasedMovies.filter { m: PurchasedMovie ->
                m.expirationDate == null || m.expirationDate!!.after(
                    Date(
                        Calendar.getInstance().time.time - 30 * 1000 * 60 * 60 * 24
                    )
                )
            }.count() < 2) {

            return false
        }

        // at least 100 dollars spent during the last year
        val oneYear = 365 * 1000 * 60 * 60 * 24
        if (customer.purchasedMovies.filter { m: PurchasedMovie ->
                m.purchaseDate.after(
                    Date(
                        Calendar.getInstance().time.time - oneYear
                    )
                )
            }.toList().sumByDouble { movie: PurchasedMovie -> movie.price.toDouble() }.toFloat() < 100F) {

            return false
        }

        customer.status = CustomerStatus.Advanced
        customer.statusExpirationDate = Date(Calendar.getInstance().time.time + oneYear)

        return true
    }

}