package com.example.mymvm.favourite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberImagePainter
import com.example.mymvm.model.local.LocalDataSourceImpl
import com.example.mymvm.model.remote.RemotDataSourceImpl
import com.example.mymvm.model.local.AppDatabase
import com.example.mymvm.model.models.Product
import com.example.mymvm.model.repo.ProductRepository
import com.example.mymvm.viewmodel.ProductFactory
import com.example.mymvm.viewmodel.ProductViewModel

class FavoritesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val factory = ProductFactory(
                ProductRepository(
                    LocalDataSourceImpl(AppDatabase.getInstance(this).productDao()),
                    RemotDataSourceImpl()
                )
            )
            val viewModel = ViewModelProvider(this, factory).get(ProductViewModel::class.java)
            FavoriteScreen(viewModel)
        }
    }
}

@Composable
fun FavoriteScreen(viewModel: ProductViewModel) {
    val products by viewModel.favProducts.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getFavoriteProducts()
    }

    Column {
        Text("Favorite Products", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(8.dp))
        LazyColumn {
            items(products) { product ->
                FavoriteProductItem(product, onRemoveFavorite = { viewModel.removeFromFavorites(product.id) })
            }
        }
    }
}

@Composable
fun FavoriteProductItem(product: Product, onRemoveFavorite: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(product.thumbnail),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(64.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(product.title, style = MaterialTheme.typography.headlineSmall)
                Text("Price: $${product.price}", style = MaterialTheme.typography.bodySmall)
            }

            Button(
                onClick = { onRemoveFavorite() },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Remove")
            }
        }
    }
}
