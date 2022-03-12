package com.sahu.panes

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.sahu.gridconfiguration.LocalGridConfiguration
import com.sahu.gridconfiguration.MatchParent

/**
 * Pane Config is the required configuration of the sub layout.
 *
 * @param columnSpan is the number of columns that it takes from parent layout's totalColumns.
 * @param horizontalMargin is the margin that to set for layout after division. Default null to take same as parent.
 * @param gutterWidth is the width that to set for layout after division. Default null to take same as parent.
 * @param totalColumns is the columns that this sub-layout takes after division. Default will be same as [columnSpan].
 *
 * @author Sahruday (SAHU)
 *
 * **int a, b = 83, 74**
 */
data class PaneConfig(
    val columnSpan: Int,
    private val horizontalMargin: Dp? = null,
    private val gutterWidth: Dp? = null,
    val totalColumns: Int = columnSpan,
) {
    @Composable
    fun getHorizontalMargin(): Dp =
        horizontalMargin ?: LocalGridConfiguration.current.horizontalMargin

    @Composable
    fun getGutterWidth(): Dp =
        gutterWidth ?: LocalGridConfiguration.current.gutterWidth

    @Composable
    fun getPaneColumns(): Int =
        if (columnSpan == MatchParent) LocalGridConfiguration.current.totalColumns else columnSpan

}