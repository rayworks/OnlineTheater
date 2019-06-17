package com.rayworks.sample.onlinetheater.domain.entities

import com.google.gson.annotations.SerializedName

enum class LicensingModel(i: Int) {
    @SerializedName("1")
    TwoDays(1),

    @SerializedName("2")
    LifeLong(2)
}