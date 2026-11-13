import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChipRow(modifier: Modifier=Modifier) {
    val scrollState=rememberScrollState()
    Row(
        modifier=modifier.horizontalScroll(scrollState).padding(16.dp)
    )
    {
        Text(text="All", modifier=Modifier.padding(16.dp))
        Text(text="Electronics", modifier=Modifier.padding(16.dp))
        Text(text="Books", modifier=Modifier.padding(16.dp))
        Text(text="All", modifier=Modifier.padding(16.dp))
        Text(text="Electronics", modifier=Modifier.padding(16.dp))
        Text(text="Books", modifier=Modifier.padding(16.dp))


    }


}
