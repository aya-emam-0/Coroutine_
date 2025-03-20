package com.example.mymvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mymvm.model.models.Product
import com.example.mymvm.model.repo.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel(private val repo: ProductRepository) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _favProducts = MutableStateFlow<List<Product>>(emptyList())
    val favProducts: StateFlow<List<Product>> = _favProducts

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            try {
                repo.fetchProducts().collect { productsList ->
                    _products.value = productsList
                }
            } catch (e: Exception) {
                _message.value = "An Error Occurred: ${e.message}"
            }
        }
    }

    fun addToFavorites(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.addToFavorites(product)
                getFavoriteProducts() // Refresh favorites
            } catch (e: Exception) {
                _message.value = "An Error Occurred: ${e.message}"
            }
        }
    }

    fun removeFromFavorites(productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.removeFromFavorites(productId)
                getFavoriteProducts()
            } catch (e: Exception) {
                _message.value = "An Error Occurred: ${e.message}"
            }
        }
    }

    fun getFavoriteProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.getFavoriteProducts().collect { list ->
                    _favProducts.value = list
                }
            } catch (e: Exception) {
                _message.value = "An Error Occurred: ${e.message}"
            }
        }
    }
}

class ProductFactory(private val repo: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            ProductViewModel(repo) as T
        } else {
            throw IllegalArgumentException("View Model Class not found")
        }
    }
}
