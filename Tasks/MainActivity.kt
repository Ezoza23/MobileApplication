import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                LifecycleTaskScreen()
            }
        }
    }
}

@Composable
fun LifecycleTaskScreen() {
    // Switch state to show and hide the composable[cite: 5]
    var showComposable by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Show Lifecycle Composable")
            Switch(
                checked = showComposable,
                onCheckedChange = { showComposable = it }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Show or hide the composable based on the switch state[cite: 5]
        if (showComposable) {
            LifecycleLoggerComponent()
        } else {
            Text("Composable is currently hidden (Disposed)", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun LifecycleLoggerComponent() {
    // Make lifecycleOwner the effect key[cite: 5]
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        // Create the observer to log ON_START and ON_STOP events[cite: 5]
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> Log.d("LifecycleLogger", "addObserver: ON_START triggered")
                Lifecycle.Event.ON_STOP -> Log.d("LifecycleLogger", "ON_STOP triggered")
                else -> {}
            }
        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)
        Log.d("LifecycleLogger", "Observer added successfully")

        onDispose {
            // Remove the observer and write a log line[cite: 5]
            lifecycleOwner.lifecycle.removeObserver(observer)
            Log.d("LifecycleLogger", "removeObserver: Component disposed, observer removed")
        }
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier.padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Active Composable with DisposableEffect", style = MaterialTheme.typography.titleMedium)
        }
    }

    /*
     * ==========================================
     * ANSWERS TO LAB CHECK REQUIREMENTS & QUESTIONS:
     * ==========================================
     *
     * 1. Toggle the Switch 5 times check:
     *    - When you toggle the Switch on and off 5 times, `DisposableEffect` enters
     *      and leaves the composition 5 times. Each time it enters, `addObserver` runs,
     *      and each time it leaves, `onDispose` (`removeObserver`) runs. Therefore,
     *      the number of `addObserver` and `removeObserver` log lines will be perfectly equal.
     *
     * 2. Temporarily remove `onDispose` and look at the build error check:
     *    - If you remove `onDispose { ... }`, Kotlin will throw a compilation/build error:
     *      "A DisposableEffect must provide an onDispose clause."
     *      This is because `DisposableEffect` requires a cleanup block by design to prevent memory
     *      leaks and dangling references when the composable leaves the screen.
     */
}