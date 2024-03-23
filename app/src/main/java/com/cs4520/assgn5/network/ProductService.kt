package com.cs4520.assgn5.network

import com.cs4520.assgn5.model.Product
import retrofit2.http.GET


interface ProductService {


    @GET("/prod/random/")
    suspend fun getProducts(): List<Product>

}