package edu.boisestate.p2

import com.google.gson.annotations.SerializedName

/**
 * Data class for product colors from api products.
 */
data class productColors (

    @SerializedName("hex_value") val hex_value : String,
    @SerializedName("colour_name") val colour_name : String
)