package com.axelblatt.vktask1.network.model

import com.google.gson.annotations.SerializedName


data class ServiceResponse (

  @SerializedName("products" ) var products : ArrayList<Product> = arrayListOf(),
  @SerializedName("total"    ) var total    : Int?                = null,
  @SerializedName("skip"     ) var skip     : Int?                = null,
  @SerializedName("limit"    ) var limit    : Int?                = null

)