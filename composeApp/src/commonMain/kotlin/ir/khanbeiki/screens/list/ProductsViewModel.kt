package ir.khanbeiki.screens.list

import cafe.adriel.voyager.core.model.ScreenModel
import ir.khanbeiki.data.models.Product
import ir.khanbeiki.data.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val repository: ProductRepository
) : ScreenModel {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun loadProducts() {
        coroutineScope.launch {
            _isLoading.value = true
            try {
                _products.value = repository.fetchAllProducts()
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error loading products: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}