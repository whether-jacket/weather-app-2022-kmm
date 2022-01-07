package co.touchlab.kampkit.android.metaweather.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.kampkit.android.metaweather.model.WeatherStats

@Composable
fun WeatherReportView(weatherStats: WeatherStats) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = weatherStats.cityTitle,
            color = Color.Black,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = weatherStats.countryTitle,
            color = Color.Black,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
        Divider(modifier = Modifier.padding(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = weatherStats.temperature,
                    color = Color.DarkGray,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp, top = 16.dp)
                )
            }
            Spacer(modifier = Modifier.padding(start = 10.dp, end = 150.dp))
            Row(
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Humidity\n${weatherStats.humidity}",
                    color = Color.DarkGray,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Wind Speed\n${weatherStats.windSpeed}",
                    color = Color.DarkGray,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp, top = 16.dp)
                )
            }
            Spacer(modifier = Modifier.padding(start = 10.dp, end = 100.dp))
            Row(
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Air Pressure\n${weatherStats.airPressure}",
                    color = Color.DarkGray,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}