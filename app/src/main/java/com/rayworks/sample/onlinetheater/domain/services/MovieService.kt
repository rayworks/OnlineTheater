package com.rayworks.sample.onlinetheater.domain.services

import com.rayworks.daysFromNow
import com.rayworks.sample.onlinetheater.domain.entities.LicensingModel
import java.util.*

class MovieService {
    fun getExpirationDate(licensingModel: LicensingModel): Date? =
        when (licensingModel) {
            LicensingModel.TwoDays -> daysFromNow(2) // add up 2 days
            LicensingModel.LifeLong -> null
        }
}