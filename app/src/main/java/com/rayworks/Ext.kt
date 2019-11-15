package com.rayworks

import java.util.*

fun daysFromNow(days: Int) = Date(
    Calendar.getInstance().time.time + days * 1000 * 60 * 60 * 24L
)