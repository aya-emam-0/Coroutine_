package com.example.mymvm

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mymvm.allproduct.ProductsActivity
import com.example.mymvm.favourite.FavoritesActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen() {
        Column(
            modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { startActivity(Intent(this@MainActivity, ProductsActivity::class.java)) },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("View All Products")
            }

            Button(
                onClick = { startActivity(Intent(this@MainActivity, FavoritesActivity::class.java)) },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("View Favorite Products")
            }
//            Button(
//                onClick = { startActivity(Intent(this@MainActivity, SearchActivity::class.java)) },
//                modifier = Modifier.fillMaxWidth().padding(8.dp)
//            ) {
//                Text("Search for names")
//            }
        }
    }
}
