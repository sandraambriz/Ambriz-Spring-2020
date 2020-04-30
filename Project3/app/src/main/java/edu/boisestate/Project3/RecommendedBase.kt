package edu.boisestate.Project3

import com.google.gson.annotations.SerializedName

data class RecommendedBase (

    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("brand") val brand : String,
    @SerializedName("type") val type : String,
    @SerializedName("description") val description : String
)