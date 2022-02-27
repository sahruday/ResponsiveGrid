package com.sahu.responsivegrid.gridsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sahu.gridconfiguration.LocalGridConfiguration

const val OverlayAlpha = 0.2f
val BorderWidth = 1.dp

/**
 * Responsive Grid Overlay is the overlay based on the current [LocalGridConfiguration],
 * over the [content] that to render.
 *
 * @author Sahruday (SAHU)
 *
 * **int a, b = 83, 74**
 */
@Composable
fun ResponsiveGridOverlay(
    drawOverlay: Boolean = true,
    showBorder: Boolean = true,
    colors: ResponsiveGridOverLayColors = ResponsiveGridOverLayDefaults.responsiveGridOverLayColors(),
    content: @Composable () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        content.invoke()

        if (drawOverlay)
            ResponsiveGridOverlay(showBorder = showBorder, colors = colors)
    }
}

/**
 * Responsive Grid Overlay is the overLay based on the current [LocalGridConfiguration].
 *
 * @author Sahruday (SAHU)
 *
 * **int a, b = 83, 74**
 */
@Composable
fun ResponsiveGridOverlay(
    showBorder: Boolean = true,
    colors: ResponsiveGridOverLayColors = ResponsiveGridOverLayDefaults.responsiveGridOverLayColors(),
) {
    val gridConfiguration = LocalGridConfiguration.current
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier
            .fillMaxHeight()
            .width(gridConfiguration.horizontalMargin)
            .background(colors.marginColor())
        ) {
            if (showBorder)
                Box(Modifier
                    .fillMaxHeight()
                    .width(BorderWidth)
                    .background(colors.borderColor())
                    .align(Alignment.TopEnd))
        }
        repeat(gridConfiguration.totalColumns - 1) {
            Box(modifier = Modifier
                .fillMaxHeight()
                .width(gridConfiguration.gutterWidth)
                .background(colors.gutterColor())
            ) {
                if (showBorder)
                    Box(Modifier
                        .fillMaxHeight()
                        .width(BorderWidth)
                        .background(colors.borderColor())
                        .align(Alignment.TopStart))
                if (showBorder)
                    Box(Modifier
                        .fillMaxHeight()
                        .width(BorderWidth)
                        .background(colors.borderColor())
                        .align(Alignment.TopEnd))
            }

        }
        Box(modifier = Modifier
            .fillMaxHeight()
            .width(gridConfiguration.horizontalMargin)
            .background(colors.marginColor())
        ) {
            if (showBorder)
                Box(Modifier
                    .fillMaxHeight()
                    .width(BorderWidth)
                    .background(colors.borderColor())
                    .align(Alignment.TopStart))
        }
    }
}

/**
 * Represents the colors of Responsive Grid Overlay.
 *
 * @author Sahruday (SAHU)
 *
 * **int a, b = 83, 74**
 */
interface ResponsiveGridOverLayColors {
    fun marginColor(): Color
    fun gutterColor(): Color
    fun borderColor(): Color
}

/**
 * Contains default values of [ResponsiveGridOverlay].
 *
 * @author Sahruday (SAHU)
 *
 * **int a, b = 83, 74**
 */
object ResponsiveGridOverLayDefaults {

    @Composable
    fun responsiveGridOverLayColors(
        marginColor: Color = MaterialTheme.colors.primary.copy(alpha = OverlayAlpha),
        gutterColor: Color = MaterialTheme.colors.secondary.copy(alpha = OverlayAlpha),
        borderColor: Color = MaterialTheme.colors.error.copy(alpha = OverlayAlpha),
    ): ResponsiveGridOverLayColors = DefaultResponsiveGridOverLayColors(
        marginColor, gutterColor, borderColor
    )
}

/**
 * Default [ResponsiveGridOverLayColors] implementation.
 *
 * @author Sahruday (SAHU)
 *
 * **int a, b = 83, 74**
 */
@Immutable
private class DefaultResponsiveGridOverLayColors(
    private val marginColor: Color,
    private val gutterColor: Color,
    private val borderColor: Color,
) : ResponsiveGridOverLayColors {

    override fun marginColor(): Color = marginColor

    override fun gutterColor(): Color = gutterColor

    override fun borderColor(): Color = borderColor
}