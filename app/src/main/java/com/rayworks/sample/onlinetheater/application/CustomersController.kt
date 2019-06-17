package com.rayworks.sample.onlinetheater.application

import com.google.gson.Gson
import com.rayworks.sample.onlinetheater.domain.entities.Customer
import com.rayworks.sample.onlinetheater.domain.entities.CustomerStatus
import com.rayworks.sample.onlinetheater.domain.entities.PurchasedMovie
import com.rayworks.sample.onlinetheater.domain.repositories.CustomerRepository
import com.rayworks.sample.onlinetheater.domain.repositories.MovieRepository
import com.rayworks.sample.onlinetheater.domain.services.CustomerService
import java.lang.RuntimeException
import java.util.*

class CustomersController(
    private val movieRepository: MovieRepository, private val customerRepository: CustomerRepository,
    private val customerService: CustomerService
) {
    private val gson: Gson = Gson()

    fun get(id: Long): String {

        val customer = customerRepository.getById(id)
        if (customer == CustomerRepository.InvalidCustomer) {
            return "{}"
        }
        return gson.toJson(customer)
    }

    fun getList(): List<String?> {
        val list = customerRepository.getList()
        return list.map { t: Customer? -> t?.let { gson.toJson(it) } }.toList()
    }

    fun create(info: String) {
        val custom = gson.fromJson<Customer>(info, Customer::class.java)
        custom.apply { id = 0; status = CustomerStatus.Regular }

        customerRepository.add(custom)
        customerRepository.saveChanges()
    }

    fun update(id: Long, info: String) {
        val newOne: Customer = gson.fromJson(info, Customer::class.java)

        val customer = customerRepository.getById(id)
        if (customer != CustomerRepository.InvalidCustomer) {
            customer.name = newOne.name

            customerRepository.saveChanges()
        }
    }

    fun purchaseMovie(id: Long, movieId: Long) {
        val movie = movieRepository.getById(movieId)
        if (movie != MovieRepository.InvalidMovie) {
            val customer = customerRepository.getById(id)
            if (customer != CustomerRepository.InvalidCustomer) {
                val count = customer.purchasedMovies.filter { movie: PurchasedMovie ->
                    movie.id == movieId &&
                            (movie.expirationDate == null || movie.expirationDate!!.after(Calendar.getInstance().time))
                }.count()

                if (count > 0) {
                    throw RuntimeException("The movie is already purchased: " + movie.name)
                }

                customerService.purchaseMovie(customer, movie)
                customerRepository.saveChanges()
            }
        }

    }

    fun promoteCustomer(id: Long) {
        val customer = customerRepository.getById(id)
        if (customer != CustomerRepository.InvalidCustomer) {
            if (customer.status == CustomerStatus.Advanced && (customer.statusExpirationDate == null ||
                        customer.statusExpirationDate!!.before(Calendar.getInstance().time))
            ) {
                throw RuntimeException("The customer already has the Advanced status")
            }

            val promoted = customerService.promoteCustomer(customer)
            if (!promoted) {
                throw RuntimeException("Cannot promote the customer")
            }

            customerRepository.saveChanges()
        }
    }
}