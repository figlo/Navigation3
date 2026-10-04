package sk.figlar.navigation3.navigation

sealed interface Destination {
    object Home: Destination
    data class Detail(val id: Int = 0): Destination
}