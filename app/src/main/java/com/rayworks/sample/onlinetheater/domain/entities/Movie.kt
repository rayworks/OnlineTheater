package com.rayworks.sample.onlinetheater.domain.entities

open class Movie : Entity() {
    var name = ""
    lateinit var licensingModel: LicensingModel
}