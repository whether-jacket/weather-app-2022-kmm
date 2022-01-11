package co.touchlab.kampkit.android.landingpage.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.touchlab.kampkit.android.design.Spacing
import co.touchlab.kampkit.android.design.TextStyles
import co.touchlab.kampkit.android.design.TextStyling
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
                style = TextStyles.xxxl,
                modifier = Modifier
                    .padding(start = Spacing.Horizontal.m)
                    .fillMaxWidth()
            )
            Text(
                text = weatherReport.countryTitle,
                style = TextStyles.xxl,
                modifier = Modifier
                    .padding(start = Spacing.Horizontal.m)
                    .fillMaxWidth()
            )
            Divider(modifier = Modifier.padding(8.dp))
            Row {
                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = weatherReport.temperature,
                        style = TextStyles.xxl,
                        modifier = Modifier
                            .padding(
                                start = Spacing.Horizontal.m,
                                end = Spacing.Horizontal.m
                            )
                    )
                }
                Row {
                    Text(
                        text = weatherReport.humidity,
                        style = TextStyles.xxl,
                        modifier = Modifier
                            .padding(
                                start = Spacing.Horizontal.m,
                                end = Spacing.Horizontal.m
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
                        style = TextStyles.xxl,
                        modifier = Modifier
                            .padding(
                                start = Spacing.Horizontal.m,
                                end = Spacing.Horizontal.m
                            )
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = weatherReport.airPressure,
                        style = TextStyles.xxl,
                        modifier = Modifier
                            .padding(
                                start = Spacing.Horizontal.m,
                                end = Spacing.Horizontal.m
                            )
                    )
                }
            }
            Text(
                text = errorMessage,
                style = TextStyles.xxl,
                modifier = Modifier
                    .padding(start = Spacing.Horizontal.m, end = Spacing.Horizontal.m)
            )
        }
    }
}