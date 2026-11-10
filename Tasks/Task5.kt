import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity5 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                ScrollTaskScreen()
            }
        }
    }
}

@Composable
fun ScrollTaskScreen() {
    var selectedVariant by remember { mutableStateOf(1) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { selectedVariant = 1 }) {
                Text("Variant 1 (Direct Read)")
            }
            Button(onClick = { selectedVariant = 2 }) {
                Text("Variant 2 (derivedStateOf)")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (selectedVariant == 1) {
            DirectReadScrollScreen()
        } else {
            DerivedStateScrollScreen()
        }
    }
}

// -------------------------------------------------------------
// Variant 1: Reading scroll state directly (causes high recompositions)
// -------------------------------------------------------------
@Composable
fun DirectReadScrollScreen() {
    val listState = rememberLazyListState()
    var recompositionCount by remember { mutableStateOf(0) }

    // SideEffect to track recompositions on every render
    SideEffect {
        recompositionCount++
    }

    // Direct read of scroll state property (changes on every single pixel scrolled)
    val showButton = listState.firstVisibleItemIndex > 0

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Text("Variant 1: Direct Read", style = MaterialTheme.typography.titleMedium)
            Text("Recompositions: $recompositionCount", color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                state = listState,
                modifier = Modifier.weight(1.0f).fillMaxWidth()
            ) {
                items(100) { index ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Text(text = "Item #$index", modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }

        if (showButton) {
            val scope = rememberCoroutineScope()
            Button(
                onClick = { scope.launch { listState.animateScrollToItem(0) } },
                modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
            ) {
                Text("Top")
            }
        }
    }
}

// -------------------------------------------------------------
// Variant 2: Using derivedStateOf and snapshotFlow analytics
// -------------------------------------------------------------
@Composable
fun DerivedStateScrollScreen() {
    val listState = rememberLazyListState()
    var recompositionCount by remember { mutableStateOf(0) }

    SideEffect {
        recompositionCount++
    }

    // Using derivedStateOf so UI only recomposes when the threshold (index > 0) actually changes
    val showButton by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }

    // snapshotFlow + map + distinctUntilChanged + filter for analytics event
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .map { index -> index > 0 }
            .distinctUntilChanged()
            .filter { crossedThreshold -> crossedThreshold } // Fires when passing item 0 threshold
            .collect {
                Log.d("AnalyticsEvent", "User crossed threshold: passed item 0! Analytics event sent.")
            }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Text("Variant 2: derivedStateOf", style = MaterialTheme.typography.titleMedium)
            Text("Recompositions: $recompositionCount", color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                state = listState,
                modifier = Modifier.weight(1.0f).fillMaxWidth()
            ) {
                items(100) { index ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Text(text = "Item #$index", modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = showButton,
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
        ) {
            val scope = rememberCoroutineScope()
            Button(onClick = { scope.launch { listState.animateScrollToItem(0) } }) {
                Text("Top")
            }
        }
    }

    /*
     * ==========================================
     * ANSWERS TO LAB CHECK QUESTIONS & COMMENTS:
     * ==========================================
     *
     * 1. Scroll down and up slowly check:
     *    - The `derivedStateOf` variant gives far fewer recompositions because `firstVisibleItemIndex`
     *      changes on every single pixel scrolled in Variant 1, triggering continuous recomposition
     *      of the entire screen wrapper. In Variant 2, `derivedStateOf` memoizes the boolean condition
     *      (`index > 0`), so recompositions only occur at the exact boundary when crossing item 0.
     *
     * 2. Logcat event check:
     *    - Thanks to `distinctUntilChanged()`, Logcat logs exactly one analytics event when
     *      crossing the threshold, rather than repeating on every single scroll offset change.
     */
}
