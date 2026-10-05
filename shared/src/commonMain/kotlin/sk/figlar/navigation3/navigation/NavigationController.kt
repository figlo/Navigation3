package sk.figlar.navigation3.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay

@Composable
fun NavigationExample() {
    val backStack: SnapshotStateList<Destination> = remember { mutableStateListOf(Destination.Home) }

    fun navigate(destination: Destination) {
        backStack.add(destination)
    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        transitionSpec = {
            EnterTransition.None togetherWith ExitTransition.None
        },
        popTransitionSpec = {
            EnterTransition.None togetherWith ExitTransition.None
        },
        predictivePopTransitionSpec = {
            EnterTransition.None togetherWith ExitTransition.None
        },
        entryProvider = entryProvider {
                entry<Destination.Home> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            repeat(6) {
                                Button(
                                    onClick = { navigate(Destination.Detail(it))},
                                ) {
                                    Text("Detail $it")
                                }
                            }
                        }
                    }
                entry<Destination.Detail> { key -> DetailScreen(key.id) }
        },
    )
}

@Composable
fun DetailScreen(id: Int) {
    Text("Detail $id")
}