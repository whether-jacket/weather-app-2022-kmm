package co.touchlab.kampkit.android.landingpage.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.kampkit.android.design.Spacing
import co.touchlab.kampkit.android.design.TextSize
import co.touchlab.kampkit.metaweather.repo.WeatherReport

@Composable
fun WeatherReportView(weatherReport: WeatherReport, errorMessage: String) {
    MaterialTheme(typography = MaterialTheme.typography) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = weatherReport.cityTitle,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = (MaterialTheme.typography).body1
                    .merge(TextStyle(fontSize = TextSize.xxxl)),
                modifier = Modifier
                    .padding(start = Spacing.HorizontalSpacing.m)
                    .fillMaxWidth()
            )
            Text(
                text = weatherReport.countryTitle,
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = (MaterialTheme.typography).body1
                    .merge(TextStyle(fontSize = TextSize.xxl)),
                modifier = Modifier
                    .padding(start = Spacing.HorizontalSpacing.m)
                    .fillMaxWidth()
            )
            Divider(modifier = Modifier.padding(8.dp))
            Row {
                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = weatherReport.temperature,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center,
                        style = (MaterialTheme.typography).body1
                            .merge(TextStyle(fontSize = TextSize.xxl)),
                        modifier = Modifier
                            .padding(
                                start = Spacing.HorizontalSpacing.m,
                                end = Spacing.HorizontalSpacing.m
                            )
                    )
                }
                Row {
                    Text(
                        text = weatherReport.humidity,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center,
                        style = (MaterialTheme.typography).body1
                            .merge(TextStyle(fontSize = TextSize.xxl)),
                        modifier = Modifier
                            .padding(
                                start = Spacing.HorizontalSpacing.m,
                                end = Spacing.HorizontalSpacing.m
                            )
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
                        text = weatherReport.windSpeed,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center,
                        style = (MaterialTheme.typography).body1
                            .merge(TextStyle(fontSize = TextSize.xxl)),
                        modifier = Modifier
                            .padding(
                                start = Spacing.HorizontalSpacing.m,
                                end = Spacing.HorizontalSpacing.m
                            )
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = weatherReport.airPressure,
                        color = Color.DarkGray,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        style = (MaterialTheme.typography).body1
                            .merge(TextStyle(fontSize = TextSize.xxl)),
                        modifier = Modifier
                            .padding(
                                start = Spacing.HorizontalSpacing.m,
                                end = Spacing.HorizontalSpacing.m
                            )
                    )
                }
            }
            Text(
                text = errorMessage,
                color = Color.DarkGray,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                style = (MaterialTheme.typography).body1
                    .merge(TextStyle(fontSize = TextSize.xxl)),
                modifier = Modifier
                    .padding(start = Spacing.HorizontalSpacing.m, end = Spacing.HorizontalSpacing.m)
            )
        }
    }
}