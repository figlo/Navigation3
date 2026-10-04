package sk.figlar.navigation3.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Destination {
    object Home: Destination
    data class Detail(val id: Int = 0): Destination
}