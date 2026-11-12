package uz.ttpu.practice

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Btn() {
    Button(
        onClick = {},
        modifier = Modifier.testTag("submit_btn")
            .padding(40.dp)
    ) {
        Text(text = "Submit")
    }


}
