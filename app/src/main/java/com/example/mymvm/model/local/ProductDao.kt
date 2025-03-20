package com.example.mymvm.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mymvm.model.models.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<Product>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(product: Product)

    @Query("SELECT * FROM products")
     fun getFavoriteProducts(): Flow<List<Product>>

    @Query("DELETE FROM products WHERE id = :productId")
    suspend fun removeFromFavorites(productId: Int):Int
}
