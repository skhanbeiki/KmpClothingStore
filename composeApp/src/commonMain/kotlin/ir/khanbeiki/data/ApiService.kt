package ir.khanbeiki.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import ir.khanbeiki.data.models.Product
import kotlinx.serialization.json.Json

interface ApiService {
    suspend fun getAllProducts(): List<Product>
    suspend fun getProductById(id: Int): Product
}

class ApiServiceImpl(private val client: HttpClient) : ApiService {
    override suspend fun getAllProducts(): List<Product> {
        return client.get("https://fakestoreapi.com/products").body()
    }

    override suspend fun getProductById(id: Int): Product {
        return client.get("https://fakestoreapi.com/products/$id").body()
    }
}

fun createHttpClient(): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
    }
}