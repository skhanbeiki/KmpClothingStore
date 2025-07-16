package ir.khanbeiki.them

object AppStrings {
    const val APP_NAME = "My KMP App"
    const val WELCOME_MESSAGE = "Welcome to Kotlin Multiplatform!"

    const val HOME_TITLE = "Home"
    const val PROFILE_TITLE = "Profile"

    const val BUTTON_SUBMIT = "Submit"
    const val BUTTON_CANCEL = "Cancel"
    const val BUTTON_SEE_MORE = "See More"

    const val ERROR_NETWORK = "Network error occurred"
    const val ERROR_GENERIC = "Something went wrong"

    fun greeting(name: String) = "Hello, $name!"
    fun itemsCount(count: Int) = "$count items"
}