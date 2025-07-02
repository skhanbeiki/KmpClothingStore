package ir.khanbeiki.navigation

sealed class Destination(val route: String) {
    object Products : Destination("products")
    data class ProductDetail(val productId: Int) : Destination("product_detail/{productId}") {
        fun createRoute(productId: Int) = "product_detail/$productId"
    }
}