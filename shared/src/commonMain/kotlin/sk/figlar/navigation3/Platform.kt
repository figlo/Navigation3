package sk.figlar.navigation3

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform