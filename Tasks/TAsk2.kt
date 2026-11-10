import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                MainContainerScreen()
            }
        }
    }
}

// Simple container to allow navigating between screens for testing
@Composable
fun MainContainerScreen() {
    var isOnSecondScreen by remember { mutableStateOf(false) }

    if (isOnSecondScreen) {
        // Another screen to test leaving and coming back
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("This is the Second Screen", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { isOnSecondScreen = false }) {
                    Text("Go Back")
                }
            }
        }
    } else {
        SnackbarTaskScreen(onNavigate = { isOnSecondScreen = true })
    }
}

@Composable
fun SnackbarTaskScreen(onNavigate: () -> Unit) {
    // 1. Create Scaffold + SnackbarHost + SnackbarHostState
    val snackbarHostState = remember { SnackbarHostState() }

    // Get the coroutine scope bound to this composition lifecycle
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(onClick = {
                        // 2. Tapping the button calls scope.launch to show the snackbar[cite: 4]
                        scope.launch {
                            snackbarHostState.showSnackbar("Hello from Snackbar!")
                        }
                    }) {
                        Text("Show Snackbar")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Button to navigate to another screen and come back[cite: 4]
                    Button(onClick = onNavigate) {
                        Text("Navigate to Another Screen")
                    }
                }
            }
        }
    )

    /*
     * ==========================================
     * ANSWERS TO LAB CHECK QUESTIONS & COMMENTS:
     * ==========================================
     *
     * 1. Why `LaunchedEffect` does not work here:
     *    - `LaunchedEffect` is designed to run side-effects automatically when entering
     *      composition or when its keys change. It reacts to state changes, whereas showing
     *      a snackbar must happen in response to an explicit user event (like clicking a button).
     *      You cannot tie a one-off click callback directly to a `LaunchedEffect` without
     *      introducing artificial state flags, which is clumsy and incorrect. `rememberCoroutineScope`
     *      gives us a scope to launch coroutines imperatively from standard event callbacks.
     *
     * 2. What a second tap does before the snackbar finishes:
     *    - If you tap the button again while a snackbar is currently visible, `showSnackbar`
     *      suspends until the current snackbar finishes or replaces/queues it depending on
     *      implementation behavior (in Compose Material3, subsequent calls are queued or dismissed
     *      to show the latest message sequentially).
     *
     * 3. Behavior when leaving the screen:
     *    - Because `rememberCoroutineScope` is automatically cancelled when the composable leaves
     *      the composition, any running snackbar coroutines are safely cleaned up, preventing
     *      memory leaks or crashes when returning[cite: 4].
     */
}
