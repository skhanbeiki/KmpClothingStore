package ir.khanbeiki.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import ir.khanbeiki.data.models.Product
import ir.khanbeiki.screens.detail.ProductDetailScreen
import ir.khanbeiki.them.AppColors
import ir.khanbeiki.them.AppStrings
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.coroutines.cancellation.CancellationException

class ProductsScreen : Screen {

    @Preview
    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<ProductsViewModel>()

        val navigator = LocalNavigator.currentOrThrow
        val products by viewModel.products.collectAsState()
        val category by viewModel.category.collectAsState()
        val isLoading by viewModel.isLoading.collectAsState()
        val error by viewModel.error.collectAsState()

        LaunchedEffect(Unit) {
            try {
                if (products.isEmpty()) {
                    viewModel.loadProducts()
                }
                if (category.isEmpty()) {
                    viewModel.fetchCategories()
                }
            } catch (e: CancellationException) {
                e.printStackTrace()
            }
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(AppStrings.APP_NAME) },
                    backgroundColor = AppColors.Primary
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when {
                    isLoading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    error != null -> {
                        Text(
                            text = "Error: $error",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    else -> {
                        Column(modifier = Modifier.fillMaxSize().background(AppColors.Background)) {
                            CategoryHorizontalList(category)

                            ProductList(
                                products = products,
                                onItemClick = { product ->
                                    navigator.push(ProductDetailScreen(product.id))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductList(products: List<Product>, onItemClick: (Product) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products) { product ->
            ProductItem(product = product, onItemClick = onItemClick)
        }
    }
}

@Composable
fun ProductItem(product: Product, onItemClick: (Product) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(product) },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = "Product image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = product.category,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                )
            }
        }
    }
}

@Composable
fun CategoryHorizontalList(categories: List<String>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(categories) { category ->
            CategoryItem(category = category)
        }
    }
}

@Composable
fun CategoryItem(category: String) {
    androidx.compose.material3.Card(
        modifier = Modifier
            .width(120.dp)
            .height(48.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.Primary,
            contentColor = AppColors.OnPrimary
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                color = AppColors.Background,
                text = category,
                maxLines = 1
            )
        }
    }
}