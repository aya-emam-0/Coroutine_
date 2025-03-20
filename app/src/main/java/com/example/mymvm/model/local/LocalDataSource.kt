package com.example.mymvm.model.local

import com.example.mymvm.model.models.Product
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun insertAll(products: List<Product>)

    suspend fun addToFavorites(product: Product)


    suspend fun getFavoriteProducts(): Flow<List<Product>>


    suspend fun removeFromFavorites(productId: Int):Int
}