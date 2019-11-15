package com.rayworks.product.onlinetheater.domain.customers

import com.google.gson.annotations.SerializedName

enum class CustomerStatusType(i: Int) {
    @SerializedName("1")
    Regular(1),

    @SerializedName("2")
    Advanced(2);
}