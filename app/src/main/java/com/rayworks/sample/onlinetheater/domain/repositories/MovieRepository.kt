package com.rayworks.sample.onlinetheater.domain.repositories

import com.rayworks.sample.onlinetheater.domain.entities.Movie

class MovieRepository : Repository<Movie> {
    lateinit var memStorage: HashMap<Long, Movie>

    init {
        memStorage = HashMap<Long, Movie>()
    }

    object InvalidMovie : Movie()

    override fun getById(id: Long): Movie = if (memStorage[id] == null) InvalidMovie else memStorage[id]!!

    override fun add(t: Movie) {
        memStorage[t.id] = t
    }

    override fun saveChanges() {
    }

    override fun getList(): List<Movie> = memStorage.values.toList()
}