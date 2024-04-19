package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeLayout(modifier: Modifier = Modifier) {
    var stepNumber by remember {
        mutableStateOf(1)
    }

    var numberOfTaps by remember {
        mutableStateOf(0)
    }

    var step2taps by remember{
        mutableStateOf(0)
    }

    val imageId = when (stepNumber) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val contentDescriptionId = when (stepNumber){
        1 -> R.string.content_description_1
        2 -> R.string.content_description_2
        3 -> R.string.content_description_3
        else -> R.string.content_description_4
    }
    val textId = when (stepNumber){
        1 -> R.string.step_1
        2 -> R.string.step_2
        3 -> R.string.step_3
        else -> R.string.step_4
    }

    Column (
        modifier = modifier
    ){
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFFEDC56))
                .padding(18.dp),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FilledTonalButton(
                shape = RoundedCornerShape(50.dp),
                onClick = {
                    if (stepNumber == 1){
                        step2taps = (2..4).random()
                    }
                    numberOfTaps = when(stepNumber){
                        2 -> numberOfTaps+1
                        else ->0
                    }
                    stepNumber = if ((stepNumber == 1 || stepNumber ==3) || (stepNumber == 2 && numberOfTaps == step2taps)) stepNumber+1
                        else if (stepNumber == 4) 1
                        else stepNumber

                }
            ) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = stringResource(id = contentDescriptionId)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = textId),
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeTheme {
        LemonadeLayout(modifier = Modifier.fillMaxSize())
    }
}