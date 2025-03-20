package com.example.mymvm.model.repo

import android.util.Log
import com.example.mymvm.model.local.LocalDataSourceImpl
import com.example.mymvm.model.remote.RemotDataSourceImpl
import com.example.mymvm.model.models.Product
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val db: LocalDataSourceImpl, private val remoteDataSource : RemotDataSourceImpl) {

    suspend fun fetchProducts(): Flow<List<Product>> {
        val products =remoteDataSource.getProducts()
        Log.i("TAG", "fetchProducts: ${products}")
        return remoteDataSource.getProducts()


    }


    suspend fun addToFavorites(product: Product) {
       db.addToFavorites(product)
    }

    suspend fun getFavoriteProducts(): Flow<List<Product>> {
        return db.getFavoriteProducts()
    }

    suspend fun removeFromFavorites(productId: Int) {
        db.removeFromFavorites(productId)
    }
}
