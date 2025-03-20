package com.example.mymvm.model.remote

import com.example.mymvm.model.models.Product
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
   suspend fun getProducts(): Flow<List<Product>>
}