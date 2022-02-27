package com.sahu.responsivegrid.gridsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.dp
import com.sahu.gridconfiguration.GridConfiguration
import com.sahu.gridconfiguration.rememberGridConfiguration
import com.sahu.responsivegrid.R

/**
 * Empty params call takes the default gird system that is predefined in theme for
 * complete Device screen.
 *
 * @author Sahruday (SAHU)
 *
 * **int a, b = 83, 74**
 */
@Composable
fun rememberGridConfiguration(): GridConfiguration =
    rememberGridConfiguration(
        layoutWidth = LocalConfiguration.current.screenWidthDp.dp,
        horizontalMargin = dimensionResource(id = R.dimen.layout_horizontal_margin),
        gutterWidth = dimensionResource(id = R.dimen.layout_gutter_width),
        totalColumns = integerResource(id = R.integer.layout_columns)
    )
