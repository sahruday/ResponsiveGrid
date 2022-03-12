package com.sahu.panes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sahu.gridconfiguration.LocalGridConfiguration
import com.sahu.gridconfiguration.MatchParent
import com.sahu.gridconfiguration.rememberGridConfiguration
import com.sahu.gridconfiguration.widthForColumns

/**
 * Centered Pane is a Composable that provides space to show content based on mode and device type.
 *
 * ![Breakpoint System](https://material.io/design/layout/responsive-layout-grid.html#breakpoints)
 *
 *
 * **|--| -- -- --- |--| -------- |--| ------- |--|**
 *
 * **|M| Column |G| Column |G| Column |M|**
 *
 * **|--SPACE--|-- Content --|--SPACE--|**
 *
 * M: Margin
 * G: Gutter
 *
 * **NOTE: Need not apply gutters anymore, consider only margins.**
 *
 * **NOTE: Container takes complete width when device is not Tablet.**
 *
 * @param centerPaneConfig is the [PaneConfig] of the Center details.
 * @param contentAlignment is the Alignment of the content inside the Box.
 *
 * @author Sahruday (SAHU)
 *
 * **int a, b = 83, 74**
 */
@Composable
fun CenteredPane(
    modifier: Modifier = Modifier,
    centerPaneConfig: PaneConfig = PaneConfig(MatchParent),
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit,
) {
    val gridConfiguration = LocalGridConfiguration.current
    require(
        (centerPaneConfig.columnSpan in 1..gridConfiguration.totalColumns)
            || centerPaneConfig.columnSpan == MatchParent
    ) { "Total column count mismatch" }

    Box(modifier = modifier,
        contentAlignment = contentAlignment
    ) {
        if (centerPaneConfig.columnSpan != MatchParent) {
            val layoutWidth = widthForColumns(columnSpan = centerPaneConfig.columnSpan)
            val horizontalMargin = (gridConfiguration.layoutWidth - layoutWidth) / 2

            CompositionLocalProvider(
                LocalGridConfiguration provides
                        rememberGridConfiguration(
                            layoutWidth = gridConfiguration.layoutWidth,
                            horizontalMargin = horizontalMargin,
                            gutterWidth = gridConfiguration.gutterWidth,
                            totalColumns = centerPaneConfig.totalColumns)
            ) {
                content()
            }
        } else {
            content()
        }
    }
}