package com.rayworks.sample.onlinetheater.domain.repositories

import com.rayworks.sample.onlinetheater.domain.entities.Customer

class CustomerRepository : Repository<Customer> {
    lateinit var memStorage: HashMap<Long, Customer>

    init {
        memStorage = HashMap<Long, Customer>()
    }

    object InvalidCustomer : Customer()

    override fun getById(id: Long): Customer = if (memStorage[id] == null) InvalidCustomer else memStorage[id]!!

    override fun add(t: Customer) {
        memStorage[t.id] = t
    }

    override fun saveChanges() {

    }

    override fun getList(): List<Customer> = memStorage.values.toList()
}