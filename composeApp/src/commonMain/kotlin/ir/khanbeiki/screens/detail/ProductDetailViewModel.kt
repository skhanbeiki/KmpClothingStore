package ir.khanbeiki.screens.detail

import cafe.adriel.voyager.core.model.ScreenModel
import ir.khanbeiki.data.models.Product
import ir.khanbeiki.data.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val repository: ProductRepository
) : ScreenModel{
    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun loadProduct(productId: Int) {
        coroutineScope.launch {
            _isLoading.value = true
            try {
                _product.value = repository.fetchProductById(productId)
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error loading product: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}