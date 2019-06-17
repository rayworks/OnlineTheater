package com.rayworks.sample.onlinetheater.domain.services

import com.rayworks.sample.onlinetheater.domain.entities.LicensingModel
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MovieServiceTest {

    lateinit var movieService: MovieService

    @Before
    fun setup() {
        movieService = MovieService()
    }

    @Test
    fun getExpirationDate() {
        Assert.assertEquals(null, movieService.getExpirationDate(LicensingModel.LifeLong))
    }
}