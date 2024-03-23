package com.cs4520.assgn5.repo

import android.content.Context
import android.widget.Toast
import androidx.room.Room
import com.cs4520.assgn5.db.AppDatabase
import com.cs4520.assgn5.db.DBProduct
import com.cs4520.assgn5.model.Product
import com.cs4520.assgn5.network.ProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Repository(private val application: Context) {

    private val fetchedProductNames = mutableSetOf<String>()


    suspend fun fetchhData() : List<Product>{
        var products : List<Product> = emptyList()
        try {
            products = loadProductsFromApi()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if(products.isEmpty()){
                try {
                   products = loadProductsFromDb()
                } catch (e: Exception) {
                     e.printStackTrace()
                }

            }
        }
        return products
    }

     suspend fun loadProductsFromApi() : List<Product>{
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://kgtttq6tg9.execute-api.us-east-2.amazonaws.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val service = retrofit.create(ProductService::class.java)

        val products = service.getProducts()

        val convertedProducts: List<Product> = products
            .filterNot { fetchedProductNames.contains(it.name) }
            .filter { (it.type == "Food" && it.expiryDate != null)
                    || it.type == "Equipment"  }
            .also { filteredProducts ->
                fetchedProductNames.addAll(filteredProducts.map { it.name })
            }

        if(convertedProducts.isNotEmpty()){
            saveProductsToDb(convertedProducts)
        }

        return convertedProducts
    }


    private suspend fun saveProductsToDb(convertedProducts : List<Product>){

        val db = Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java, "products"
        ).build()

        val dbProducts = convertedProducts.map { product ->
            DBProduct(
                name = product.name,
                price = product.price,
                productType = product.type,
                expiryDate = product.expiryDate
            )
        }

        val dbProductsArray = dbProducts.toTypedArray()
        withContext(Dispatchers.IO) {
            db.DBProductDao().deleteAll()
            db.DBProductDao().insertAll(*dbProductsArray)
        }
    }

    private suspend fun loadProductsFromDb() : List<Product>{
        val db = Room.databaseBuilder(
            application,
            AppDatabase::class.java, "products"
        ).build()
        var  products : List<Product>
        withContext(Dispatchers.IO) {
            val dbProducts = db.DBProductDao().getAll()
             products = dbProducts.map { dbProduct ->
                Product(
                    name = dbProduct.name,
                    price = dbProduct.price,
                    type = dbProduct.productType,
                    expiryDate = dbProduct.expiryDate
                )
            }

        }
        db.close()
        return products
    }

}