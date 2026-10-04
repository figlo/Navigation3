package sk.figlar.navigation3.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay

@Composable
fun NavigationExample() {
    val backStack: SnapshotStateList<Destination> = remember { mutableStateListOf(Destination.Home) }

    fun navigateBack(): () -> Unit = {
        backStack.removeLastOrNull()
    }

    fun navigate(destination: Destination) {
        backStack.add(destination)
    }

    NavDisplay(
        backStack = backStack,
        onBack = navigateBack(),
        entryProvider = { key ->
            when (key) {
                Destination.Home -> NavEntry(key) {
                    Column {
                        repeat(5) {
                            Button(
                                onClick = { navigate(Destination.Detail(it))},
                            ) {
                                Text("Detail $it")
                            }
                        }
                    }
                }
                is Destination.Detail -> NavEntry(key) { Text("Detail ${key.id}") }
            }
        },
    )
}