package com.cs4520.assgn5.model


import android.app.Application
import androidx.lifecycle.AndroidViewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cs4520.assgn5.repo.Repository
import kotlinx.coroutines.runBlocking


class ProductItemsViewModel(application : Application) : AndroidViewModel(application) {

    private val repository = Repository(application)

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchProducts() {

        val products1 = runBlocking {
            repository.fetchhData()
        }

        _products.value = products1
        _isLoading.postValue(false)
        println("Products loaded from API: $products1")
    }
}
