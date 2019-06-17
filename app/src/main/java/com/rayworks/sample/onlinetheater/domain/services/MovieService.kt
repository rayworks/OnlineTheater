package com.rayworks.sample.onlinetheater.domain.services

import com.rayworks.sample.onlinetheater.domain.entities.LicensingModel
import java.util.*

class MovieService {
    fun getExpirationDate(licensingModel: LicensingModel): Date? =
        when (licensingModel) {
            LicensingModel.TwoDays -> Date(Calendar.getInstance().time.time + 1000 * 24 * 60 * 60 * 2) // add up 2 days
            LicensingModel.LifeLong -> null
        }
}