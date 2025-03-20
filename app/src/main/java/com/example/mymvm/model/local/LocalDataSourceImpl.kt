package com.example.mymvm.model.local

import com.example.mymvm.model.models.Product
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(private val dao: ProductDao) : LocalDataSource {
    override suspend fun insertAll(products: List<Product>) {
        return dao.insertAll(products)
    }

    override suspend fun addToFavorites(product: Product) {
     dao.addToFavorites(product)
    }

    override suspend fun getFavoriteProducts(): Flow<List<Product>> {
      return dao.getFavoriteProducts()
    }

    override suspend fun removeFromFavorites(productId: Int): Int {
        return dao.removeFromFavorites(productId)
    }
}