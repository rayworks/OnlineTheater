package com.rayworks.sample.onlinetheater.domain.repositories

import com.rayworks.sample.onlinetheater.domain.entities.Entity

interface Repository<T : Entity> {
    fun getById(id: Long): T
    fun add(t:T)
    fun saveChanges()
    fun getList():List<T>
}