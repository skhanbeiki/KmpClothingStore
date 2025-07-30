package ir.khanbeiki.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
import ir.khanbeiki.utils.isWatchDevice
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.coroutines.cancellation.CancellationException

class ProductsScreen : Screen {

    @Preview
    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<ProductsViewModel>()

        val navigator = LocalNavigator.currentOrThrow
        val products by viewModel.products.collectAsState()
        val categories by viewModel.category.collectAsState()
        val isLoading by viewModel.isLoading.collectAsState()
        val error by viewModel.error.collectAsState()

        var selectedCategory by remember { mutableStateOf<String?>(null) }

        LaunchedEffect(Unit) {
            try {
                if (products.isEmpty()) viewModel.loadProducts()
                if (categories.isEmpty()) viewModel.fetchCategories()
            } catch (e: CancellationException) {
                e.printStackTrace()
            }
        }

        val content: @Composable () -> Unit = {
            when {
                isLoading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }

                error != null -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error: $error")
                }

                else -> Column(modifier = Modifier.fillMaxSize().background(AppColors.Background)) {
                    CategoryHorizontalList(
                        categories = categories,
                        selectedCategory = selectedCategory,
                        onCategorySelected = {
                            selectedCategory = it
                            viewModel.loadProducts()
                        }
                    )

                    ProductList(
                        products = products,
                        onItemClick = { product -> navigator.push(ProductDetailScreen(product.id)) }
                    )
                }
            }
        }

        if (isWatchDevice()) {
            Scaffold { padding ->
                Box(modifier = Modifier.fillMaxSize().padding(padding)) {
                    content()
                }
            }
        } else {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(AppStrings.APP_NAME) },
                        backgroundColor = AppColors.Primary,
                        contentColor = AppColors.OnPrimary
                    )
                }
            ) { padding ->
                Box(modifier = Modifier.fillMaxSize().padding(padding)) {
                    content()
                }
            }
        }
    }
}

@Composable
fun ProductList(products: List<Product>, onItemClick: (Product) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
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
            .padding(horizontal = 16.dp)
            .clickable { onItemClick(product) },
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = AppColors.CardBackground
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = "Product image",
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.h6,
                    color = AppColors.OnBackground,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.body1,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = AppColors.Primary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = product.category,
                    style = MaterialTheme.typography.caption,
                    color = AppColors.TextSecondary
                )
            }
        }
    }
}

@Composable
fun CategoryHorizontalList(
    categories: List<String>,
    selectedCategory: String?,
    onCategorySelected: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(categories) { category ->
            val isSelected = category == selectedCategory

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(
                        if (isSelected) AppColors.Primary else AppColors.ChipBackground
                    )
                    .clickable { onCategorySelected(category) }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = category,
                    color = if (isSelected) AppColors.OnPrimary else AppColors.OnBackground,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}
