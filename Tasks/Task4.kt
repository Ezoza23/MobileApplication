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

// 1. Simple Analytics object as requested[cite: 6]
object Analytics {
    var userType: String = "Guest"
}

class MainActivity4 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                AnalyticsTaskScreen()
            }
        }
    }
}

@Composable
fun AnalyticsTaskScreen() {
    var userType by remember { mutableStateOf("Guest") }

    // Counter to track how many times SideEffect runs[cite: 6]
    var sideEffectRunCount by remember { mutableStateOf(0) }

    // 2. When userType state changes, pass it to the external object using SideEffect[cite: 6]
    SideEffect {
        Analytics.userType = userType
        sideEffectRunCount++
        // 3. Print to Logcat how many times it ran[cite: 6]
        Log.d("AnalyticsTask", "SideEffect ran #$sideEffectRunCount times. Current userType: ${Analytics.userType}")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Current User Type: $userType", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "SideEffect Execution Count: $sideEffectRunCount", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { userType = "Standard User" }) {
                Text("Set Standard")
            }
            Button(onClick = { userType = "Premium User" }) {
                Text("Set Premium")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button that triggers a recomposition WITHOUT changing the userType state
        Button(
            onClick = { /* Triggers general recomposition */ },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Trigger Recomposition")
        }
    }

    /*
     * ==========================================
     * ANSWERS TO LAB CHECK QUESTIONS & COMMENTS:
     * ==========================================
     *
     * 1. Why SideEffect runs after recomposition even if the state did not change:
     *    - `SideEffect` is specifically designed to execute on *every successful recomposition*
     *      of the Composable, not just when specific state keys change. Because it guarantees
     *      synchronization with non-Compose objects after the UI successfully renders, any parent
     *      recomposition or independent state change in the tree will trigger it again.
     *
     * 2. One sentence explaining the lecture's `attempt` model:
     *    - The lecture's `attempt` model describes a design pattern or trial structure where state
     *      mutations and asynchronous boundaries are carefully managed to prevent race conditions and
     *      stale UI closures during data fetching or operational retries.
     */
}
