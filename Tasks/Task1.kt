import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                SplashLabScreen()
            }
        }
    }
}

@Composable
fun SplashLabScreen() {
    // Parent version counter increasing every 500 ms[cite: 2]
    var version by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(500L)
            version++
        }
    }

    // Lambda created in the parent[cite: 2]
    val onTimeout = { Log.d("SplashCheck", "v$version") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Current Version: v$version", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // Variant 1: Capturing lambda directly (prints old version v0)[cite: 2, 3]
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Variant 1 (Direct)")
                Spacer(modifier = Modifier.height(8.dp))
                DirectLandingScreen(onTimeout = onTimeout)
            }

            // Variant 2: Using rememberUpdatedState (prints latest version)[cite: 2, 3]
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Variant 2 (Updated)")
                Spacer(modifier = Modifier.height(8.dp))
                UpdatedLandingScreen(onTimeout = onTimeout)
            }
        }
    }
}

// Variant 1: Direct capture
@Composable
fun DirectLandingScreen(onTimeout: () -> Int) {
    LaunchedEffect(Unit) {
        delay(2000L) // Wait 2 seconds[cite: 2]
        onTimeout() // Stale callback -> prints v0[cite: 3]
    }
}

// Variant 2: Using rememberUpdatedState
@Composable
fun UpdatedLandingScreen(onTimeout: () -> Int) {
    val currentOnTimeout by rememberUpdatedState(onTimeout)

    LaunchedEffect(Unit) {
        delay(2000L) // Wait 2 seconds[cite: 2]
        currentOnTimeout() // Non-stale callback -> prints latest version[cite: 2, 3]
    }

    /*
     * NOTE FOR LAB CHECK:
     * If we change LaunchedEffect(Unit) to LaunchedEffect(onTimeout), the wait timer restarts
     * every 500ms.
     * Reason: Every time the parent recomposes, a new `onTimeout` lambda reference is created.
     * Because the key changes, LaunchedEffect cancels the running coroutine and restarts the
     * 2-second delay from the beginning indefinitely.[cite: 3]
     */
}
