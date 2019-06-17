package com.rayworks.sample.onlinetheater.domain.entities

import com.google.gson.annotations.SerializedName

enum class CustomerStatus(i: Int) {
    @SerializedName("1")
    Regular(1),

    @SerializedName("2")
    Advanced(2)
}