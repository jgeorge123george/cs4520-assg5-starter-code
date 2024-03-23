package com.cs4520.assgn5.screens


import android.graphics.Color.parseColor
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cs4520.assgn5.R
import com.cs4520.assgn5.model.Product
import com.cs4520.assgn5.model.ProductItemsViewModel



@Composable
fun ListItemScreen(
    modifier: Modifier = Modifier,
    productItemsViewModel: ProductItemsViewModel = viewModel(),
    navController: NavController

){
    productItemsViewModel.fetchProducts()
    val products = productItemsViewModel.products.value
    Surface(modifier = Modifier.fillMaxSize(),
      color = MaterialTheme.colorScheme.surface) {
        LazyColumn(modifier = Modifier
            .padding(0.dp)
            .fillMaxSize()
         ) {
            items(products ?: listOf()) { product ->
                ProdutcItem(product)
            }
        }
    }
}



@Composable
fun ProdutcItem(product : Product) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(1.0f)
            .padding(0.dp)
            .background(color = Color.Yellow)
            .border(0.dp, Color.LightGray)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(
                color = when (product.type) {
                    "Food" -> "#FFD965".color
                    "Equipment" -> "#E06666".color
                    else -> Color.White
                }
            )
        ) {
            Column(modifier = Modifier
                .fillMaxWidth(0.2f)
                .weight(0.2f)) {
                Box(modifier = Modifier
                    .width(100.dp)
                    .height(80.dp)
                    .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if(product.type == "Food"){
                        val imageId = R.drawable.food
                        val imagePainter: Painter = painterResource(id = imageId)
                        Image(painter = imagePainter, contentDescription = "Product Type")
                    }else{
                        val imageId = R.drawable.tools
                        val imagePainter: Painter = painterResource(id = imageId)
                        Image(painter = imagePainter, contentDescription = "Product Type")
                    }
                }
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .weight(0.8f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                 Text(
                     product.name,
                     fontWeight = FontWeight.Bold
                 )
                if(product.expiryDate == null){
                    Text("")
                }else{
                    Text(
                        product.expiryDate.toString(),
                        fontWeight = FontWeight.Bold
                    )
                }
               Text(
                   "$ " + product.price.toString(),
                   fontWeight = FontWeight.Bold
               )
            }
        }
    }
}



val String.color
    get() = Color(parseColor(this))