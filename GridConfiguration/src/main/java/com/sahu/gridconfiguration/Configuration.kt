package com.sahu.gridconfiguration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp

/**
 * Grid Configuration is the system that the screen is divided into multiple columns.
 *
 * ![Responsive Grid Layout](https://material.io/design/layout/responsive-layout-grid.html#columns-gutters-and-margins)
 *
 * @param layoutWidth is the width of the layout in [Dp] that the layout is getting
 * rendered.
 * @param columnWidth is the width of the single column int the grid system in [Dp]
 * @param horizontalMargin is the margin within the layout that can be applied to
 * the layout in [Dp].
 * @param gutterWidth is the width/space in between the columns, which can be act
 * as spacing for the items (if there were multiple items to show) in [Dp].
 * @param totalColumns is the count of columns that the layout is having in it.
 *
 * @author Sahruday (SAHU)
 *
 * **int a, b = 83, 74**
 */
data class GridConfiguration(
    val layoutWidth: Dp,
    val columnWidth: Dp,
    val horizontalMargin: Dp,
    val gutterWidth: Dp,
    val totalColumns: Int,
)

/**
 * Customisable grid system to configure if required for the sub-layout which also
 * follows the grid system.
 *
 * @param layoutWidth is the width of the layout that going to have this system.
 * @param horizontalMargin is the margin on both ends (Start and End) for the layout to
 * follow.
 * @param gutterWidth is the width that the layout has to follow for spacing.
 * @param totalColumns is the columns that the layout is having in it.
 *
 * @author Sahruday (SAHU)
 *
 * **int a, b = 83, 74**
 */
@Composable
fun rememberGridConfiguration(
    layoutWidth: Dp,
    horizontalMargin: Dp,
    gutterWidth: Dp,
    totalColumns: Int,
) = remember {
    gridConfiguration(
        layoutWidth,
        horizontalMargin,
        gutterWidth,
        totalColumns
    )
}

private fun gridConfiguration(
    windowWidth: Dp,
    horizontalMargin: Dp,
    gutterWidth: Dp,
    totalColumns: Int,
): GridConfiguration {

    val columnLength = (windowWidth - horizontalMargin * 2 - gutterWidth * (totalColumns - 1)) / totalColumns

    return GridConfiguration(
        layoutWidth = windowWidth,
        columnWidth = columnLength,
        horizontalMargin = horizontalMargin,
        gutterWidth = gutterWidth,
        totalColumns = totalColumns
    )
}

val LocalGridConfiguration = compositionLocalOf<GridConfiguration>(
    neverEqualPolicy()
) { error("Local Grid Configuration not present") }
