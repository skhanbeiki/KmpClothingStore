package ir.khanbeiki.data.repository

import ir.khanbeiki.data.ApiService
import ir.khanbeiki.data.models.Product

interface ProductRepository {
    suspend fun fetchAllProducts(): List<Product>
    suspend fun fetchProductById(id: Int): Product
    suspend fun fetchCategories(): List<String>
}

class ProductRepositoryImpl(
    private val apiService: ApiService
) : ProductRepository {
    override suspend fun fetchAllProducts(): List<Product> {
        return apiService.getAllProducts()
    }

    override suspend fun fetchProductById(id: Int): Product {
        return apiService.getProductById(id)
    }

    override suspend fun fetchCategories(): List<String> {
        return apiService.fetchCategories()
    }
}