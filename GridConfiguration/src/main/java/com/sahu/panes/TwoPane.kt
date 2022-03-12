package com.sahu.panes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.sahu.gridconfiguration.LocalGridConfiguration
import com.sahu.gridconfiguration.rememberGridConfiguration
import com.sahu.gridconfiguration.widthForColumns

/**
 * Tablet Pane is a Composable that divides into [left] and [right] panes as 4 and 8 parts each for 10" devices.
 *
 * ![Tablet Layout Grid](https://material.io/design/layout/responsive-layout-grid.html#columns-gutters-and-margins)
 *
 * ![Breakpoint System](https://material.io/design/layout/responsive-layout-grid.html#breakpoints)
 *
 *
 * **|--| -- -- --- |--| -------- |--| ------- |--|**
 *
 * **|M| Column |G| Column |G| Column |M|**
 *
 * **|--- Left ---|---------- Right ---------|**
 *
 * M: Margin
 * G: Gutter
 *
 * **NOTE: Need not apply gutters anymore, consider only margins.**
 *
 * **NOTE: sum of columns in [leftPaneConfig] and [rightPaneConfig] should be current layout totalColumns.**
 *
 * **Note: If one of them is equal to totalColumns that alone fills the screen with detail pane as 1st priority.**
 *
 * [left] and [right] will be divided at a gutter and that gutter width will be added to [right].
 *
 * @param left [Composable] that takes master pane components.
 * @param right [Composable] that takes detail pane components.
 * @param modifier [Modifier] of the Tablet Pane.
 * @param leftPaneConfig is the [PaneConfig] of the master pane.
 * @param rightPaneConfig is the [PaneConfig] of the detail pane.
 *
 * @author Sahruday (SAHU)
 *
 * **int a, b = 83, 74**
 */
@Composable
fun TwoPane(
    left: @Composable BoxScope.() -> Unit,
    right: @Composable BoxScope.() -> Unit,
    modifier: Modifier = Modifier,
    leftPaneConfig: PaneConfig = PaneConfig(LocalGridConfiguration.current.totalColumns/2),
    rightPaneConfig: PaneConfig = PaneConfig(LocalGridConfiguration.current.totalColumns/2),
) {
    val gridConfiguration = LocalGridConfiguration.current

    val onlyLeftPane = leftPaneConfig.getPaneColumns() == gridConfiguration.totalColumns
    val onlyRightPane = rightPaneConfig.getPaneColumns() == gridConfiguration.totalColumns

    require(
        leftPaneConfig.getPaneColumns() + rightPaneConfig.getPaneColumns() == gridConfiguration.totalColumns
                || onlyLeftPane
                || onlyRightPane
    ) { "Total column count mismatch" }

    Row(modifier = modifier.fillMaxSize()) {

        if (onlyLeftPane || onlyRightPane) {
            val (config, layout) =
                if (onlyRightPane) rightPaneConfig to right
                else leftPaneConfig to left


            val layoutConfiguration = rememberGridConfiguration(
                layoutWidth = gridConfiguration.layoutWidth,
                horizontalMargin = config.getHorizontalMargin(),
                gutterWidth = config.getGutterWidth(),
                totalColumns = config.totalColumns)

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(gridConfiguration.layoutWidth)
            ) {
                CompositionLocalProvider(LocalGridConfiguration provides layoutConfiguration) {
                    layout()
                }
            }
        } else {

            val leftWidth = widthForColumns(columnSpan = leftPaneConfig.columnSpan) + gridConfiguration.horizontalMargin
            val rightWidth = gridConfiguration.layoutWidth - leftWidth

            val leftConfiguration = rememberGridConfiguration(layoutWidth = leftWidth,
                horizontalMargin = leftPaneConfig.getHorizontalMargin(),
                gutterWidth = leftPaneConfig.getGutterWidth(),
                totalColumns = leftPaneConfig.totalColumns)

            val rightConfiguration = rememberGridConfiguration(layoutWidth = rightWidth,
                horizontalMargin = rightPaneConfig.getHorizontalMargin(),
                gutterWidth = rightPaneConfig.getGutterWidth(),
                totalColumns = rightPaneConfig.totalColumns)

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(leftWidth)
            ) {
                CompositionLocalProvider(LocalGridConfiguration provides leftConfiguration) {
                    left()
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(rightWidth)
            ) {
                CompositionLocalProvider(LocalGridConfiguration provides rightConfiguration) {
                    right()
                }
            }
        }
    }
}