package ir.khanbeiki

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform