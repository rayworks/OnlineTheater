package com.rayworks.product.onlinetheater.domain.common

interface Repository<T : Entity> {
    fun getById(id: Long): T
    fun add(t:T)
}