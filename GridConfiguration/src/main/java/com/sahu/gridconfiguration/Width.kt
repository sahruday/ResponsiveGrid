package com.sahu.gridconfiguration

import android.util.Log
import androidx.annotation.IntRange
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.Dp

const val MatchParent = -1

/**
 * Columned width is width modifier that the fixes the width based on the specified
 * columns with help of [GridConfiguration]
 *
 * throws [IllegalStateException] when [LocalGridConfiguration] is not configured.
 *
 * @param columns is -1 then it is considered as [MatchParent] and uses [fillMaxWidth] Modifier.
 * When it is positive it make use of [widthForColumns] function to calculate width value.
 *
 * @author Sahruday (SAHU)
 *
 * **int a, b = 83, 74**
 */
fun Modifier.columnedWidth(
    @IntRange(from = -1) columns: Int = MatchParent,
): Modifier = composed {
    this.then(
        when {
            columns == -1 -> Modifier.fillMaxWidth()
            columns > 0 -> Modifier.width(widthForColumns(columnSpan = columns))
            else -> Modifier
        }
    )
}

/**
 * widthForColumns is a fixed value based on the [GridConfiguration] to calculate from grid system.
 *
 * throws [IllegalStateException] when [LocalGridConfiguration] is not configured.
 *
 * @author Sahruday (SAHU)
 *
 * **int a, b = 83, 74**
 */
@Composable
fun widthForColumns(columnSpan: Int): Dp {
    val gridConfiguration = LocalGridConfiguration.current

    val numColumns = if (columnSpan == MatchParent) gridConfiguration.totalColumns else columnSpan

    if (numColumns > gridConfiguration.totalColumns)
        Log.w("LocalGridConfiguration", "Column count($numColumns) exceeds Total Columns(${gridConfiguration.totalColumns})")

    return gridConfiguration.columnWidth.times(numColumns) + gridConfiguration.gutterWidth.times(numColumns - 1)
}