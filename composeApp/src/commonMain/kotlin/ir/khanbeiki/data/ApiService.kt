package ir.khanbeiki.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ir.khanbeiki.data.models.Product

interface ApiService {
    suspend fun getAllProducts(): List<Product>
    suspend fun getProductById(id: Int): Product
    suspend fun fetchCategories(): List<String>
}

class ApiServiceImpl(private val client: HttpClient) : ApiService {
    override suspend fun getAllProducts(): List<Product> {
        return client.get("https://fakestoreapi.com/products").body()
    }

    override suspend fun getProductById(id: Int): Product {
        return client.get("https://fakestoreapi.com/products/$id").body()
    }

    override suspend fun fetchCategories(): List<String> {
        return client.get("https://fakestoreapi.com/products/categories").body()
    }
}

expect fun createHttpClient(): HttpClient