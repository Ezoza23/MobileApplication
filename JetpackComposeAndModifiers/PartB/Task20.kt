package uz.ttpu.practice

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Animation(){
    val isVisible = true
    val alpha by animateFloatAsState(targetValue=if(isVisible) 5f else 0f)
    Card(
        modifier= Modifier.padding(40.dp)
            .graphicsLayer{
                this.alpha=alpha
            }){
        Text(text="Hello", modifier=Modifier.padding(16.dp))
    }
}