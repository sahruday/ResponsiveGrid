package com.sahu.responsivegrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sahu.gridconfiguration.LocalGridConfiguration
import com.sahu.gridconfiguration.columnedWidth
import com.sahu.responsivegrid.gridsystem.ResponsiveGridOverlay
import com.sahu.responsivegrid.gridsystem.rememberGridConfiguration
import com.sahu.responsivegrid.ui.theme.ResponsiveGridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResponsiveGridTheme {
                var showOverLay by remember { mutableStateOf(false)}

                CompositionLocalProvider(LocalGridConfiguration provides rememberGridConfiguration()) {
                    ResponsiveGridOverlay(showOverLay) {
                        Surface(color = MaterialTheme.colors.background) {
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Checkbox(checked = showOverLay, onCheckedChange = { showOverLay = !showOverLay })
                                    Text(text = "Show Grid Overlay", style = MaterialTheme.typography.h6)
                                }
                                GridColumns(modifier = Modifier.padding(horizontal = LocalGridConfiguration.current.horizontalMargin))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GridColumns(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(50.dp)
    ) {
        val gridConfiguration = LocalGridConfiguration.current
        val columnCount = gridConfiguration.totalColumns

        if (columnCount < 1) return

        for (i in 0..columnCount)
            Row(
                horizontalArrangement = Arrangement.spacedBy(gridConfiguration.gutterWidth)
            ) {
                if (i > 0)
                    Box(
                        modifier = Modifier
                            .columnedWidth(i)
                            .height(60.dp)
                            .background(MaterialTheme.colors.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$i",
                            style = MaterialTheme.typography.h5
                        )
                    }

                if (columnCount - i > 0)
                    Box(
                        modifier = Modifier
                            .columnedWidth(columnCount - i)
                            .height(60.dp)
                            .background(MaterialTheme.colors.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${gridConfiguration.totalColumns - i}",
                            style = MaterialTheme.typography.h5,
                        )
                    }
            }
    }
}