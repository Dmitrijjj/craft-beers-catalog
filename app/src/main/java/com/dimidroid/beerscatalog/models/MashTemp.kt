package com.dimidroid.beerscatalog.models


import com.google.gson.annotations.SerializedName

data class MashTemp(
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("temp")
    val temp: TempX
) {
    class TempX {

    }
}