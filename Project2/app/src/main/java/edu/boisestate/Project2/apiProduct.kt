package edu.boisestate.Project2

import com.google.gson.annotations.SerializedName

/**
 * Data class for api product variables.
 */

data class Base (

    @SerializedName("id") val id : Int,
    @SerializedName("brand") val brand : String,
    @SerializedName("name") val name : String,
    @SerializedName("price") val price : Double,
    @SerializedName("price_sign") val price_sign : String,
    @SerializedName("currency") val currency : String,
    @SerializedName("image_link") val image_link : String,
    @SerializedName("product_link") val product_link : String,
    @SerializedName("website_link") val website_link : String,
    @SerializedName("description") val description : String,
    @SerializedName("rating") val rating : String,
    @SerializedName("category") val category : String,
    @SerializedName("product_type") val product_type : String,
    @SerializedName("tag_list") val tag_list : List<String>,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("updated_at") val updated_at : String,
    @SerializedName("product_api_url") val product_api_url : String,
    @SerializedName("api_featured_image") val api_featured_image : String,
    @SerializedName("product_colors") val product_colors : List<productColors>
)