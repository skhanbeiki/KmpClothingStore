package ir.khanbeiki

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import ir.khanbeiki.screens.detail.ProductDetailScreen
import ir.khanbeiki.screens.list.ProductsScreen


//@Composable
//@Preview
//fun App() {
//    MaterialTheme {
//        var showContent by remember { mutableStateOf(false) }
//        Column(
//            modifier = Modifier
//                .safeContentPadding()
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//        }
//    }
//}

@Composable
fun App() {
    MaterialTheme {
        Navigator(ProductsScreen()) { navigator ->
            SlideTransition(navigator)
        }
    }
}
